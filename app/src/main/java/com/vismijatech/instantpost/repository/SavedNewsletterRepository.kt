package com.vismijatech.instantpost.repository

import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.NewsletterContent
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
class SavedNewsletterRepository @Inject constructor(private val db: InstantPostDatabase) {
    private val newsletterDao = db.newsletterDao()
    suspend fun insertNewsletterContent(newsletterContent: NewsletterContent) = newsletterDao.insertNewsletterContent(newsletterContent = newsletterContent)
    suspend fun deleteNewsletterContent(newsletterContent: NewsletterContent) = newsletterDao.deleteNewsletterContent(newsletterContent = newsletterContent)
    fun getNewsletterContents() = newsletterDao.getNewsletterContents()
}