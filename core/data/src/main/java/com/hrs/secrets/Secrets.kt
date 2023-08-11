package com.hrs.secrets

import com.hrs.secrets.imp.ApiEndpointUrlImpl
import com.hrs.secrets.imp.ClientIdImpl
import com.hrs.secrets.imp.ClientSecretImpl

object Secrets {

    val apiEndpointUrl: String
        get() = ApiEndpointUrlImpl().value

    val clientId: String
        get() = ClientIdImpl().value

    val clientSecret: String
        get() = ClientSecretImpl().value
}