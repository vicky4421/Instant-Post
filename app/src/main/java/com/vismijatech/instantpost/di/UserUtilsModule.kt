package com.vismijatech.instantpost.di

import com.vismijatech.instantpost.user.UserUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vivek Sawant on 13-01-2024.
 */
@Module
@InstallIn(SingletonComponent::class)
class UserUtilsModule {
    @Provides
    @Singleton
    fun provideUserUtils(): UserUtils {
        return UserUtils()
    }
}