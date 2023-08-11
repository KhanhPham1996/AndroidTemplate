package com.hrs.network

import kotlinx.coroutines.flow.map


object ResourceMapper {
    fun <T, R> kotlinx.coroutines.flow.Flow<NetworkResource<T>>.mapResult(transform: (T) -> R): kotlinx.coroutines.flow.Flow<NetworkResource<R>> {
        return map { networkResult ->
            when (networkResult) {
                is NetworkResource.Success -> {
                    val transformedData =transform(networkResult.data)
                    NetworkResource.Success(transformedData)
                }

                is NetworkResource.Error -> NetworkResource.Error(
                    networkResult.failure
                )
                is NetworkResource.Loading -> NetworkResource.Loading
            }
        }
    }

}