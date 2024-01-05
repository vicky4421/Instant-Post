package com.vismijatech.instantpost.network.pexelapi

import android.util.Log
import com.vismijatech.instantpost.data.PEXEL_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Vivek Sawant on 04-01-2024.
 */
class PexelInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", PEXEL_API_KEY)
                .build()

            val headers = request.headers
            for (name in headers.names()){
                val value = headers[name]
                Log.d("Pexel Interceptor", "headers: $name : $value")
            }

            return chain.proceed(request)
        } catch (e: Exception){
            Log.d("Pexel Interceptor exception", "intercept: ${e.message}")
            e.printStackTrace()
        }
        return chain.proceed(chain.request())
    }
}