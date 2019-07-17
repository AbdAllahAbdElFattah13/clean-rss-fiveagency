package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import com.github.abdallahabdelfattah13.domain.usecase.Mocks.feedsUrl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

/**
 * Created by AbdAllah AbdElfattah on 29/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class AddNewFeedUseCaseTest {

    @MockK
    internal lateinit var repo: FeedRepository
    private val addFeedUseCase by lazy { AddNewFeedUseCase(repo) }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun run_validUrlSuccess() {
        every { repo.createNewFeed(any()) } returns Completable.complete()

        val result = addFeedUseCase.run(feedsUrl[0])
        result.test()
            .assertComplete()
            .assertNoErrors()
    }


    @Test
    fun run_invalidUrl_empty() {
        every { repo.createNewFeed(any()) } returns Completable.complete()

        val completableUnderTesting = addFeedUseCase.run("")
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun run_invalidUrl_Blank() {
        every { repo.createNewFeed(any()) } returns Completable.complete()

        val completableUnderTesting = addFeedUseCase.run("     ")
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun run_invalidUrl_NoneEmptyString() {
        every { repo.createNewFeed(any()) } returns Completable.complete()

        val completableUnderTesting = addFeedUseCase.run("loremIpsum")
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

}