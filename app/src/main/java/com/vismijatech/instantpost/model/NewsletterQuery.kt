package com.vismijatech.instantpost.model

/**
 * Created by Vivek Sawant on 13-12-2023.
 */
data class NewsletterQuery(
    val themeOrTopic: String,
    val title: String,
    val sectionAndContents: String,
    val callToAction: String,
    val links: String,
    val subjectLine: String,
    val tone: String,
    val personalization: String,
    val frequency: String,
    val length: String
)
