package com.github.abdallahabdelfattah13.data.repositories.feed

import com.github.abdallahabdelfattah13.domain.model.Feed
import org.junit.Before
import org.junit.Test

/**
 * Created by AbdAllah AbdElfattah on 24/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class InMemoryFeedRepoImplTest {

    lateinit var feedRepoImpl: InMemoryFeedRepoImpl

    @Before
    fun setUp() {
        feedRepoImpl = InMemoryFeedRepoImpl()
    }

    //region feedRepoImpl.createNewFeed test cases
    @Test
    fun shouldCompleteCreateFeedSuccessfully() {
        feedRepoImpl.createNewFeedInMemory(getListOfFeedsUrl()[0])
            .test()
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfEmptyFeedUrl() {
        feedRepoImpl.createNewFeedInMemory("")
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfBlankFeedUrl() {
        feedRepoImpl.createNewFeedInMemory("                  ")
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfSameFeedUrl() {
        val testFeedUrl = getListOfFeedsUrl()[0]
        feedRepoImpl.createNewFeedInMemory(testFeedUrl)

        feedRepoImpl.createNewFeedInMemory(testFeedUrl)
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldErrorIfCreatedFeedWithoutValidUrl() {
        feedRepoImpl.createNewFeedInMemory("Some random text that isn't url!")
            .test()
            .assertError(IllegalArgumentException::class.java)
    }
    //endregion

    //region feedRepoImpl.deleteFeed test cases
    @Test
    fun shouldCompleteDeleteFeedSuccessfully() {
        val feedsList = getListOfFeeds().toMutableList()
        feedsList.forEach {
            feedRepoImpl.createNewFeedInMemory(it.url)
        }

        feedsList.forEachIndexed { index, _ ->
            val completableUnderTesting = feedRepoImpl.deleteFeedInMemory(index)
            completableUnderTesting
                .test()
                .assertComplete()
                .assertNoErrors()
        }
    }

    @Test
    fun shouldCompleteDeleteFeedWithError_NegNumber() {
        val completableUnderTesting = feedRepoImpl.deleteFeedInMemory(-7)
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCompleteDeleteFeedWithError_NoneExistingId() {
        val feedsList = getListOfFeeds().toMutableList()
        feedsList.forEach {
            feedRepoImpl.createNewFeedInMemory(it.url)
        }

        val completableUnderTesting = feedRepoImpl.deleteFeedInMemory(feedsList.size)
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    //endregion

    //region feedRepoImpl.getFeeds test cases
    @Test
    fun getFeedsSuccessfully_WhileAdding() {
        val listOfFeeds = getListOfFeeds()
        val testFeedsList = ArrayList<Feed>()

        val testedFeedsObservable = feedRepoImpl.getFeedsInMemory()

        listOfFeeds.forEach {
            feedRepoImpl.createNewFeedInMemory(it.url)
            testFeedsList.add(it)

            testedFeedsObservable
                .test()
                .assertSubscribed()
                .assertNoErrors()
                .assertValue(testFeedsList)
        }
    }

    fun getFeedsSuccessfully_WhileDeleting() {
        val listOfFeeds = getListOfFeeds()
        listOfFeeds.forEach {
            feedRepoImpl.createNewFeedInMemory(it.url)
        }
        val testFeedsList = ArrayList<Feed>(listOfFeeds)

        val testedFeedsObservable = feedRepoImpl.getFeedsInMemory()
        listOfFeeds.forEachIndexed { index, _ ->
            feedRepoImpl.deleteFeedInMemory(index)
            testFeedsList.removeAt(index)

            testedFeedsObservable
                .test()
                .assertSubscribed()
                .assertNoErrors()
                .assertValue(testFeedsList)
        }
    }


    @Test
    fun getFeedsWillNotEmitInCaseOfBlankStrings() {
        val testedFeedsObservable = feedRepoImpl.getFeedsInMemory()

        feedRepoImpl.createNewFeedInMemory("    ")

        testedFeedsObservable
            .test()
            .assertSubscribed()
            .assertNoErrors()
            .assertNever { emittedList -> emittedList.find { feed -> feed.url.isBlank() } != null }
    }

    @Test
    fun getFeedsWillReturnLastStateUponSubscription() {
        val testedFeedsObservable = feedRepoImpl.getFeedsInMemory()
        val feeds = getListOfFeeds()

        feeds.forEach { feed -> feedRepoImpl.createNewFeedInMemory(feed.url) }

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