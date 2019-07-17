package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Completable


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class DeleteFeedUseCase(private val feedRepository: FeedRepository) {

    fun run(feedId: Int): Completable =
        if (feedId < -1)
            Completable.error(IllegalArgumentException("FeedId can't be -ve!"))
        else
            feedRepository.deleteFeed(feedId)

}