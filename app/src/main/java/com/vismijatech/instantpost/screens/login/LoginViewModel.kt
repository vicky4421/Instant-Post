package com.vismijatech.instantpost.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.vismijatech.instantpost.di.AuthRepository
import com.vismijatech.instantpost.utils.firebaseresource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.s
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _signInState = Channel<LoginState>()
    val signInState = _signInState.receiveAsFlow()

    private val _googleState = mutableStateOf(GoogleSignInState())
    val googleState : State<GoogleSignInState> = _googleState

    fun googleLogIn(credential: AuthCredential) = viewModelScope.launch {
        authRepository.googleSignIn(credential).collect{
            authRepository.googleSignIn(credential).collect{result ->
                when(result){
                    is Resource.Success -> {
                        _googleState.value = GoogleSignInState(success = result.data)
                    }
                    is Resource.Loading -> {
                        _googleState.value = GoogleSignInState(loading = true)
                    }
                    is Resource.Error -> {
                        _googleState.value = GoogleSignInState(error = result.message.toString())
                    }
                }
            }
        }
    }

    fun logInUser(email: String, password: String) = viewModelScope.launch {
        authRepository.loginUser(email = email, password = password).collect{result ->
            when(result){
                is Resource.Success -> {
                    _signInState.send(LoginState(isSuccess = "Login success!"))
                }
                is Resource.Loading -> {
                    _signInState.send(LoginState(isLoading = true))
                }
                is Resource.Error -> {
                    _signInState.send(LoginState(isError = result.message))
                }
            }
        }
    }
}