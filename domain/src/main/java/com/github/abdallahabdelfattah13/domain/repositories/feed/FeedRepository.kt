package com.github.abdallahabdelfattah13.domain.repositories.feed

import com.github.abdallahabdelfattah13.domain.model.Article
import com.github.abdallahabdelfattah13.domain.model.Feed
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable


/**
 * Created by AbdAllah AbdElfattah on 21/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
interface FeedRepository {

    fun createNewFeedInMemory(feedUrl: String): Completable

    fun getFeedsInMemory(): Observable<List<Feed>>

    fun getFeedArticlesInMemory(feedId: Int): Flowable<List<Article>>

    fun deleteFeedInMemory(feedId: Int): Completable

    fun insertFeedInDb(feed: Feed): Completable

    fun selectFeedsInDb(): Flowable<List<Feed>>

    fun deleteFeedInDb(feed: Int): Completable

}