package com.github.abdallahabdelfattah13.data.repositories.feed

import com.github.abdallahabdelfattah13.domain.model.Article
import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.net.URL


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class InMemoryFeedRepoImpl : InMemoryFeedRepo {

    private val feeds = ArrayList<Feed>()
    private val behaviorSubject: BehaviorSubject<List<Feed>> = BehaviorSubject.create()

    override fun createNewFeedInMemory(feedUrl: String): Completable {

        if (feedUrl.isBlank()) return Completable.error(IllegalArgumentException("feedUrl can't be empty or blank!"))

        try {
            URL(feedUrl).toURI()
        } catch (e: Exception) {
            return Completable.error(IllegalArgumentException("feedUrl isn't valid url!"))
        }

        val feedsUrlAlreadyExist = feeds.firstOrNull { feedUrl == it.url }
        if (feedsUrlAlreadyExist != null) return Completable.error(IllegalArgumentException("Feed already added!"))
        val feedId = feeds.size
        val feedCreated = Feed(
            id = feedId,
            url = feedUrl,
            thumbnail = "",
            title = feedUrl,
            description = "This is feed number: $feedId in your list"
        )
        feeds.add(feedCreated)
        behaviorSubject.onNext(ArrayList(feeds))
        return Completable.complete()
    }

    override fun getFeedsInMemory(): Observable<List<Feed>> = behaviorSubject

    override fun getFeedArticlesInMemory(feedId: Int): Flowable<List<Article>> {
        TODO("Not implemented yet")
    }

    override fun deleteFeedInMemory(feedId: Int): Completable {

        if (feedId < 0) return Completable.error(IllegalArgumentException("feedId can't be -ve!"))

        val feedIndex = feeds.indexOfFirst { it.id == feedId }
        return if (feedIndex == -1) Completable.error(IllegalArgumentException("The feedId provided can't be found!"))
        else {
            feeds.removeAt(feedIndex)
            behaviorSubject.onNext(ArrayList<Feed>(feeds))
            Completable.complete()
        }
    }
}