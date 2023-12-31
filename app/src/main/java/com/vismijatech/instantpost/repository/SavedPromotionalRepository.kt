package com.vismijatech.instantpost.repository

import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.PromotionalContent
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
class SavedPromotionalRepository @Inject constructor(private val db: InstantPostDatabase) {
    private val promotionalDao = db.promotionalDao()
    suspend fun insertPromotionalContent(promotionalContent: PromotionalContent) = promotionalDao.insertPromotionalContent(promotionalContent = promotionalContent)
    suspend fun deletePromotionalContent(promotionalContent: PromotionalContent) = promotionalDao.deletePromotionalContent(promotionalContent = promotionalContent)
    fun getPromotionalContents() = promotionalDao.getPromotionalContents()
}