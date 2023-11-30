package com.vismijatech.instantpost.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.InputText
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import com.vismijatech.instantpost.utils.hasOneLowerCaseLetter
import com.vismijatech.instantpost.utils.hasOneNumber
import com.vismijatech.instantpost.utils.hasOneUpperCaseLetter
import com.vismijatech.instantpost.utils.isContainsSymbol
import com.vismijatech.instantpost.utils.isValidEmail
import com.vismijatech.instantpost.utils.isValidPassword

/**
 * Created by Vivek Sawant on 27-11-2023.
 */

@Composable
fun LoginTextInput(email: MutableState<String>) {
    val validEmail = isValidEmail(email = email.value)
    InputText(
        valueState = email,
        label = "email",
        trailingIcon = { },
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Email,
        isError = if (email.value.isEmpty()) false else !validEmail,
        supportingText = {
            if (email.value.isEmpty()) {
                Text(text = "")
            } else if (!validEmail) Text(
                text = "Invalid email!",
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
        label = "password",
        trailingIcon = {
            IconButton(onClick = { showPassword.value = !showPassword.value }, modifier = Modifier.scale(2f)) {
                Icon(
                    painter = painterResource(id = if (showPassword.value) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off),
                    contentDescription = "Show/Hide password"
                )
            }
        },
        imeAction = ImeAction.Done,
        isError = if (password.value.isEmpty()) false else !validPassword,
        supportingText = {
            if (password.value.isEmpty()) Text(text = "")
            else if (password.value.length < 6) Text(
                text = "Min 6 characters needed!",
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (password.value.length > 15) Text(
                text = "Character limit exceeded!",
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!atLeastOneUpperCase) Text(
                text = "Must have a upper case character!",
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!atLeastOneLowerCase) Text(
                text = "Must have a lower case character!",
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!atLeastOneNumber) Text(
                text = "Must have a number!",
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
            else if (!containsSymbol) Text(
                text = "Must have on special character!",
                style = TextStyle(fontFamily = PoppinsFontFamily)
            )
        },
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
    ) {
        // onImeAction / onTrailingIconClick
    }
}

@Composable
fun LoginButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .fillMaxWidth()
            .height(height = 50.dp)
    ) {
        Text(text = label, style = TextStyle(fontFamily = PoppinsFontFamily))
    }
}

@Composable
fun ForgotPasswordText(onClick: () -> Unit) {
    Text(
        text = "Forgot password?",
        style = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold),
        modifier = Modifier
            .clickable { onClick() }
            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
    )
}

@Composable
fun CreateNewAccountButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 60.dp)
            .padding(paddingValues = PaddingValues(10.dp))
    ) {
        Text(
            text = label,
            style = TextStyle(fontFamily = PoppinsFontFamily)
        )
    }
}

@Composable
fun LoginWithGoogle(expanded: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sign in with ",
                style = TextStyle(fontFamily = PoppinsFontFamily),
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
            AnnotatedGoogleText()
        }
        AnimatedVisibility(visible = expanded) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(top = 10.dp)) {
                Text(text = "Google sign in")
            }
        }

    }
}

@Preview
@Composable
fun LoginButtonPreview() {
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