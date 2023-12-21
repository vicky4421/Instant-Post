package com.vismijatech.instantpost.model.queries

/**
 * Created by Vivek Sawant on 13-12-2023.
 */
data class WhatsappReplyQuery(
    val messageContent: String,
    val sendersTone: String,
    val relationship: String,
    val goal: String,
    val yourTone: String,
    val occasion: String,
    val specialInstructions: String,
    val length: String
)
