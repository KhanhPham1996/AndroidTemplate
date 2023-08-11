package com.hrs.secrets.imp

import com.hrs.secrets.ApiEndpointUrl

class ApiEndpointUrlImpl: ApiEndpointUrl {

    override val value: String
        get() = "https://api-test.paypense.com/api/v1/"
}