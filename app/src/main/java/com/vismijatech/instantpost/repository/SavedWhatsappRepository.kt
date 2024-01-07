package com.vismijatech.instantpost.repository

import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.WhatsappContent
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
class SavedWhatsappRepository @Inject constructor(private val db: InstantPostDatabase) {
    private val whatsappDao = db.whatsappDao()

    suspend fun insertWhatsappContent(whatsappContent: WhatsappContent) = whatsappDao.insertWhatsappContent(whatsappContent = whatsappContent)
    suspend fun deleteWhatsappContent(whatsappContent: WhatsappContent) = whatsappDao.deleteWhatsappContent(whatsappContent = whatsappContent)
    fun getWhatsappContents() = whatsappDao.getWhatsappContents()
}