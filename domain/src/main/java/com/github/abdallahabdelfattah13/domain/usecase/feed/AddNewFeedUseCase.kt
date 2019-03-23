package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Completable


/**
 * Created by AbdAllah AbdElfattah on 21/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class AddNewFeedUseCase(private val feedRepository: FeedRepository) {

    fun run(feedUrl: String): Completable {
        if (feedUrl.isBlank())
            return Completable.error(IllegalArgumentException("Can't add an empty url!"))
        return feedRepository.createNewFeed(feedUrl)
    }
}