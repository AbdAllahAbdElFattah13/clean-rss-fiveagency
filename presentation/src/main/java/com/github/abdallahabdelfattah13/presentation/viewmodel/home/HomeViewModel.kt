package com.github.abdallahabdelfattah13.presentation.viewmodel.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.abdallahabdelfattah13.domain.model.Feed
import com.github.abdallahabdelfattah13.domain.usecase.feed.AddNewFeedUseCase
import com.github.abdallahabdelfattah13.domain.usecase.feed.DeleteFeedUseCase
import com.github.abdallahabdelfattah13.domain.usecase.feed.GetAllFeedsUseCase
import com.github.abdallahabdelfattah13.presentation.viewmodel.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


/**
 * Created by AbdAllah AbdElfattah on 23/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class HomeViewModel(
    subscribeOn: Scheduler,
    observeOn: Scheduler,
    private val addNewFeedsUseCase: AddNewFeedUseCase,
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val deleteFeedUseCase: DeleteFeedUseCase
) : BaseViewModel(subscribeOn, observeOn) {

    val addFeedSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val deleteFeedSuccessLiveData = MutableLiveData<Boolean>()
    val feedsLiveData = MutableLiveData<List<Feed>>()

    fun addFeed(url: String) {
        executeCompletable(
            Consumer {
            },
            Action {
                addFeedSuccessLiveData.postValue(true)
            },
            Consumer {
                addFeedSuccessLiveData.postValue(false)
            },
            addNewFeedsUseCase.run(url)
        )
    }

    fun deleteFeed(feedId: Int) {
        executeCompletable(
            Consumer {
            },
            Action {
                deleteFeedSuccessLiveData.postValue(true)
            },
            Consumer {
                deleteFeedSuccessLiveData.postValue(false)
            },
            deleteFeedUseCase.run(feedId)
        )
    }

    fun getFeeds() {
        executeObservable(
            Consumer {

            },
            Consumer {
                feedsLiveData.postValue(it)
            },
            Consumer {
                feedsLiveData.postValue(emptyList())
            },
            getAllFeedsUseCase.run()
        )
    }

}

class HomeViewModelFactory(
    private val subscribeOn: Scheduler,
    private val observeOn: Scheduler,
    private val addNewFeedsUseCase: AddNewFeedUseCase,
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val deleteFeedUseCase: DeleteFeedUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return (HomeViewModel(
                subscribeOn,
                observeOn,
                addNewFeedsUseCase,
                getAllFeedsUseCase,
                deleteFeedUseCase
            )) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }

}