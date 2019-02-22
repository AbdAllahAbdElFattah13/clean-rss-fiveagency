package com.github.abdallahabdelfattah13.domain.model.usecase.feed

import com.github.abdallahabdelfattah13.domain.model.repositories.FeedRepository
import io.reactivex.Completable


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class DeleteFeedUseCase(private val feedRepository: FeedRepository) {

    fun run(feedId: Int): Completable = feedRepository.deleteFeed(feedId)

}