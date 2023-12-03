package com.vismijatech.instantpost.di

import android.util.Log
import androidx.navigation.NavHostController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.utils.firebaseresource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
class AuthRepositoryImplementation @Inject constructor(private val auth: FirebaseAuth) :
    AuthRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseRef = firebaseDatabase.getReference("users")
    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            val result = auth.signInWithEmailAndPassword(email, password).await()
            emit(value = Resource.Success(data = result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun registerUser(
        email: String,
        password: String,
        name: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            val result = authResult(email, password, name)
            emit(value = Resource.Success(data = result))
        }.catch {
            emit(value = Resource.Error(it.message.toString()))
        }
    }

    private suspend fun authResult(
        email: String,
        password: String,
        name: String
    ): AuthResult {
        val result =
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                val uid = task.result.user?.uid.toString()
                val credits = 10
                val imageUrl = task.result.user?.photoUrl.toString()
                val mail = task.result.user?.email.toString()

                databaseRef.child(uid)
                try {
                    databaseRef.child(uid).child("name").setValue(name)
                    databaseRef.child(uid).child("credit").setValue(credits)
                    databaseRef.child(uid).child("imageUrl").setValue(imageUrl)
                    databaseRef.child(uid).child("email").setValue(mail)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.d("firebase database", "signUpUser: ${e.message}")
                }
            }.await()
        return result
    }

    override fun googleSignIn(
        credential: AuthCredential,
        navController: NavHostController
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            val result = authResult1(credential, navController)
            emit(value = Resource.Success(data = result))
        }.catch {
            emit(value = Resource.Error(it.message.toString()))
        }
    }

    private suspend fun authResult1(
        credential: AuthCredential,
        navController: NavHostController
    ): AuthResult {
        val result = auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = currentUser?.uid.toString()
                val credit = 10
                val mail = currentUser?.email.toString()
                val imageUrl = currentUser?.photoUrl.toString()
                val name = currentUser?.displayName

                // Check if the user already exists in database using the email address
                databaseRef.get().addOnCompleteListener { snapshot ->
                    if (snapshot.isSuccessful && snapshot.result.exists()) {
                        // User is already exists, navigate to dashboard
                        navController.popBackStack()
                        navController.navigate(InstantPostScreen.DashboardScreen.name)
                    } else {
                        // User does not exist in the database, create a new entry.
                        try {
                            databaseRef.child(uid).child("credit").setValue(credit)
                            databaseRef.child(uid).child("email").setValue(mail)
                            databaseRef.child(uid).child("imageUrl").setValue(imageUrl)
                            databaseRef.child(uid).child("name").setValue(name)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Log.d("firebase database", "googleSignIn: ${e.message}")
                        }
                    }
                }
            }
        }.await()
        return result
    }

}