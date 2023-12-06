package com.vismijatech.instantpost.di

import androidx.navigation.NavHostController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DatabaseReference
import com.vismijatech.instantpost.user.IPUser
import com.vismijatech.instantpost.utils.firebaseresource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String, name: String): Flow<Resource<AuthResult>>
    fun googleSignIn(credential: AuthCredential, navController: NavHostController): Flow<Resource<AuthResult>>
    fun getUser(uid: String): Flow<IPUser?>
}