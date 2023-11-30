package com.vismijatech.instantpost.screens.login

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
