package com.github.abdallahabdelfattah13.data.repositories.dao

import androidx.room.*
import com.github.abdallahabdelfattah13.data.repositories.entities.FeedEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feedEntity: FeedEntity): Completable

    @Query("SELECT * FROM feed")
    fun selectFeeds(): Flowable<List<FeedEntity>>

    /*@Delete
    fun deleteFeed(feedDb: FeedEntity): Completable*/

    @Query("DELETE FROM feed WHERE id = :feedId")
    fun deleteFeed(feedId: Int): Completable

}