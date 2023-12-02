package com.vismijatech.instantpost.screens.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import com.vismijatech.instantpost.utils.getGoogleSignInClient
import com.vismijatech.instantpost.utils.isValidEmail
import com.vismijatech.instantpost.utils.isValidPassword

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){
        val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val result = account.getResult(ApiException::class.java)
            val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
            viewModel.googleLogIn(credentials)
        } catch (it: ApiException){
            it.printStackTrace()
        }
    }
    val googleSignInState = viewModel.googleState.value
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val expanded = rememberSaveable {
        mutableStateOf(false)
    }
    val validEmail = isValidEmail(email.value)
    val validPassword = isValidPassword(password.value)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
            Spacer(modifier = Modifier.height(10.dp))
            LoginTextInput(email = email)
            PasswordTextInput(password = password)
            LoginButton(label = "Login",
                enabled = email.value.isNotEmpty() && password.value.isNotEmpty() && validEmail && validPassword
            ) {
                // check if the email is verified.
                if (auth.currentUser?.isEmailVerified == true){
                    navController.navigate(InstantPostScreen.DashboardScreen.name)
                } else {
                    Toast.makeText(context, "Please verify your email", Toast.LENGTH_LONG).show()
                }
            }
            ForgotPasswordText { }
            SendVerificationEmail {
                if (auth.currentUser != null){
                    auth.currentUser?.sendEmailVerification()
                    Toast.makeText(context, "Email verification link sent", Toast.LENGTH_LONG).show()
                } else if (auth.currentUser == null && email.value.isEmpty()){
                    Toast.makeText(context, "Please enter email id", Toast.LENGTH_LONG).show()
                }
//                else if (auth.currentUser == null && email.value.isNotEmpty()){
                   // TODO: implement after implementing realtime database
//                }

            }
            LoginWithGoogle(
                expanded = expanded.value,
                onGoogleClick = {expanded.value = !expanded.value}
            ) {
                val googleSignInClient = getGoogleSignInClient(context)
                launcher.launch(googleSignInClient.signInIntent)
            }
            Box(
                modifier = Modifier.padding(top = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                if (googleSignInState.loading) LinearProgressIndicator()
                else if (googleSignInState.success?.toString()?.isNotEmpty() == true) {
                    navController.popBackStack()
                    navController.navigate(InstantPostScreen.DashboardScreen.name)
                }
                else if (googleSignInState.error.isNotEmpty()) Text(
                    text = googleSignInState.error,
                    style = TextStyle(fontFamily = PoppinsFontFamily, textAlign = TextAlign.Center)
                )
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
            CreateNewAccountButton(label = "Create new account") {
                navController.navigate(InstantPostScreen.SignupScreen.name)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}