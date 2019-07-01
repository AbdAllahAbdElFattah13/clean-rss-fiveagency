package com.github.abdallahabdelfattah13.data.repositories.data_factory

import com.github.abdallahabdelfattah13.data.repositories.db.FeedDatabase
import com.github.abdallahabdelfattah13.data.repositories.feed.InMemoryFeedRepo
import com.github.abdallahabdelfattah13.data.repositories.feed.InMemoryFeedRepoImpl

class DataSourceFactory(
    private val provideFeedInMemory: InMemoryFeedRepo,
    private val provideDatabase: FeedDatabase
) {

    val feedInMemoryImpl: InMemoryFeedRepo
        get() = provideFeedInMemory

    val feedDatabase: FeedDatabase
        get() = provideDatabase
}