package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.BusinessContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface BusinessDao {
    @Insert
    suspend fun insertBusinessContent(businessContent: BusinessContent)

    @Delete
    suspend fun deleteBusinessContent(businessContent: BusinessContent)

    @Query("SELECT * FROM BusinessContent")
    fun getBusinessContents(): Flow<List<BusinessContent>>
}