package com.vismijatech.instantpost.di

import androidx.navigation.NavHostController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.vismijatech.instantpost.user.IPUser
import com.vismijatech.instantpost.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<NetworkResult<AuthResult>>
    fun registerUser(email: String, password: String, name: String): Flow<NetworkResult<AuthResult>>
    fun googleSignIn(credential: AuthCredential, navController: NavHostController): Flow<NetworkResult<AuthResult>>
    fun getUser(uid: String): Flow<IPUser?>
}