package com.raphael.lemon.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationDIModule {

    @Provides
    @Singleton
    fun provideDBManager(@ApplicationContext context: Context): DBManager {
        return DBManager(context, true)
    }
}