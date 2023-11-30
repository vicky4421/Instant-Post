package com.vismijatech.instantpost.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.repository.firebaseAuthRepository.AuthRepository
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
class SignupViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun signUpUser(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUser(email = email, password = password).collect{result ->
            when(result){
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Signup successful"))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))
                }
                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message))
                }
            }
        }
    }
}