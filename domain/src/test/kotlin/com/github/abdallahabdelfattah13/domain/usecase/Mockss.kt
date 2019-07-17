package com.github.abdallahabdelfattah13.domain.usecase

import com.github.abdallahabdelfattah13.domain.model.Feed
import io.mockk.every
import io.mockk.mockk

val feed = mockk<Feed>().also {
    every { it.id } returns 1
    every { it.description } returns "description"
    every { it.thumbnail } returns "thumbnail"
    every { it.title } returns "title"
    every { it.url } returns "www.google.com"
}