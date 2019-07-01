package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Observable


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class GetAllFeedsUseCase(private val feedRepository: FeedRepository) {

    fun run(): Observable<List<Feed>> = feedRepository.getFeedsInMemory()

}