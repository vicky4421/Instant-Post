package com.vismijatech.instantpost.db.typeconverters

import androidx.room.TypeConverter
import java.util.Date

/**
 * Created by Vivek Sawant on 29-12-2023.
 */
class Converters {
    @TypeConverter
    fun fromDateToInt(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun fromIntToDate(long: Long): Date{
        return Date(long)
    }
}