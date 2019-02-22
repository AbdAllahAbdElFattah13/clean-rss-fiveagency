package com.github.abdallahabdelfattah13.domain.model.usecase

import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.model.repositories.FeedRepository
import io.reactivex.Flowable


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class GetAllFeedsUseCase(private val feedRepository: FeedRepository) {

    fun run(): Flowable<List<Feed>> = feedRepository.getFeeds()

}