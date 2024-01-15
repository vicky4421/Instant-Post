package com.vismijatech.instantpost.user

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by Vivek Sawant on 11-01-2024.
 */
class UserUtils() {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance()
    private val currentUser = auth.currentUser
    private val uid = currentUser?.uid.toString()
    private val dbRef = db.getReference("users").child(uid)


    suspend fun addUserCredit(credit: Int) {
        dbRef.child("credit").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentCredits = snapshot.getValue(Int::class.java) ?: 0
                val updatedCredits = currentCredits + credit
                dbRef.child("credit").setValue(updatedCredits)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("addCredit", "onCancelled: ${error.message}")
            }
        })
    }

    suspend fun costUserCredit(credit: Int) {
        var message = ""
        dbRef.child("credit").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentCredits = snapshot.getValue(Int::class.java) ?: 0
                val updatedCredits = currentCredits - credit
                dbRef.child("credit").setValue(updatedCredits)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("costCredit", "onCancelled: ${error.message}")
            }

        })
    }
}