package com.vismijatech.instantpost.model.queries

/**
 * Created by Vivek Sawant on 13-12-2023.
 */
data class SocialMediaQuery(
    val imageDescription: String,
    val emotions: String,
    val captionStyle: String,
    val keywords: String,
    val messageOrStory: String,
    val location: String,
    val callToAction: String,
    val tagline: String,
    val length: String,
    val goal: String,
    val targetAudience: String
)
