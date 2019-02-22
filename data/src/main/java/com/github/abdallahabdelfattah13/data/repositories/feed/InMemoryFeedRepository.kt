package com.github.abdallahabdelfattah13.data.repositories.feed

import com.github.abdallahabdelfattah13.domain.model.Article
import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class InMemoryFeedRepository : FeedRepository {

    private val feeds = ArrayList<Feed>()

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

        return Completable.complete()
    }

    override fun getFeeds(): Flowable<List<Feed>> = Flowable.just(feeds)

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