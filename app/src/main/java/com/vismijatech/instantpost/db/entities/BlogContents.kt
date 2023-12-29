package com.vismijatech.instantpost.db.entities

import androidx.compose.ui.res.stringResource
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vismijatech.instantpost.R

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
@Entity(tableName = "BlogContents")
data class BlogContents(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: Int,
    val content: String,
    val title: String

)
