package com.vismijatech.instantpost.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseAuth
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.login.isValidEmail
import com.vismijatech.instantpost.screens.login.isValidPassword
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.launch

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun SignupScreen(
    navController: NavHostController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val name = rememberSaveable {
        mutableStateOf("")
    }
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }

    val state = viewModel.signUpState.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    val validName = isValidName(name.value)
    val validEmail = isValidEmail(email.value)
    val validPassword = isValidPassword(password.value)
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.ic_logo)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "App Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(size = 100.dp)
//                    .clip(shape = CircleShape)
            )
            SignupNameInput(name = name)
            SignupEmailInput(email = email)
            SignupPasswordInput(password = password)
            SignupSubmitButton(
                enabled = name.value.isNotEmpty() &&
                        password.value.isNotEmpty() &&
                        email.value.isNotEmpty() &&
                        validName &&
                        validEmail &&
                        validPassword
            ) {
                scope.launch {
                    viewModel.signUpUser(
                        email = email.value,
                        password = password.value,
                        name = name.value
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center
            ) {
                if (state.value?.isLoading == true) LinearProgressIndicator()
                else if (state.value?.isSuccess?.isNotEmpty() == true) navController.navigate(
                    InstantPostScreen.LoginScreen.name
                )
                else if (state.value?.isError?.isNotEmpty() == true) Text(
                    text = state.value?.isError!!,
                    style = TextStyle(fontFamily = PoppinsFontFamily, textAlign = TextAlign.Center)
                )
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
            SignupAlreadyHaveAccount {
                navController.navigate(InstantPostScreen.LoginScreen.name)
            }
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen(navController = rememberNavController())
}