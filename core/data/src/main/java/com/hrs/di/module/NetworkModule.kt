package com.hrs.di.module

import com.google.gson.Gson
import com.hrs.data.intercepter.AuthInterceptor
import com.hrs.data.intercepter.AuthRefreshInterceptor
import com.hrs.data.intercepter.RequestAuthInterceptor
import com.hrs.di.provider.ConverterFactoryProvider
import com.hrs.di.provider.OkHttpClientProvider
import com.hrs.di.provider.RetrofitProvider
import com.hrs.di.scopes.EndpointInfo
import com.hrs.di.scopes.GsonConverter
import com.hrs.di.scopes.LoggingInterceptor
import com.hrs.di.scopes.OkHttpAuth
import com.hrs.di.scopes.OkHttpRefreshAuth
import com.hrs.di.scopes.OkHttpUploadFile
import com.hrs.di.scopes.RefreshTokenInterceptor
import com.hrs.di.scopes.RetrofitAuth
import com.hrs.di.scopes.RetrofitRefreshAuth
import com.hrs.di.scopes.RetrofitUploadFile
import com.hrs.di.scopes.TokenInterceptor
import com.hrs.fuction.login.data.Authenticator
import com.hrs.fuction.login.network.AuthServiceApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.brickmate.apps.internal.data.di.scopes.MoshiConverter
import kr.brickmate.apps.internal.data.di.scopes.MoshiNetworkAdapter
import com.hrs.secrets.Secrets
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @EndpointInfo
    @Provides
    fun provideBaseApiUrl() = Secrets.apiEndpointUrl

    @GsonConverter
    @Provides
    fun provideConverterFactoryGson(gson: Gson): Converter.Factory =
        ConverterFactoryProvider.getConverterFactory(gson)

    @MoshiConverter
    @Provides
    fun provideConverterFactoryMoshi(@MoshiNetworkAdapter moshi: Moshi): Converter.Factory =
        ConverterFactoryProvider.getConverterFactory(moshi)

    @Provides
    fun providerRefreshAuthInterceptor(
        authenticator: Authenticator,
        serviceApi: AuthServiceApi
    ): okhttp3.Authenticator = RequestAuthInterceptor(authenticator, serviceApi)

    @TokenInterceptor
    @Provides
    fun providerHeaderInterceptor(authenticator: Authenticator): Interceptor {
        return AuthInterceptor(authenticator)
    }

    @RefreshTokenInterceptor
    @Provides
    fun providerRefreshHeaderInterceptor(authenticator: Authenticator): Interceptor {
        return AuthRefreshInterceptor(authenticator)
    }

    @LoggingInterceptor
    @Provides
    fun providerLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
//        level = when (BuildConfig.DEBUG) {
//            true -> HttpLoggingInterceptor.Level.BODY
//            else -> HttpLoggingInterceptor.Level.NONE
//        }
    }



    @OkHttpUploadFile
    @Provides
    fun provideUploadOkHttpClient(
        authenticator: okhttp3.Authenticator,
        @LoggingInterceptor logging: Interceptor,
        @TokenInterceptor header: Interceptor
    ): OkHttpClient =
        OkHttpClientProvider.getUploadOkHttpClientBuilder(logging, header, authenticator).build()



    @OkHttpAuth
    @Provides
    fun provideOkHttpClient(
        authenticator: okhttp3.Authenticator,
        @LoggingInterceptor logging: Interceptor,
        @TokenInterceptor header: Interceptor
    ): OkHttpClient =
        OkHttpClientProvider.getOkHttpClientBuilder(logging, header, authenticator).build()


    @OkHttpRefreshAuth
    @Provides
    fun provideOkHttpRefreshAuth(
        @LoggingInterceptor logging: Interceptor,
        @RefreshTokenInterceptor header: Interceptor
    ): OkHttpClient = OkHttpClientProvider.getOkHttpClientBuilder(logging, header).build()



    @RetrofitAuth
    @Provides
    fun provideRetrofit(
        @EndpointInfo baseUrl: String,
        @OkHttpAuth okHttpClient: OkHttpClient,
        // @GsonConverter converterFactory1: Converter.Factory,
        @MoshiConverter converterFactory2: Converter.Factory,
    ): Retrofit = RetrofitProvider
        .getRetrofitBuilder(baseUrl, okHttpClient, converterFactory2)
        .build()

    @RetrofitUploadFile
    @Provides
    fun provideRetrofitUploadFile(
        @EndpointInfo baseUrl: String,
        @OkHttpUploadFile okHttpClient: OkHttpClient,
        @MoshiConverter converterFactory: Converter.Factory,
    ): Retrofit = RetrofitProvider
        .getRetrofitBuilder(baseUrl, okHttpClient, converterFactory)
        .build()

    @RetrofitRefreshAuth
    @Provides
    fun provideRetrofitRefreshAuth(
        @EndpointInfo baseUrl: String,
        @OkHttpRefreshAuth okHttpClient: OkHttpClient,
        @MoshiConverter converterFactory: Converter.Factory,
    ): Retrofit = RetrofitProvider
        .getRetrofitBuilder(baseUrl, okHttpClient, converterFactory)
        .build()




}