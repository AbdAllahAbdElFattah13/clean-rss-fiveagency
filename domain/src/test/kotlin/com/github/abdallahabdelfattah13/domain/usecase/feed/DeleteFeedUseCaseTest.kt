package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import com.github.abdallahabdelfattah13.domain.usecase.Mocks
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

/**
 * Created by AbdAllah AbdElfattah on 11/04/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class DeleteFeedUseCaseTest {

    private lateinit var deleteFeedUseCase: DeleteFeedUseCase
    private val NEGATIVE_NUMBER_CASE = -13

    @Before
    fun setUp() {

        val repo: FeedRepository = mock()

        Mocks.feedsUrl.forEachIndexed { index, _ ->
            given(repo.deleteFeed(index)).willReturn(Completable.complete())
        }

        given(repo.deleteFeed(Mocks.feedsUrl.size)).willReturn(
            Completable.error(
                IllegalArgumentException()
            )
        )

        given(repo.deleteFeed(NEGATIVE_NUMBER_CASE))
            .willReturn(
                Completable.error(
                    IllegalArgumentException()
                )
            )

        deleteFeedUseCase = DeleteFeedUseCase(repo)

    }


    @Test
    fun run_validIdToDelete_success() {
        val feeds = Mocks.feeds

        feeds.forEachIndexed { index, _ ->
            val completableUnderTesting = deleteFeedUseCase.run(index)

            completableUnderTesting
                .test()
                .assertComplete()
                .assertNoErrors()
        }
    }

    @Test
    fun run_invalidIdToDelete_negative_fail() {
        val completableUnderTesting = deleteFeedUseCase.run(NEGATIVE_NUMBER_CASE)

        completableUnderTesting
            .test()
            .assertFailure(java.lang.IllegalArgumentException::class.java)
    }

    @Test
    fun run_invalidIdToDelete_noneExistingNumber_fail() {
        val completableUnderTesting = deleteFeedUseCase.run(Mocks.feedsUrl.size)

        completableUnderTesting
            .test()
            .assertFailure(java.lang.IllegalArgumentException::class.java)
    }
}