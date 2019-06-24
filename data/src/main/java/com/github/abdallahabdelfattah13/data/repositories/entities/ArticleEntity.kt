package com.github.abdallahabdelfattah13.data.repositories.entities

import java.util.*

data class ArticleEntity(
    val id: Int,
    val title: String,
    val url: String,
    val publicationDate: Date
)