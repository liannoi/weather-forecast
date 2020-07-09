package org.itstep.liannoi.weatherforecast.application.common.storage

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbstractRepository {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.follow() {
        compositeDisposable.add(this)
    }

    fun stop() {
        compositeDisposable.clear()
    }

    fun destroy() {
        compositeDisposable.dispose()
    }
}
