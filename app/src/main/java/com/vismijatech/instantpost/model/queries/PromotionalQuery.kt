package com.vismijatech.instantpost.model.queries

/**
 * Created by Vivek Sawant on 13-12-2023.
 */
data class PromotionalQuery(
    val targetAudience: String,
    val product: String,
    val keyPoints: String,
    val callToAction: String,
    val discounts: String,
    val subjectLine: String,
    val personalization: String,
    val tone: String,
    val closing: String,
    val length: String
)
