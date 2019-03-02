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

    fun createNewFeed(feedUrl: String): Completable

    fun getFeeds(): Observable<List<Feed>>

    fun getFeedArticles(feedId: Int): Flowable<List<Article>>

    fun deleteFeed(feedId: Int): Completable
}