package com.hrs



import com.hrs.data.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import com.hrs.data.network.ErrorMapper
import com.hrs.data.network.ErrorResponseMapper
import com.hrs.data.network.ResponseFailure
import com.hrs.network.Either
import com.hrs.network.NetworkResource
import com.hrs.network.Either.*

import retrofit2.Call

abstract class NetworkRepository {

    open fun <T> safeNetworkFlow(call: Call<T>) =
        flow {
            emit(NetworkResource.Loading)
            when (val rs = execute(call = call)) {
                is Either.Left ->{
                    emit(com.hrs.network.NetworkResource.Error(rs.a))
                }
                is Right ->{
                    emit(com.hrs.network.NetworkResource.Success(rs.b))
                }
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(com.hrs.network.NetworkResource.Error(com.hrs.network.Failure.UnknownFailure(e)))
        }


//    open fun <T> safeCallFlow(call: Call<T>) =
//        flow {
//            when (val rs = execute(call = call)) {
//                is Left -> emit(Left(rs.a))
//                is Right -> emit(Right(rs.b))
//            }
//        }.flowOn(Dispatchers.IO).catch { e ->
//            emit(Left(Failure.UnknownFailure(e)))
//        }



    open fun <T> safeCall(call: Call<T>) =
        execute(call = call)

    private fun <T> execute(call: Call<T>) =
        try {
            when (val apiResponse = NetworkResponse.create(call.execute())) {
                is NetworkResponse.ApiSuccessResponse -> {
                    Right(apiResponse.body)
                }
                is NetworkResponse.ApiEmptyResponse -> {

                        Left(ResponseFailure.SuccessEmptyResponse)
                }
                is NetworkResponse.ApiErrorResponse -> {
                    Left(ErrorResponseMapper<T>().map(apiResponse))
                }
            }
        } catch (err: Throwable) {
            Left(ErrorMapper<T>().map(err))
        }
}