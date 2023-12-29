package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.WhatsappContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface WhatsappDao {
    @Insert
    suspend fun insertWhatsappContent(whatsappContent: WhatsappContent)

    @Delete
    suspend fun deleteWhatsappContent(whatsappContent: WhatsappContent)

    @Query("SELECT * FROM WhatsappContent")
    fun getWhatsappContents(): Flow<List<WhatsappContent>>
}