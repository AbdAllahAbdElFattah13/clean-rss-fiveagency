package com.github.abdallahabdelfattah13.domain.model.usecase

import com.github.abdallahabdelfattah13.domain.model.repositories.FeedRepository
import io.reactivex.Single


/**
 * Created by AbdAllah AbdElfattah on 21/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class AddNewFeedUseCase(private val feedRepository: FeedRepository) {

    fun run(feedUrl: String): Single<Int> = feedRepository.createNewFeed(feedUrl)
}