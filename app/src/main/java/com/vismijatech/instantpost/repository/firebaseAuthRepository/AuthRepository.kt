package com.vismijatech.instantpost.repository.firebaseAuthRepository

import com.google.firebase.auth.AuthResult
import com.vismijatech.instantpost.utils.firebaseresource.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
}