package com.vismijatech.instantpost.network.openaiapi

import com.vismijatech.instantpost.model.openai.OpenAiRequest
import com.vismijatech.instantpost.model.openai.OpenAiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Vivek Sawant on 23-12-2023.
 */
interface OpenAIApi {
    @POST("chat/completions")
    suspend fun getResponse(
        @Body request: OpenAiRequest,
//        @Header("Content-Type") contentType: String = "application/json"
//        @Header("Authorization") authorization: String = "Bearer $OPENAI_API_KEY"
    ): Response<OpenAiResponse?>
}