package com.hrs.data.intercepter

import com.hrs.fuction.login.data.Authenticator
import com.hrs.mutiplemoduleapp.core.data.BuildConfig

import okhttp3.Request

class AuthInterceptor(private val authenticator: Authenticator): NetworkInterceptor() {

    private val accessToken: String
        get() = authenticator.userAccessToken

    override fun applyOptions(builder: Request.Builder) {
        builder.addHeader("Content-Type", "application/json")
        val authorization = accessToken
        if (authorization.isNotBlank()) {
            builder.addHeader("Authorization", "Bearer $authorization")
            if (BuildConfig.DEBUG) {
                timber.log.Timber.tag("okhttp.OkHttpClient").e("Bearer '%s'", authorization)
            }
        }
    }
}