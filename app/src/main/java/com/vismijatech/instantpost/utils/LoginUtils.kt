package com.vismijatech.instantpost.utils

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.vismijatech.instantpost.R

/**
 * Created by Vivek Sawant on 27-11-2023.
 */

// email validation
val emailRegex = Regex("^\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")
fun isValidEmail(email: String): Boolean = emailRegex.matches(email)

val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=(?:.*[\\W]){1,1}).{6,15}\$")
fun isValidPassword(password: String): Boolean = passwordRegex.matches(password)

//val symbolRegex = Regex(pattern = "[^A-Za-z0-9]*[^A-Za-z0-9][^A-Za-z0-9]*")
//fun isContainsSymbol(password: String): Boolean = symbolRegex.matches(password)
fun isContainsSymbol(password: String): Boolean {
    val specialCharacters = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?', '|')

    var specialCharacterCount = 0

    for (char in password) {
        if (specialCharacters.contains(char)) {
            specialCharacterCount++
            if (specialCharacterCount > 1) {
                return false
            }
        }
    }

    return specialCharacterCount == 1
}


val upperCaseRegex = Regex(pattern = ".*[A-Z].*")
fun hasOneUpperCaseLetter(password: String): Boolean = upperCaseRegex.matches(password)

val lowerCaseRegex = Regex(pattern = ".*[a-z].*")
fun hasOneLowerCaseLetter(password: String): Boolean = lowerCaseRegex.matches(password)

//val oneNumberRegex = Regex(pattern = "(?=.*\\d)")
//fun hasOneNumber(password: String): Boolean = oneNumberRegex.matches(password)

fun hasOneNumber(password: String): Boolean {
    for (char in password) {
        if (char.isDigit()) {
            return true
        }
    }
    return false
}

// sign in with google
fun getGoogleSignInOptions(context: Context): GoogleSignInOptions{
    return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(context.getString(R.string.web_client_id))
        .requestProfile()
        .requestId()
        .build()
}

fun getGoogleSignInClient(context: Context): GoogleSignInClient{
    val googleSignInOptions = getGoogleSignInOptions(context)
    return GoogleSignIn.getClient(context, googleSignInOptions)
}
