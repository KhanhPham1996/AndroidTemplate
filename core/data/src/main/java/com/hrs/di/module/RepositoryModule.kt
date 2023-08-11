package com.hrs.di.module

import com.hrs.fuction.login.data.Authenticator
import com.hrs.fuction.login.network.LoginUserService
import com.hrs.fuction.login.repository.LoginRepository
import com.hrs.fuction.login.repository.LoginRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository( apiService: LoginUserService,authenticator: Authenticator): LoginRepository =
        LoginRepositoryImp(apiService, authenticator)
}