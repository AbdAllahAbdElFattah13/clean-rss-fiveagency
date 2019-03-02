package com.github.abdallahabdelfattah13.clean_rss_fiveagency.home.adapters.Feeds

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.abdallahabdelfattah13.clean_rss_fiveagency.R
import com.github.abdallahabdelfattah13.domain.model.Feed


/**
 * Created by AbdAllah AbdElfattah on 02/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvFeed = itemView.findViewById<TextView>(R.id.tv_feed)
    private lateinit var currentFeed: Feed

    fun bind(feed: Feed, index: Int) {
        currentFeed = feed
        tvFeed.text = currentFeed.url
    }
}