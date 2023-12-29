package com.vismijatech.instantpost.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vismijatech.instantpost.data.DATABASE_VERSION_1
import com.vismijatech.instantpost.db.dao.BlogDao
import com.vismijatech.instantpost.db.dao.EmailDao
import com.vismijatech.instantpost.db.dao.SocialMediaDao
import com.vismijatech.instantpost.db.dao.WhatsappDao
import com.vismijatech.instantpost.db.entities.BlogContent
import com.vismijatech.instantpost.db.entities.EmailContent
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import com.vismijatech.instantpost.db.entities.WhatsappContent
import com.vismijatech.instantpost.db.typeconverters.Converters

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Database(entities = [BlogContent::class, EmailContent::class, SocialMediaContent::class, WhatsappContent::class], version = DATABASE_VERSION_1)
@TypeConverters(Converters::class)
abstract class InstantPostDatabase: RoomDatabase() {
    abstract fun blogDao(): BlogDao
    abstract fun emailDao(): EmailDao
    abstract fun socialMediaDao(): SocialMediaDao
    abstract fun whatsappDao(): WhatsappDao
}