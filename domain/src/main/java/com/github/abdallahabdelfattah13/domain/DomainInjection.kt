package com.github.abdallahabdelfattah13.domain

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import com.github.abdallahabdelfattah13.domain.usecase.feed.AddNewFeedUseCase
import com.github.abdallahabdelfattah13.domain.usecase.feed.DeleteFeedUseCase
import com.github.abdallahabdelfattah13.domain.usecase.feed.GetAllFeedsUseCase


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
object DomainInjection {

    @JvmStatic
    fun provideAddNewFeedUseCase(feedRepository: FeedRepository): AddNewFeedUseCase =
        AddNewFeedUseCase(feedRepository)

    @JvmStatic
    fun provideGetAllFeedsUseCase(feedRepository: FeedRepository): GetAllFeedsUseCase =
        GetAllFeedsUseCase(feedRepository)

    @JvmStatic
    fun provideDeleteFeedUseCase(feedRepository: FeedRepository): DeleteFeedUseCase =
        DeleteFeedUseCase(feedRepository)

}