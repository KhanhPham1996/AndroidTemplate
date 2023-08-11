package com.hrs.data.intercepter

import com.hrs.fuction.login.data.Authenticator
import okhttp3.Request

class AuthRefreshInterceptor(private val authenticator: Authenticator): NetworkInterceptor() {

    private val refreshToken: String
        get() = authenticator.userRefreshToken

    override fun applyOptions(builder: Request.Builder) {
        builder.addHeader("Content-Type", "application/json")
        val authorization = refreshToken
        if (authorization.isNotBlank()) {
            builder.addHeader("Authorization", "Bearer $authorization")
        }
    }
}