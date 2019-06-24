package com.github.abdallahabdelfattah13.data

import android.content.Context
import androidx.room.Room
import com.github.abdallahabdelfattah13.data.repositories.data_factory.DataSourceFactory
import com.github.abdallahabdelfattah13.data.repositories.db.FeedDatabase
import com.github.abdallahabdelfattah13.data.repositories.feed.InMemoryFeedRepo
import com.github.abdallahabdelfattah13.data.repositories.feed.InMemoryFeedRepoImpl
import com.github.abdallahabdelfattah13.data.repositories.repository.FeedRepositoryImpl
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository


/**
 * Created by AbdAllah AbdElfattah on 22/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
object DataInjection {

    private lateinit var mFeedDatabase: FeedDatabase

    @JvmStatic
    fun provideFeedInMemory(): InMemoryFeedRepo = InMemoryFeedRepoImpl()

    @JvmStatic
    fun provideDatabase(context: Context): FeedDatabase {
        mFeedDatabase = Room.databaseBuilder(
            context,
            FeedDatabase::class.java,
            "feed.db"
        )
            .build()
        return mFeedDatabase
    }

    @JvmStatic
    fun provideDatasource(): DataSourceFactory =
        DataSourceFactory(provideFeedInMemory(), mFeedDatabase)

    @JvmStatic
    fun provideRepo(): FeedRepository = FeedRepositoryImpl(provideDatasource())
}