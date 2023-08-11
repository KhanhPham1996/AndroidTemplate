package com.hrs.network

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class NetworkResource<out R> {

    data class Success<out T>(val data: T) : NetworkResource<T>()
    data class Error(val failure: Failure) : NetworkResource<Nothing>()
    object Loading : NetworkResource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[failure=$failure]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [NetworkResource] is of type [Success] & holds non-null [Success.data].
 */
val NetworkResource<*>.succeeded
    get() = this is NetworkResource.Success && data != null

fun <T> NetworkResource<T>.successOr(fallback: T): T {
    return (this as? NetworkResource.Success<T>)?.data ?: fallback
}

val <T> NetworkResource<T>.data: T?
    get() = (this as? NetworkResource.Success)?.data

/**
 * Updates value of [MutableStateFlow] if [NetworkResource] is of type [Success]
 */
inline fun <reified T> NetworkResource<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is NetworkResource.Success) {
        stateFlow.value = data
    }
}