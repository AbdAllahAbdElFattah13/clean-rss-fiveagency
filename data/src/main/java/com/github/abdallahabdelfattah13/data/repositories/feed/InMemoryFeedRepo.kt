package com.github.abdallahabdelfattah13.data.repositories.feed

import com.github.abdallahabdelfattah13.domain.model.Article
import com.github.abdallahabdelfattah13.domain.model.Feed
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface InMemoryFeedRepo {

    fun createNewFeedInMemory(feedUrl: String): Completable

    fun getFeedsInMemory(): Observable<List<Feed>>

    fun getFeedArticlesInMemory(feedId: Int): Flowable<List<Article>>

    fun deleteFeedInMemory(feedId: Int): Completable

}