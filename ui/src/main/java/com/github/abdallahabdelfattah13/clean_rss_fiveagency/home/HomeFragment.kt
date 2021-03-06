package com.github.abdallahabdelfattah13.clean_rss_fiveagency.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.abdallahabdelfattah13.clean_rss_fiveagency.R
import com.github.abdallahabdelfattah13.clean_rss_fiveagency.home.adapters.Feeds.FeedsAdapter
import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.kotlin_extensions.snack
import com.github.abdallahabdelfattah13.presentation.PresentationInjections
import com.github.abdallahabdelfattah13.presentation.viewmodel.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), FeedsAdapter.FeedItemActions {

    private lateinit var homeViewModel: HomeViewModel
    private val feedsAdapter = FeedsAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_feed_items.adapter = feedsAdapter
        rv_feed_items.layoutManager = LinearLayoutManager(rv_feed_items.context, RecyclerView.VERTICAL, false)

        homeViewModel = ViewModelProviders.of(this, PresentationInjections.provideHomeViewModelFactory())
            .get(HomeViewModel::class.java)

        homeViewModel.feedsLiveData.observe(viewLifecycleOwner, Observer {
            feedsAdapter.submitList(it)
        })

        iv_save_feed.setOnClickListener {
            val newFeed = til_feed_input.editText?.text?.toString()
            if (newFeed.isNullOrBlank()) return@setOnClickListener

            homeViewModel.addFeed(newFeed)
            til_feed_input.editText?.text?.clear()
        }

        homeViewModel.addFeedSuccessLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                cl_root_view.snack("Added the feed successfully!")
            } else {
                cl_root_view.snack("Something went wrong while adding the new feed, please try again later!")
            }
        })

        homeViewModel.deleteFeedSuccessLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                cl_root_view.snack("Deleted the feed successfully!")
            } else {
                cl_root_view.snack("Something went wrong while deleting your feed, please try again later!")
            }
        })

        homeViewModel.getFeeds()

    }

    override fun onDeleteFeedClicked(feed: Feed) {
        homeViewModel.deleteFeed(feed.id)
    }
}
