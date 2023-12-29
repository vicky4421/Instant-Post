package com.vismijatech.instantpost.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Entity(tableName = "WhatsappContent")
data class WhatsappContent(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: Int,
    val content: String,
    val title: String

)
