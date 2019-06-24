package com.github.abdallahabdelfattah13.data.repositories.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed")
data class FeedEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val url: String,
    val thumbnail: String,
    val title: String,
    val description: String
)