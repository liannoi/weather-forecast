package org.itstep.liannoi.weatherforecast.application.storage.forecasts

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.common.exceptions.DetailForecastException
import org.itstep.liannoi.weatherforecast.application.common.exceptions.FiveDaysForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.current.CurrentQuery
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.FiveDaysQuery
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ForecastRepository private constructor() {
    private val composite: CompositeDisposable = CompositeDisposable()
    private val forecastService: ForecastService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApplicationDefaults.ENDPOINT_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastService = retrofit.create(ForecastService::class.java)
    }

    fun getCurrent(query: CurrentQuery, handler: CurrentQuery.Handler) {
        forecastService.getCurrent(query.city, query.appId, query.units)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ handler.onSuccess(it) }, { handler.onError(DetailForecastException()) })
            .follow()
    }

    fun getFiveDays(query: FiveDaysQuery, handler: FiveDaysQuery.Handler) {
        forecastService.getFiveDays(query.city, query.appId, query.units)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handler.onSuccess(it) },
                { handler.onError(FiveDaysForecastException(it.message!!)) })
            .follow()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Dispose
    ///////////////////////////////////////////////////////////////////////////

    fun stop() {
        composite.clear()
    }

    fun destroy() {
        composite.dispose()
    }

    private fun Disposable.follow() {
        composite.add(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Companion
    ///////////////////////////////////////////////////////////////////////////

    companion object {
        private var instance: ForecastRepository? = null

        fun getInstance(): ForecastRepository? {
            if (instance == null) instance = ForecastRepository()

            return instance
        }
    }
}
