package com.vismijatech.instantpost.model.queries

/**
 * Created by Vivek Sawant on 13-12-2023.
 */
data class BusinessQuery(
    val recipientsName: String,
    val purpose: String,
    val backgroundInfo: String,
    val professionalTone: String,
    val callToAction: String,
    val signature: String,
    val mainPoints: String,
    val closingSentiment: String
)
