package com.vismijatech.instantpost.user

/**
 * Created by Vivek Sawant on 29-11-2023.
 */
data class IPUser(
     var userId: String = "",
     var username: String? = "",
     var credits: Int = 0,
     var imageUrl: String? = null,
     var email: String? = ""
)