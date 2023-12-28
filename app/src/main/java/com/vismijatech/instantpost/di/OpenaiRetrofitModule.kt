package com.vismijatech.instantpost.di

import com.vismijatech.instantpost.data.OPENAI_URL
import com.vismijatech.instantpost.network.openaiapi.OpenAIApi
import com.vismijatech.instantpost.network.openaiapi.OpenAIInterceptor
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
 * Created by Vivek Sawant on 24-12-2023.
 */
@InstallIn(SingletonComponent::class)
@Module
class OpenaiRetrofitModule {

    @Singleton
    @Provides
    fun providesOpenAIInterceptor(): OpenAIInterceptor {
        return OpenAIInterceptor()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(openAIInterceptor: OpenAIInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(openAIInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(OPENAI_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesOpenAIApi(retrofit: Retrofit): OpenAIApi{
        return retrofit.create(OpenAIApi::class.java)
    }
}