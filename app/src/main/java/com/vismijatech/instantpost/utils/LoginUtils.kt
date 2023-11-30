package com.vismijatech.instantpost.utils

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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

