package com.vismijatech.instantpost.repository

import android.util.Log
import com.vismijatech.instantpost.data.GPT_MODEL
import com.vismijatech.instantpost.data.TAG
import com.vismijatech.instantpost.model.openai.Choice
import com.vismijatech.instantpost.model.openai.Message
import com.vismijatech.instantpost.model.openai.OpenAiRequest
import com.vismijatech.instantpost.model.openai.OpenAiResponse
import com.vismijatech.instantpost.model.openai.Usage
import com.vismijatech.instantpost.network.openaiapi.OpenAIApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 24-12-2023.
 */
class OpenAiRepository @Inject constructor(private val openAIApi: OpenAIApi) {

    private val _openAIResponse = MutableStateFlow<OpenAiResponse?>(value = null)
    val openAiResponse: StateFlow<OpenAiResponse?>
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
                    response.body()?.let { openaiResponse ->
                        _openAIResponse.emit(openaiResponse)
                    }
                } else {
                    Log.d(TAG, "getResponse: ${response.errorBody().toString()}")
                }
            }
        } catch (e: SocketTimeoutException) {
            _openAIResponse.emit(
                OpenAiResponse(
                    choices = listOf(
                        Choice(
                            index = 0,
                            message = Message(content = "Timeout! Please try again!,\nyour credits are refunded!", role = "System")
                        )
                    ),
                    created = 0,
                    id = "0",
                    usage = Usage(completionTokens = 0, promptTokens = 0, totalTokens = 0)
                )
            )
            // Refund user credits.
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}