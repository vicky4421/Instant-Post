package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.EmailContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface EmailDao {
    @Insert
    suspend fun insertEmailContent(emailContent: EmailContent)

    @Delete
    suspend fun deleteEmailContent(emailContent: EmailContent)

    @Query("SELECT * FROM EmailContents")
    fun getEmailContents(): Flow<List<EmailContent>>
}