package com.vismijatech.instantpost.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.di.AuthRepository
import com.vismijatech.instantpost.user.IPUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 05-12-2023.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _userData = MutableStateFlow<IPUser?>(value = null)
    val userData: StateFlow<IPUser?> = _userData

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getUserData(uid: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                authRepository.getUser(uid).collect { user ->
                    _userData.value = user
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}