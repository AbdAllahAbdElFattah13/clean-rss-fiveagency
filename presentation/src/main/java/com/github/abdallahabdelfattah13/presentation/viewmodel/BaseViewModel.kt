package com.github.abdallahabdelfattah13.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription


/**
 * Created by AbdAllah AbdElfattah on 23/02/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
abstract class BaseViewModel(private val subscribeOn: Scheduler, private val observeOn: Scheduler) : ViewModel() {

    private val disposables = CompositeDisposable()


    protected fun <T> executeFlowable(
        loadingConsumer: Consumer<Subscription>,
        successConsumer: Consumer<T>,
        throwableConsumer: Consumer<Throwable>,
        useCase: Flowable<T>
    ) {
        val observable = useCase
            .doOnSubscribe(loadingConsumer)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)

        addDisposable(observable.subscribe(successConsumer, throwableConsumer))
    }


    protected fun executeCompletable(
        loadingConsumer: Consumer<Disposable>,
        successConsumer: Action,
        throwableConsumer: Consumer<Throwable>,
        useCase: Completable
    ) {
        val observable: Completable = useCase
            .doOnSubscribe(loadingConsumer)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)

        addDisposable(observable.subscribe(successConsumer, throwableConsumer))
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    private fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}