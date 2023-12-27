package com.vismijatech.instantpost.model.openai

/**
 * Created by Vivek Sawant on 24-12-2023.
 */
data class OpenAiRequest(
    val messages: List<Message>,
    val model: String
)
