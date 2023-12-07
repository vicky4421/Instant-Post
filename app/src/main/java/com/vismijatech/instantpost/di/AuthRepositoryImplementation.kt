package com.vismijatech.instantpost.di

import android.util.Log
import androidx.navigation.NavHostController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.user.IPUser
import com.vismijatech.instantpost.utils.firebaseresource.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
class AuthRepositoryImplementation @Inject constructor(
    private val auth: FirebaseAuth,
    private val databaseRef: DatabaseReference,
    private val id: String
) : AuthRepository {

    override fun getUser(uid: String): Flow<IPUser?> = callbackFlow {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(object : GenericTypeIndicator<Map<String, Any>>() {})

                if (data != null) {
                    // this is because we've inconsistent fields in db, if there's no field we assign a default value
                    val user = IPUser(
                        userId = uid,
                        username = data["name"] as? String ?: "",
                        credits = (data["credit"] as? Long)?.toInt() ?: 0,
                        imageUrl = data["imageUrl"] as? String ?: "",
                        email = data["email"] as? String ?: ""
                    )
                    trySend(element = user)
                } else {
                    trySend(element = null)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(cause = error.toException())
            }
        }
        databaseRef.child(uid).addValueEventListener(eventListener)
        awaitClose { databaseRef.removeEventListener(eventListener) }
    }

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            val result = auth.signInWithEmailAndPassword(email, password).await()

            val user = auth.currentUser
            if (user != null && user.isEmailVerified) {
                emit(value = Resource.Success(data = result))
            } else {
                auth.signOut()
                emit(Resource.Error("Email not verified. Please check your email for a verification link."))
            }
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

                if (task.isSuccessful) {
                    auth.currentUser?.sendEmailVerification()
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
                val uid = "${auth.currentUser?.uid}"
                val credit = 10
                val mail = auth.currentUser?.email.toString()
                val imageUrl = auth.currentUser?.photoUrl.toString()
                val name = auth.currentUser?.displayName

                // Check if the user already exists in database using the email address
                databaseRef.child(uid).get().addOnCompleteListener { snapshot ->
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
                            navController.popBackStack()
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