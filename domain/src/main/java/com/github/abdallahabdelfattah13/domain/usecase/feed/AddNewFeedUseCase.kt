package com.github.abdallahabdelfattah13.domain.usecase.feed

import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.repositories.feed.FeedRepository
import io.reactivex.Completable
import java.net.URL


/**
 * Created by AbdAllah AbdElfattah on 21/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class AddNewFeedUseCase(private val feedRepository: FeedRepository) {

    fun run(feedUrl: String): Completable {
        if (feedUrl.isBlank())
            return Completable.error(IllegalArgumentException("Can't add an empty url!"))
        try {
            URL(feedUrl).toURI()
        } catch (e: Exception) {
            return Completable.error(IllegalArgumentException("feedUrl isn't valid url!"))
        }

        return feedRepository.createNewFeedInMemory(feedUrl)
    }
}