package com.github.abdallahabdelfattah13.data.repositories.db.type_converters

import androidx.room.TypeConverter
import java.util.*

object TypeConvert {

    @TypeConverter
    fun timeStampToDate(date: Long): Date = Date(date)

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time

}