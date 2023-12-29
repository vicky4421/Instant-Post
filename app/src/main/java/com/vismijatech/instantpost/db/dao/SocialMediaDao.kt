package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface SocialMediaDao {
    @Insert
    suspend fun insertSocialMediaContent(socialMediaContent: SocialMediaContent)

    @Delete
    suspend fun deleteSocialMediaContent(socialMediaContent: SocialMediaContent)

    @Query("SELECT * FROM SocialMediaContent")
    fun getSocialMediaContents(): Flow<List<SocialMediaContent>>
}