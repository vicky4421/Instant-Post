package com.vismijatech.instantpost.di

import com.vismijatech.instantpost.data.PEXEL_URL
import com.vismijatech.instantpost.di.annotations.PexelOkHttpClientQualifier
import com.vismijatech.instantpost.di.annotations.PexelRetrofitQualifier
import com.vismijatech.instantpost.network.pexelapi.PexelApi
import com.vismijatech.instantpost.network.pexelapi.PexelInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Vivek Sawant on 04-01-2024.
 */
@InstallIn(SingletonComponent::class)
@Module
class PexelRetrofitModule {
    @Singleton
    @Provides
    fun providesPexelInterceptor(): PexelInterceptor{
        return PexelInterceptor()
    }

    @Singleton
    @PexelOkHttpClientQualifier
    @Provides
    fun providesOkHttpClient(pexelInterceptor: PexelInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(pexelInterceptor)
            .build()
    }

    @Singleton
    @PexelRetrofitQualifier
    @Provides
    fun providesPexelRetrofit(@PexelOkHttpClientQualifier okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(PEXEL_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @PexelRetrofitQualifier
    @Provides
    fun providesPexelApi(@PexelRetrofitQualifier retrofit: Retrofit): PexelApi{
        return retrofit.create(PexelApi::class.java)
    }
}