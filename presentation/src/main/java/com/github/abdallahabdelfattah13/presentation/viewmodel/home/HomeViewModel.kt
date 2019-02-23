package com.github.abdallahabdelfattah13.presentation.viewmodel.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.abdallahabdelfattah13.domain.usecase.feed.AddNewFeedUseCase
import com.github.abdallahabdelfattah13.domain.usecase.feed.DeleteFeedUseCase
import com.github.abdallahabdelfattah13.domain.usecase.feed.GetAllFeedsUseCase
import com.github.abdallahabdelfattah13.presentation.viewmodel.BaseViewModel
import io.reactivex.Scheduler


/**
 * Created by AbdAllah AbdElfattah on 23/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class HomeViewModel(
    private val subscribeOn: Scheduler,
    private val observeOn: Scheduler,
    private val addNewFeedsUseCase: AddNewFeedUseCase,
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val deleteFeedUseCase: DeleteFeedUseCase
) : BaseViewModel(subscribeOn, observeOn) {


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