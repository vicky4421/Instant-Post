package com.vismijatech.instantpost.repository

import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.BlogContent
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
class SavedBlogRepository @Inject constructor(db: InstantPostDatabase) {
    private val blogDao = db.blogDao()

    suspend fun insertBlogContent(blogContent: BlogContent) = blogDao.insertBlogContent(blogContent = blogContent)
    suspend fun deleteBlogContent(blogContent: BlogContent) = blogDao.deleteBlogContent(blogContent = blogContent)
    fun getBlogContents() = blogDao.getBlogContents()
}