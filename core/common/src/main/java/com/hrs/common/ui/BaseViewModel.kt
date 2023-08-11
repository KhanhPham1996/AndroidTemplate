package com.hrs.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrs.domain.util.UseCase
import com.hrs.network.Failure
import com.hrs.network.NetworkResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.hrs.common.helper.IsLoading

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel(
    private vararg val useCases: UseCase<*, *>?
) : ViewModel() {

    private val _failure: Channel<Failure> = Channel(Channel.BUFFERED)
    val failure: Flow<Failure> = _failure.receiveAsFlow()

    private val _loading: Channel<IsLoading> = Channel(Channel.BUFFERED)
    val loading: Flow<IsLoading> = _loading.receiveAsFlow()

    protected inline fun launch(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        crossinline job: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(coroutineContext) {
        job.invoke(this)
    }

    override fun onCleared() {
        useCases.let { userCases ->
            if (userCases.isNotEmpty()) userCases.forEach { it?.onCleared() }
        }
        super.onCleared()
    }

    protected suspend fun handleFailure(failure: Failure) {
        _loading.send(false)
        _failure.send(failure)
    }

    protected suspend fun showLoading() {
        _loading.send(true)
    }

    protected suspend fun hideLoading() {
        _loading.send(false)
    }

    protected fun <Type> Flow<NetworkResource<Type>>.transformUI(catchFailure: Boolean = true) =
        this.onEach { resource ->
            when (resource) {
                is NetworkResource.Loading -> showLoading()
                is NetworkResource.Success -> hideLoading()
                is NetworkResource.Error -> {
                    if (catchFailure) handleFailure(resource.failure) else hideLoading()
                }
            }
        }
}