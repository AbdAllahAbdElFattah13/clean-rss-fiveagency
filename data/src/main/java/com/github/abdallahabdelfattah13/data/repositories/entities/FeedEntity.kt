package com.github.abdallahabdelfattah13.data.repositories.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.abdallahabdelfattah13.domain.model.Feed

fun FeedEntity.mapToFeed() : Feed {
    return Feed(this.id, this.url, this.thumbnail, this.title, this.description)
}

@Entity(tableName = "feed")
data class FeedEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val url: String,
    val thumbnail: String,
    val title: String,
    val description: String
)