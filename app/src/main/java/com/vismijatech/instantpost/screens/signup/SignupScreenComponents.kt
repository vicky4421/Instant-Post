package com.vismijatech.instantpost.screens.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.vismijatech.instantpost.components.InputText
import com.vismijatech.instantpost.screens.login.CreateNewAccountButton
import com.vismijatech.instantpost.screens.login.LoginButton
import com.vismijatech.instantpost.screens.login.LoginTextInput
import com.vismijatech.instantpost.screens.login.PasswordTextInput

/**
 * Created by Vivek Sawant on 30-11-2023.
 */

@Composable
fun SignupNameInput(name: MutableState<String>) {
    val validName = isValidName(name.value)
    val nameInRange = isNameInRange(name.value)
//    val containsNumberOrSpace = isContainsNumberOrSpace(name.value)
    InputText(
        valueState = name,
        label = "Full name",
        trailingIcon = { /**/ },
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Text,
        isError = !validName,
        supportingText = {
            if (name.value.isEmpty()) Text(text = "")
            else if (!nameInRange) Text(text = "Full name should be between 2 and 50 characters!")
        }
    ) {
        // Ime Action
    }
}

@Composable
fun SignupEmailInput(email: MutableState<String>) {
    LoginTextInput(email = email)
}

@Composable
fun SignupPasswordInput(password: MutableState<String>) {
    PasswordTextInput(password = password)
}

@Composable
fun SignupSubmitButton(enabled: Boolean, onClick: () -> Unit) {
    LoginButton(label = "Sign Up", enabled = enabled) {
        onClick()
    }
}

@Composable
fun SignupAlreadyHaveAccount(onClick: () -> Unit) {
    CreateNewAccountButton(label = "Already have an account") {
        onClick()
    }
}
