package com.vismijatech.instantpost.screens.signup

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
class SignupViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()


    fun signUpUser(email: String, password: String, name: String) = viewModelScope.launch {
        authRepository.registerUser(email = email, password = password, name = name)
            .collect { result ->
                when (result) {
                    is Resource.Success -> {
                        // create new user
                        _signUpState.send(SignUpState(isSuccess = "success"))
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