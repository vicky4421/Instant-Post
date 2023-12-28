package com.vismijatech.instantpost.repository

import android.util.Log
import com.vismijatech.instantpost.data.GPT_MODEL
import com.vismijatech.instantpost.data.TAG
import com.vismijatech.instantpost.model.openai.Message
import com.vismijatech.instantpost.model.openai.OpenAiRequest
import com.vismijatech.instantpost.network.openaiapi.OpenAIApi
import com.vismijatech.instantpost.network.openaiapi.OpenAIInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 24-12-2023.
 */
class OpenAiRepository @Inject constructor(private val openAIApi: OpenAIApi) {

    private val _openAIResponse = MutableStateFlow<Message?>(value = null)
    val openAiResponse: StateFlow<Message?>
        get() = _openAIResponse

    suspend fun getResponse(prompt: String) {
        try {
            val openAiRequest = OpenAiRequest(
                arrayListOf(
                    Message(
                        content = "Response",
                        role = "system"
                    ),
                    Message(
                        content = prompt,
                        role = "user"
                    )
                ), GPT_MODEL
            )
            val response = openAIApi.getResponse(openAiRequest)

            if (response.isSuccessful && response.body() != null) {
                if (response.code() == 200) {
                    response.body()?.choices?.first()?.message.let {
                        _openAIResponse.emit(it)
                    }
                } else {
                    Log.d(TAG, "getResponse: ${response.errorBody().toString()}")
                }
            }
        } catch (e: SocketTimeoutException) {
            _openAIResponse.emit(
                Message(
                    content = "Timeout! Please try again!,\nyour credits are refunded!",
                    role = "user"
                )
            )
            // Refund user credits.
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}