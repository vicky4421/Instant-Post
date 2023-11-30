package com.vismijatech.instantpost.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.di.AuthRepository
import com.vismijatech.instantpost.utils.firebaseresource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _signInState = Channel<LoginState>()
    val signInState = _signInState.receiveAsFlow()

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