package com.vismijatech.instantpost.model.openai

data class Usage(
    val completionTokens: Int,
    val promptTokens: Int,
    val totalTokens: Int
)