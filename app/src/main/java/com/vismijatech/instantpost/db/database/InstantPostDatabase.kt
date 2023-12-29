package com.vismijatech.instantpost.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vismijatech.instantpost.data.DATABASE_VERSION_1
import com.vismijatech.instantpost.db.dao.BlogDao
import com.vismijatech.instantpost.db.dao.BusinessDao
import com.vismijatech.instantpost.db.dao.EmailDao
import com.vismijatech.instantpost.db.dao.NewsletterDao
import com.vismijatech.instantpost.db.dao.PersonalDao
import com.vismijatech.instantpost.db.dao.PromotionalDao
import com.vismijatech.instantpost.db.dao.SocialMediaDao
import com.vismijatech.instantpost.db.dao.WhatsappDao
import com.vismijatech.instantpost.db.entities.BlogContent
import com.vismijatech.instantpost.db.entities.BusinessContent
import com.vismijatech.instantpost.db.entities.EmailContent
import com.vismijatech.instantpost.db.entities.NewsletterContent
import com.vismijatech.instantpost.db.entities.PersonalContent
import com.vismijatech.instantpost.db.entities.PromotionalContent
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import com.vismijatech.instantpost.db.entities.WhatsappContent
import com.vismijatech.instantpost.db.typeconverters.Converters

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Database(
    entities = [BlogContent::class,
        EmailContent::class,
        SocialMediaContent::class,
        WhatsappContent::class,
        BusinessContent::class,
        NewsletterContent::class,
        PersonalContent::class,
        PromotionalContent::class],
    version = DATABASE_VERSION_1
)
@TypeConverters(Converters::class)
abstract class InstantPostDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao
    abstract fun socialMediaDao(): SocialMediaDao
    abstract fun whatsappDao(): WhatsappDao
    abstract fun businessDao(): BusinessDao
    abstract fun newsletterDao(): NewsletterDao
    abstract fun personalDao(): PersonalDao
    abstract fun promotionalDao(): PromotionalDao
    abstract fun emailDao(): EmailDao
}