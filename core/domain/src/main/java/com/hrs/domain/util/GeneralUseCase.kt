package com.hrs.domain.util

import kotlinx.coroutines.flow.Flow

abstract class GeneralUseCase<out Type, in Params> where Type : Any {

    abstract fun execute(params: Params): Flow<com.hrs.network.NetworkResource<Type>>

    protected operator fun invoke(params: Params): Flow<com.hrs.network.NetworkResource<Type>> = execute(params)

    open fun onCleared() {}

    class None
}