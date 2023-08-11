package com.hrs.data.network

import com.hrs.network.Failure.*


sealed class UserFailure {

    class PhoneAlreadyRegistered(val error: String?) : FeatureFailure()

    class UserSnsAuthFailure(val error: String?) : FeatureFailure()

    class UserLogoutFailure(val error: String?) : FeatureFailure()

    object UserBlockedPermissionFailure : FeatureFailure()

    object SessionExpiredFailure : FeatureFailure()
}