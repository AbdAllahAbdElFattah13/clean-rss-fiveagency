package com.github.abdallahabdelfattah13.data.repositories.db.type_converters

import androidx.room.TypeConverter
import com.github.abdallahabdelfattah13.data.repositories.entities.ArticleEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object TypeConvert {

    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToListArticleDb(data: String?): List<ArticleEntity> {
        return data?.run {
            val list = object: TypeToken<List<ArticleEntity>>(){}.type
            gson.fromJson<List<ArticleEntity>>(this, list)
        } ?: emptyList()
    }

    @TypeConverter
    fun listArticleDbToString(obj: List<ArticleEntity>): String = gson.toJson(obj)

    @TypeConverter
    fun timeStampToDate(date: Long): Date = Date(date)

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time

}