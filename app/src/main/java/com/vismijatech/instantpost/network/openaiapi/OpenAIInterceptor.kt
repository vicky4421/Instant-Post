package com.vismijatech.instantpost.network.openaiapi

import android.util.Log
import com.vismijatech.instantpost.data.OPENAI_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Vivek Sawant on 25-12-2023.
 */
class OpenAIInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $OPENAI_API_KEY")
            .build()

        // log headers
        val headers = request.headers
        for (name in headers.names()){
            val value = headers[name]
            Log.d("Interceptor", "header: $name: $value")
        }

        // Proceed with the request
        return chain.proceed(request)
    }

}