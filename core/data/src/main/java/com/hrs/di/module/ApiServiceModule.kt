package com.hrs.di.module

import com.hrs.di.scopes.RetrofitAuth
import com.hrs.di.scopes.RetrofitRefreshAuth
import com.hrs.fuction.login.network.AuthServiceApi
import com.hrs.fuction.login.network.LoginUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideApiUserService(@RetrofitAuth retrofit: Retrofit): LoginUserService =
        retrofit.create(LoginUserService::class.java)

    @Provides
    fun provideAuthApiService(@RetrofitRefreshAuth retrofit: Retrofit): AuthServiceApi =
        retrofit.create(AuthServiceApi::class.java)
}