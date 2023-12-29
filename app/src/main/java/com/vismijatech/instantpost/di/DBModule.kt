package com.vismijatech.instantpost.di

import android.content.Context
import androidx.room.Room
import com.vismijatech.instantpost.db.database.InstantPostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@InstallIn(SingletonComponent::class)
@Module
class DBModule {
    @Singleton
    @Provides
    fun providesDatabase(context: Context): InstantPostDatabase{
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = InstantPostDatabase::class.java,
            name = "instantPostDB"
        ).build()
    }
}