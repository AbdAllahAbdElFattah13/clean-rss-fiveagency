package com.github.abdallahabdelfattah13.presentation

import com.github.abdallahabdelfattah13.data.DataInjection
import com.github.abdallahabdelfattah13.domain.DomainInjection
import com.github.abdallahabdelfattah13.presentation.viewmodel.home.HomeViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by AbdAllah AbdElfattah on 23/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
object PresentationInjections {

    @JvmStatic
    fun provideHomeViewModelFactory(): HomeViewModelFactory {
        val feedsRepo = DataInjection.provideFeedRepository()
        return HomeViewModelFactory(
            Schedulers.io(), AndroidSchedulers.mainThread(),
            DomainInjection.provideAddNewFeedUseCase(feedsRepo),
            DomainInjection.provideGetAllFeedsUseCase(feedsRepo),
            DomainInjection.provideDeleteFeedUseCase(feedsRepo)
        )
    }
}