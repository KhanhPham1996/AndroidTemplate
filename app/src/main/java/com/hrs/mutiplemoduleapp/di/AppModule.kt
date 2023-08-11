package com.hrs.mutiplemoduleapp.di

import android.content.Context
import com.hrs.mutiplemoduleapp.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun providesApplicationInstance(@ApplicationContext context: Context): MyApplication {
        return context as MyApplication
    }
}
