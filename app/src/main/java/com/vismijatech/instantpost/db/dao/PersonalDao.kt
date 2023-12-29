package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.PersonalContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface PersonalDao {
    @Insert
    suspend fun insertPersonalContent(personalContent: PersonalContent)

    @Delete
    suspend fun deletePersonalContent(personalContent: PersonalContent)

    @Query("SELECT * FROM PersonalContent")
    fun getPersonalContents(): Flow<List<PersonalContent>>
}