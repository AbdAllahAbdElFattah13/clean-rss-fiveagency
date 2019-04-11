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

    //region feedRepository.createNewFeed test cases
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
    //endregion

    //region feedRepository.deleteFeed test cases
    @Test
    fun shouldCompleteDeleteFeedSuccessfully() {
        val feedsList = getListOfFeeds().toMutableList()
        feedsList.forEach {
            feedRepository.createNewFeed(it.url)
        }

        feedsList.forEachIndexed { index, _ ->
            val completableUnderTesting = feedRepository.deleteFeed(index)
            completableUnderTesting
                .test()
                .assertComplete()
                .assertNoErrors()
        }
    }

    @Test
    fun shouldCompleteDeleteFeedWithError_NegNumber() {
        val completableUnderTesting = feedRepository.deleteFeed(-7)
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCompleteDeleteFeedWithError_NoneExistingId() {
        val feedsList = getListOfFeeds().toMutableList()
        feedsList.forEach {
            feedRepository.createNewFeed(it.url)
        }

        val completableUnderTesting = feedRepository.deleteFeed(feedsList.size)
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    //endregion

    //region feedRepository.getFeeds test cases
    @Test
    fun getFeedsSuccessfully() {
        val listOfFeeds = getListOfFeeds()
        val testFeedsList = ArrayList<Feed>()

        val testedFeedsObservable = feedRepository.getFeeds()

        listOfFeeds.forEach {
            feedRepository.createNewFeed(it.url)
            testFeedsList.add(it)

            testedFeedsObservable
                .test()
                .assertSubscribed()
                .assertNoErrors()
                .assertValue(testFeedsList)
        }
    }

    @Test
    fun getFeedsWillNotEmitInCaseOfBlankStrings() {
        val testedFeedsObservable = feedRepository.getFeeds()

        feedRepository.createNewFeed("    ")

        testedFeedsObservable
            .test()
            .assertSubscribed()
            .assertNoErrors()
            .assertNever { emittedList -> emittedList.find { feed -> feed.url.isBlank() } != null }
    }

    @Test
    fun getFeedsWillReturnLastStateUponSubscription() {
        val testedFeedsObservable = feedRepository.getFeeds()
        val feeds = getListOfFeeds()

        feeds.forEach { feed -> feedRepository.createNewFeed(feed.url) }

        testedFeedsObservable
            .test()
            .assertSubscribed()
            .assertNoErrors()
            .assertValue(feeds)
    }
    //endregion


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

    private fun getListOfFeeds(): List<Feed> {
        return getListOfFeedsUrl().mapIndexed(::mapStringToFeedObject)
    }
}