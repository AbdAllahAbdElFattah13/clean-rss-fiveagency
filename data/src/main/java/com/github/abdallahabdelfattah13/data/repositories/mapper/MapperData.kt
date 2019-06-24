package com.github.abdallahabdelfattah13.data.repositories.mapper

import com.github.abdallahabdelfattah13.data.repositories.entities.FeedEntity
import com.github.abdallahabdelfattah13.domain.model.Feed

class MapperData {

    companion object {

        fun toFeedDomain(feedEntity: List<FeedEntity>): List<Feed> {
            return feedEntity
                .map { Feed(it.id, it.url, it.thumbnail, it.title, it.description) }
        }

        fun toFeedData(feed: Feed): FeedEntity = FeedEntity(feed.id, feed.url, feed.thumbnail, feed.title, feed.description)

    }

}