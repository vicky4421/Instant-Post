package com.vismijatech.instantpost.repository

import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.PersonalContent
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
class SavedPersonalRepository @Inject constructor(private val db: InstantPostDatabase) {
    val personalDao = db.personalDao()
    suspend fun insertPersonalContent(personalContent: PersonalContent) = personalDao.insertPersonalContent(personalContent = personalContent)
    suspend fun deletePersonalContent(personalContent: PersonalContent) = personalDao.deletePersonalContent(personalContent = personalContent)
    fun getPersonalContents() = personalDao.getPersonalContents()
}