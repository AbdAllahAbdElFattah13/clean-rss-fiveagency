package com.github.abdallahabdelfattah13.data

import com.github.abdallahabdelfattah13.data.repositories.feed.InMemoryFeedRepository
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
object DataInjection {

    @JvmStatic
    fun provideFeedRepository(): FeedRepository = InMemoryFeedRepository()
}