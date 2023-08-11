package com.hrs.data.network

import com.hrs.network.Failure
import com.hrs.network.Mapper


internal class ErrorResponseMapper<T> : Mapper<NetworkResponse.ApiErrorResponse<T>, Failure> {

    override fun map(from: NetworkResponse.ApiErrorResponse<T>): Failure {
        return when (from.httpCode) {
            401, 403 -> UserFailure.SessionExpiredFailure
            else -> Failure.ServerError(
                code = from.error.message.code,
                error = from.error.message.text
            )
        }
    }
}