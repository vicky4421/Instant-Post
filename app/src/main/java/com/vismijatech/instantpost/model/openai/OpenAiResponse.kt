package com.vismijatech.instantpost.model.openai

data class OpenAiResponse(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val usage: Usage
)