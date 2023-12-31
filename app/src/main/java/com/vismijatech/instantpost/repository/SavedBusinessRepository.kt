package com.vismijatech.instantpost.repository

import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.BusinessContent
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
class SavedBusinessRepository @Inject constructor(private val db: InstantPostDatabase) {
    private val businessDao = db.businessDao()
    suspend fun insertBusinessContent(businessContent: BusinessContent) = businessDao.insertBusinessContent(businessContent = businessContent)
    suspend fun deleteBusinessContent(businessContent: BusinessContent) = businessDao.deleteBusinessContent(businessContent = businessContent)
    fun getBusinessContents() = businessDao.getBusinessContents()
}