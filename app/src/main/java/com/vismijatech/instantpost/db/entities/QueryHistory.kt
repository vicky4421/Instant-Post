package com.vismijatech.instantpost.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Vivek Sawant on 07-01-2024.
 */
@Entity(tableName = "QueryHistory")
data class QueryHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val query: String
)
