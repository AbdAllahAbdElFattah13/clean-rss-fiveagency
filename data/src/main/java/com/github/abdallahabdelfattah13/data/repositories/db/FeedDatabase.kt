package com.github.abdallahabdelfattah13.data.repositories.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.abdallahabdelfattah13.data.repositories.dao.FeedDao
import com.github.abdallahabdelfattah13.data.repositories.db.type_converters.TypeConvert
import com.github.abdallahabdelfattah13.data.repositories.entities.FeedEntity

@Database(entities = [FeedEntity::class], version = 3, exportSchema = false)
@TypeConverters(TypeConvert::class)
abstract class FeedDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao

}