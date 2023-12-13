package com.vismijatech.instantpost.model

/**
 * Created by Vivek Sawant on 13-12-2023.
 */
data class PersonalQuery(
    val recipientsName: String,
    val relationship: String,
    val occasion: String,
    val purpose: String,
    val mainPoints: String,
    val positiveQualities: String,
    val emotionalTone: String,
    val closingSentiment: String
)
