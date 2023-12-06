package com.vismijatech.instantpost.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepositoryImplementation(auth: FirebaseAuth, databaseReference: DatabaseReference, uid: String): AuthRepository {
        return AuthRepositoryImplementation(auth = auth, databaseRef = databaseReference, id = uid)
    }

    @Provides
    @Singleton
    fun providesFirebaseDatabaseRef() = FirebaseDatabase.getInstance().getReference("users")

    @Provides
    @Singleton
    fun providesUserUid() = FirebaseAuth.getInstance().currentUser?.uid.toString()
}