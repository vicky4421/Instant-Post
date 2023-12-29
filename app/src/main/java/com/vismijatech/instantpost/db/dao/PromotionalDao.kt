package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.PromotionalContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface PromotionalDao {
    @Insert
    suspend fun insertPromotionalContent(promotionalContent: PromotionalContent)

    @Delete
    suspend fun deletePromotionalContent(promotionalContent: PromotionalContent)

    @Query("SELECT * FROM PromotionalContent")
    fun getPromotionalContents(): Flow<List<PromotionalContent>>
}