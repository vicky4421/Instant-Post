package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.NewsletterContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface NewsletterDao {
    @Insert
    suspend fun insertNewsletterContent(newsletterContent: NewsletterContent)

    @Delete
    suspend fun deleteNewsletterContent(newsletterContent: NewsletterContent)

    @Query("SELECT * FROM NewsletterContent")
    fun getNewsletterContents(): Flow<List<NewsletterContent>>
}