package com.vismijatech.instantpost.user

/**
 * Created by Vivek Sawant on 29-11-2023.
 */
data class IPUser(
    private val userId: String,
    private val username: String?,
    private val credits: Int?,
    private val imageUrl: String?,
    private val nativeLanguage: String?
)
