package com.github.abdallahabdelfattah13.data.repositories.feed

import com.github.abdallahabdelfattah13.domain.model.Feed
import org.junit.Before
import org.junit.Test

/**
 * Created by AbdAllah AbdElfattah on 24/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class InMemoryFeedRepositoryTest {

    lateinit var feedRepository: InMemoryFeedRepository

    @Before
    fun setUp() {
        feedRepository = InMemoryFeedRepository()
    }

    @Test
    fun shouldCompleteCreateFeedSuccessfully() {
        feedRepository.createNewFeed(getListOfFeedsUrl()[0])
            .test()
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfEmptyFeedUrl() {
        feedRepository.createNewFeed("")
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfBlankFeedUrl() {
        feedRepository.createNewFeed("                  ")
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfSameFeedUrl() {
        val testFeedUrl = getListOfFeedsUrl()[0]
        feedRepository.createNewFeed(testFeedUrl)

        feedRepository.createNewFeed(testFeedUrl)
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldErrorIfCreatedFeedWithoutValidUrl() {
        feedRepository.createNewFeed("Some random text that isn't url!")
            .test()
            .assertError(IllegalArgumentException::class.java)
    }

    private fun mapStringToFeedObject(index: Int, s: String): Feed = Feed(
        id = index,
        url = s,
        title = s,
        description = "This is feed number: $index in your list",
        thumbnail = ""
    )

    private fun getListOfFeedsUrl(): List<String> {
        return listOf(
            "http://feeds.bbci.co.uk/news/rss.xml",
            "http://news.yahoo.com/rss/",
            "http://feeds.bbci.co.uk/news/technology/rss.xml"
        )
    }

    private fun getListOfFeedsObject(): List<Feed> {
        return getListOfFeedsUrl().mapIndexed(::mapStringToFeedObject)
    }
}