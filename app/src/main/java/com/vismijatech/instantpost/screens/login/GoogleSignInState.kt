package com.vismijatech.instantpost.screens.login

import com.google.firebase.auth.AuthResult

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
data class GoogleSignInState(
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String = ""
)
