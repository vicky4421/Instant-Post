package com.vismijatech.instantpost.screens.signup

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
