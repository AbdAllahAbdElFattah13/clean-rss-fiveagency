package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import com.github.abdallahabdelfattah13.domain.usecase.Mocks
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetAllFeedsUseCaseTest {

    @MockK
    internal lateinit var repo: FeedRepository
    private val getAllFeedsUseCase by lazy { GetAllFeedsUseCase(repo) }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun run_load_test() {
        every { getAllFeedsUseCase.run() } returns Observable.just(Mocks.feeds)

        val result = getAllFeedsUseCase.run()
        result
            .test()
            .assertNoErrors()
            .assertComplete()
    }
}