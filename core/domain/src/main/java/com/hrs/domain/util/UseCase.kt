package com.hrs.domain.util

import com.hrs.network.Either
import com.hrs.network.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * Thank @[Fernando Cejas](https://github.com/android10)
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onRevoke: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onRevoke(deferred.await())
        }
    }

    open fun onCleared() {}

    class None
}