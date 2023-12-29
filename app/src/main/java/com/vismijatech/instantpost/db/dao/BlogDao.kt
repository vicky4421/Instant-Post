package com.vismijatech.instantpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vismijatech.instantpost.db.entities.BlogContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Dao
interface BlogDao {
    @Insert
    suspend fun insertBlogContent(blogContent: BlogContent)

    @Delete
    suspend fun deleteBlogContent(blogContent: BlogContent)

    @Query("SELECT * FROM BlogContents")
    fun getBlogContents(): Flow<List<BlogContent>>
}