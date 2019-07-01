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

    lateinit var feedRepository: InMemoryFeedRepo

    @Before
    fun setUp() {
        feedRepository = InMemoryFeedRepoImpl()
    }

    //region feedRepoImpl.createNewFeed test cases
    @Test
    fun shouldCompleteCreateFeedSuccessfully() {
        feedRepository.createNewFeedInMemory(getListOfFeedsUrl()[0])
            .test()
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfEmptyFeedUrl() {
        feedRepository.createNewFeedInMemory("")
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfBlankFeedUrl() {
        feedRepository.createNewFeedInMemory("                  ")
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCreateFeedWithErrorInCaseOfSameFeedUrl() {
        val testFeedUrl = getListOfFeedsUrl()[0]
        feedRepository.createNewFeedInMemory(testFeedUrl)

        feedRepository.createNewFeedInMemory(testFeedUrl)
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldErrorIfCreatedFeedWithoutValidUrl() {
        feedRepository.createNewFeedInMemory("Some random text that isn't url!")
            .test()
            .assertError(IllegalArgumentException::class.java)
    }
    //endregion

    //region feedRepoImpl.deleteFeed test cases
    @Test
    fun shouldCompleteDeleteFeedSuccessfully() {
        val feedsList = getListOfFeeds().toMutableList()
        feedsList.forEach {
            feedRepository.createNewFeedInMemory(it.url)
        }

        feedsList.forEachIndexed { index, _ ->
            val completableUnderTesting = feedRepository.deleteFeedInMemory(index)
            completableUnderTesting
                .test()
                .assertComplete()
                .assertNoErrors()
        }
    }

    @Test
    fun shouldCompleteDeleteFeedWithError_NegNumber() {
        val completableUnderTesting = feedRepository.deleteFeedInMemory(-7)
        completableUnderTesting
            .test()
            .assertFailure(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldCompleteDeleteFeedWithError_NoneExistingId() {
        val feedsList = getListOfFeeds().toMutableList()
        feedsList.forEach {
            feedRepository.createNewFeedInMemory(it.url)
        }

        val completableUnderTesting = feedRepository.deleteFeedInMemory(feedsList.size)
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

        val testedFeedsObservable = feedRepository.getFeedsInMemory()

        listOfFeeds.forEach {
            feedRepository.createNewFeedInMemory(it.url)
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
            feedRepository.createNewFeedInMemory(it.url)
        }
        val testFeedsList = ArrayList<Feed>(listOfFeeds)

        val testedFeedsObservable = feedRepository.getFeedsInMemory()
        listOfFeeds.forEachIndexed { index, _ ->
            feedRepository.deleteFeedInMemory(index)
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
        val testedFeedsObservable = feedRepository.getFeedsInMemory()

        feedRepository.createNewFeedInMemory("    ")

        testedFeedsObservable
            .test()
            .assertSubscribed()
            .assertNoErrors()
            .assertNever { emittedList -> emittedList.find { feed -> feed.url.isBlank() } != null }
    }

    @Test
    fun getFeedsWillReturnLastStateUponSubscription() {
        val testedFeedsObservable = feedRepository.getFeedsInMemory()
        val feeds = getListOfFeeds()

        feeds.forEach { feed -> feedRepository.createNewFeedInMemory(feed.url) }

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