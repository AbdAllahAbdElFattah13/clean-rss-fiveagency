package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import com.github.abdallahabdelfattah13.domain.usecase.feed
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

/**
 * Created by AbdAllah AbdElfattah on 11/04/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class DeleteFeedUseCaseTest {

    @MockK
    internal lateinit var repo: FeedRepository
    private val deleteFeedUseCase by lazy { DeleteFeedUseCase(repo) }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun run_validIdToDelete_success() {
        every { repo.deleteFeed(any()) } returns Completable.complete()

        val result = deleteFeedUseCase.run(feed.id)
        result.test()
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun run_invalidIdToDelete_negative_fail() {
        every { repo.deleteFeed(any()) } returns Completable.complete()

        val result = deleteFeedUseCase.run(-2)
        result
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun run_invalidIdToDelete_noneExistingNumber_fail() {
        every { repo.deleteFeed(any()) } returns Completable.complete()

        val result = deleteFeedUseCase.run(-2)
        result
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }
}