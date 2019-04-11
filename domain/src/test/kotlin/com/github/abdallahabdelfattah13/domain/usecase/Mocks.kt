package com.github.abdallahabdelfattah13.domain.usecase

import com.github.abdallahabdelfattah13.domain.model.Feed


/**
 * Created by AbdAllah AbdElfattah on 29/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
object Mocks {

    private fun mapStringToFeedObject(index: Int, s: String): Feed = Feed(
        id = index,
        url = s,
        title = s,
        description = "This is feed number: $index in your list",
        thumbnail = ""
    )

    val feedsUrl: List<String>
        get() {
            return listOf(
                "http://feeds.bbci.co.uk/news/rss.xml",
                "http://news.yahoo.com/rss/",
                "http://feeds.bbci.co.uk/news/technology/rss.xml"
            )
        }

    val feeds: List<Feed>
        get() {
            return feedsUrl.mapIndexed(::mapStringToFeedObject)
        }
}