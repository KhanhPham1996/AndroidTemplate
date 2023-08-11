package com.hrs.di.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientProvider {

    private const val DEFAULT_TIMEOUT_SEC       = 20L // in seconds
    private const val DEFAULT_READ_TIMEOUT_SEC  = 20L // in seconds
    private const val DEFAULT_WRITE_TIMEOUT_SEC = 20L // in seconds

    fun getUploadOkHttpClientBuilder(
        logging: Interceptor,
        header: Interceptor,
        authenticator: okhttp3.Authenticator
    ) = getOkHttpClientBuilder(logging, header, authenticator)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)

    fun getOkHttpClientBuilder(
        logging: Interceptor,
        header: Interceptor,
        authenticator: okhttp3.Authenticator
    ) = getOkHttpClientBuilder(logging, header).authenticator(authenticator)

    fun getOkHttpClientBuilder(
        logging: Interceptor,
        header: Interceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_READ_TIMEOUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor(header)
}