package com.hrs.di.module

import com.google.gson.Gson
import com.hrs.di.provider.JsonBuilderProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.brickmate.apps.internal.data.di.scopes.MoshiNetworkAdapter
import kr.brickmate.apps.internal.data.di.scopes.MoshiUserAdapter

@Module
@InstallIn(SingletonComponent::class)
class JsonModule {

    @Provides
    fun provideGson(): Gson = JsonBuilderProvider.gsonBuilder.create()

    @MoshiNetworkAdapter
    @Provides
    fun provideNetworkMoshi(): Moshi = JsonBuilderProvider.moshiNetworkBuilder.build()

    @MoshiUserAdapter
    @Provides
    fun provideUserMoshi(): Moshi = JsonBuilderProvider.moshiUserBuilder.build()
}