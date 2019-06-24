package com.github.abdallahabdelfattah13.data.repositories.repository

import com.github.abdallahabdelfattah13.data.repositories.data_factory.DataSourceFactory
import com.github.abdallahabdelfattah13.data.repositories.mapper.MapperData
import com.github.abdallahabdelfattah13.domain.model.Article
import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

class FeedRepositoryImpl(private val dtSource: DataSourceFactory) : FeedRepository {

    override fun createNewFeedInMemory(feedUrl: String): Completable =
        dtSource.feedInMemoryImpl.createNewFeedInMemory(feedUrl)

    override fun getFeedsInMemory(): Observable<List<Feed>> =
        dtSource.feedInMemoryImpl.getFeedsInMemory()

    override fun getFeedArticlesInMemory(feedId: Int): Flowable<List<Article>> =
        dtSource.feedInMemoryImpl.getFeedArticlesInMemory(feedId)

    override fun deleteFeedInMemory(feedId: Int): Completable =
        dtSource.feedInMemoryImpl.deleteFeedInMemory(feedId)

    override fun insertFeedInDb(feed: Feed): Completable =
        dtSource.feedDatabase.feedDao().insertFeed(MapperData.toFeedData(feed))

    override fun selectFeedsInDb(): Flowable<List<Feed>> =
        dtSource.feedDatabase.feedDao().selectFeeds().map { MapperData.toFeedDomain(it) }

//    override fun deleteFeedInDb(feed: Feed): Completable =
//        dtSource.feedDatabase.feedDao().deleteFeed(MapperData.toFeedData(feed))

    override fun deleteFeedInDb(feed: Int): Completable =
        dtSource.feedDatabase.feedDao().deleteFeed(feed)
}