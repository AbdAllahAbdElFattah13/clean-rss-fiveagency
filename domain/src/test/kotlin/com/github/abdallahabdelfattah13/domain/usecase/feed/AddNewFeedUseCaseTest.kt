package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import com.github.abdallahabdelfattah13.domain.usecase.Mocks
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

/**
 * Created by AbdAllah AbdElfattah on 29/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class AddNewFeedUseCaseTest {

    private lateinit var addFeedUseCase: AddNewFeedUseCase
    private val BLANK_STRING_CASE = "      "
    private val NONE_URL_STRING_CASE = "some random text that isn't a valid url and isn't empty!"


    @Before
    fun setUp() {
        val repo: FeedRepository = mock()

        Mocks.feedsUrl.forEach {
            given(repo.createNewFeed(it)).willReturn(Completable.complete())
        }

        given(repo.createNewFeed("")).willReturn(Completable.error(IllegalArgumentException()))
        given(repo.createNewFeed(BLANK_STRING_CASE)).willReturn(Completable.error(IllegalArgumentException()))
        given(repo.createNewFeed(NONE_URL_STRING_CASE))
            .willReturn(
                Completable.error(
                    IllegalArgumentException()
                )
            )

        addFeedUseCase = AddNewFeedUseCase(repo)
    }

    @Test
    fun run_validUrlSuccess() {
        val completableUnderTesting = addFeedUseCase.run(Mocks.feedsUrl[0])
        completableUnderTesting
            .test()
            .assertComplete()
            .assertNoErrors()
    }


    @Test
    fun run_invalidUrl_empty() {
        val completableUnderTesting = addFeedUseCase.run("")
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun run_invalidUrl_Blank() {
        val completableUnderTesting = addFeedUseCase.run(BLANK_STRING_CASE)
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun run_invalidUrl_NoneEmptyString() {
        val completableUnderTesting = addFeedUseCase.run(NONE_URL_STRING_CASE)
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

}