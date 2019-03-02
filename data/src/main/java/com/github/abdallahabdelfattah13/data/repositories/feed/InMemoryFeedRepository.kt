package com.github.abdallahabdelfattah13.data.repositories.feed

import com.github.abdallahabdelfattah13.domain.model.Article
import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class InMemoryFeedRepository : FeedRepository {

    private val feeds = ArrayList<Feed>()
    private val behaviorSubject: BehaviorSubject<List<Feed>> = BehaviorSubject.create()

    override fun createNewFeed(feedUrl: String): Completable {
        val feedId = feeds.size
        val feedCreated = Feed(
            id = feedId,
            url = feedUrl,
            thumbnail = "",
            title = feedUrl,
            description = "This is feed number: $feedId in your list"
        )
        feeds.add(feedCreated)
        behaviorSubject.onNext(ArrayList<Feed>(feeds))
        return Completable.complete()
    }

    override fun getFeeds(): Observable<List<Feed>> = behaviorSubject

    override fun getFeedArticles(feedId: Int): Flowable<List<Article>> {
        TODO("Not implemented yet")
    }

    override fun deleteFeed(feedId: Int): Completable {
        val feedIndex = feeds.indexOfFirst { it.id == feedId }
        return if (feedIndex == -1) Completable.error(IllegalArgumentException())
        else {
            feeds.removeAt(feedIndex)
            Completable.complete()
        }
    }
}