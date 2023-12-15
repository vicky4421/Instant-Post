package com.vismijatech.instantpost.screens.login

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.InputText
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Vivek Sawant on 27-11-2023.
 */
@Composable
fun LoginTextInput(email: MutableState<String>) {
    val validEmail = isValidEmail(email = email.value)
    InputText(
        valueState = email,
        label = stringResource(id = R.string.loginScreen_email_label),
        trailingIcon = { },
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Email,
        isError = if (email.value.isEmpty()) false else !validEmail,
        supportingText = {
            if (email.value.isEmpty()) {
                Text(text = "")
            } else if (!validEmail) Text(
                text = stringResource(id = R.string.loginScreen_invalidEmail_label),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
        }
    ) {
        // on imeAction

    }
}

@Composable
fun PasswordTextInput(password: MutableState<String>) {
    val validPassword = isValidPassword(password.value) && isContainsSymbol(password.value)
    val containsSymbol = isContainsSymbol(password.value)
    val atLeastOneUpperCase = hasOneUpperCaseLetter(password.value)
    val atLeastOneLowerCase = hasOneLowerCaseLetter(password.value)
    val atLeastOneNumber = hasOneNumber(password.value)
    val showPassword = rememberSaveable {
        mutableStateOf(false)
    }
    InputText(
        valueState = password,
        label = stringResource(id = R.string.loginScreen_password_label),
        trailingIcon = {
            IconButton(onClick = { showPassword.value = !showPassword.value }) {
                Icon(
                    painter = painterResource(id = if (showPassword.value) R.drawable.ic_visible else R.drawable.ic_invisible),
                    contentDescription = "Show/Hide password"
                )
            }
        },
        imeAction = ImeAction.Done,
        isError = if (password.value.isEmpty()) false else !validPassword,
        supportingText = {
            if (password.value.isEmpty()) Text(text = "")
            else if (password.value.length < 6) Text(
                text = stringResource(id = R.string.loginScreen_minCharNeeded_supportingText),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (password.value.length > 15) Text(
                text = stringResource(id = R.string.loginScreen_charLimitExceeded_supportingText),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!atLeastOneUpperCase) Text(
                text = stringResource(id = R.string.loginScreen_mustUpperCase_supportingText),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!atLeastOneLowerCase) Text(
                text = stringResource(id = R.string.loginScreen_mustLowerCase_supportingText),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!atLeastOneNumber) Text(
                text = stringResource(id = R.string.loginScreen_mustANumber_supportingText),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!containsSymbol) Text(
                text = stringResource(id = R.string.loginScreen_mustSpecialChar_supportingText),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
        },
        keyboardType = KeyboardType.Password,
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
    ) {
        // onImeAction / onTrailingIconClick
    }
}

@Composable
fun LoginButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .fillMaxWidth()
            .height(height = 50.dp),
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = R.string.loginScreen_logIn_label),
            style = TextStyle(fontFamily = PoppinsFontFamily)
        )
    }
}

@Composable
fun ForgotPasswordText(
    showEmailInput: Boolean,
    forgotEmail: MutableState<String>,
    auth: FirebaseAuth,
    context: Context,
    scope: CoroutineScope,
    onEndIconClick: () -> Unit,
    onClick: () -> Unit
) {
    Text(
        text = stringResource(id = R.string.loginScreen_forgotPassword_label),
        style = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold),
        modifier = Modifier
            .clickable { onClick() }
            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
    )
    AnimatedVisibility(visible = showEmailInput) {
        ForgotPassword(
            forgotEmail = forgotEmail,
            auth = auth,
            context = context,
            scope = scope
        ){
            onEndIconClick()
        }
    }
}

@Composable
fun SendVerificationEmail(onClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.loginScreen_resendVerificationLink_label),
        style = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Light),
        modifier = Modifier
            .clickable { onClick() }
            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
    )
}

@Composable
fun LoginWithGoogle(
    expanded: Boolean,
    onGoogleClick: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .clickable { onGoogleClick() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.loginScreen_signInWith_label) + " ",
                style = TextStyle(fontFamily = PoppinsFontFamily),
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
            AnnotatedGoogleText()
        }
        AnimatedVisibility(visible = expanded) {
            OutlinedButton(onClick = { onClick() }, modifier = Modifier.padding(top = 20.dp)) {
                Text(text = stringResource(id = R.string.loginScreen_googleSignIn_label))
            }
        }

    }
}

@Composable
fun AnnotatedGoogleText() {
    val textsize = 25.sp
    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color(color = 0xFF4285F4),
                fontSize = textsize
            )
        ) {
            append(text = "G")
        }
        withStyle(
            style = SpanStyle(
                color = Color(color = 0xFFDB4437),
                fontSize = textsize
            )
        ) {
            append(text = "o")
        }
        withStyle(
            style = SpanStyle(
                color = Color(color = 0xFFF4B400),
                fontSize = textsize
            )
        ) {
            append(text = "o")
        }
        withStyle(
            style = SpanStyle(
                color = Color(color = 0xFF4285F4),
                fontSize = textsize
            )
        ) {
            append(text = "g")
        }
        withStyle(
            style = SpanStyle(
                color = Color(color = 0xFF0F9D58),
                fontSize = textsize
            )
        ) {
            append(text = "l")
        }
        withStyle(
            style = SpanStyle(
                color = Color(color = 0xFFDB4437),
                fontSize = textsize
            )
        ) {
            append(text = "e")
        }
    })
}

@Composable
fun ForgotPassword(
    forgotEmail: MutableState<String>,
    auth: FirebaseAuth,
    context: Context,
    scope: CoroutineScope,
    onClick: () -> Unit
) {
    val validEmail = isValidEmail(email = forgotEmail.value)
    InputText(
        valueState = forgotEmail,
        label = stringResource(id = R.string.loginScreen_email_label),
        trailingIcon = {
            IconButton(onClick = {
                resetPassword(
                    auth = auth,
                    context = context,
                    scope = scope,
                    email = forgotEmail.value
                )
                onClick()
            }) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "Send")
            }
        },
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Email,
        isError = if (forgotEmail.value.isEmpty()) false else !validEmail,
        supportingText = {
            if (forgotEmail.value.isEmpty()) {
                Text(text = "")
            } else if (!validEmail) Text(
                text = stringResource(id = R.string.loginScreen_invalidEmail_label),
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
        }
    ) {
        // on imeAction
    }
}
