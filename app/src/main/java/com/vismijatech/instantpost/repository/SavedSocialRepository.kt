package com.vismijatech.instantpost.repository

import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 30-12-2023.
 */
class SavedSocialRepository @Inject constructor(private val db: InstantPostDatabase){
    private val socialDao = db.socialMediaDao()

    suspend fun insertSocialContent(socialMediaContent: SocialMediaContent){
        socialDao.insertSocialMediaContent(socialMediaContent = socialMediaContent)
    }

    suspend fun deleteSocialContent(socialMediaContent: SocialMediaContent){
        socialDao.deleteSocialMediaContent(socialMediaContent = socialMediaContent)
    }

    suspend fun getSocialMediaContents(): Flow<List<SocialMediaContent>> = socialDao.getSocialMediaContents()

}