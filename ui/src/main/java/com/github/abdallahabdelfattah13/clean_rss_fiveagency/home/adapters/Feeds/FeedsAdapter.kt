package com.github.abdallahabdelfattah13.clean_rss_fiveagency.home.adapters.Feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.abdallahabdelfattah13.clean_rss_fiveagency.R
import com.github.abdallahabdelfattah13.domain.model.Feed


/**
 * Created by AbdAllah AbdElfattah on 02/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class FeedsAdapter : ListAdapter<Feed, FeedViewHolder>(object : DiffUtil.ItemCallback<Feed>() {

    override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean = oldItem == newItem

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        return FeedViewHolder(v)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}