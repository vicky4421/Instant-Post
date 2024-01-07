package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.vismijatech.instantpost.db.entities.QueryHistory

/**
 * Created by Vivek Sawant on 07-01-2024.
 */
@Dao
interface QueryHistoryDao {
    @Insert
    suspend fun insertQueryHistory(queryHistory: QueryHistory)

    @Delete
    suspend fun deleteQueryHistory(queryHistory: QueryHistory)
}