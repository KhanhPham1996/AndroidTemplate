package com.hrs.data.network

import com.hrs.network.Failure

sealed class ResponseFailure {

    object SuccessEmptyResponse : Failure.FeatureFailure()
}