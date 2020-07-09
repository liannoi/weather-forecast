package org.itstep.liannoi.weatherforecast.application.storage.forecasts

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.common.exceptions.DetailForecastException
import org.itstep.liannoi.weatherforecast.application.common.storage.AbstractRepository
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.DetailQuery
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ForecastRepository private constructor() : AbstractRepository() {
    private val forecastService: ForecastService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApplicationDefaults.ENDPOINT_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastService = retrofit.create(ForecastService::class.java)
    }

    fun current(query: DetailQuery, handler: DetailQuery.Handler) {
        forecastService.getCurrent(query.city, query.appId, query.units)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ handler.onSuccess(it) }, { handler.onError(DetailForecastException()) })
            .follow()
    }

    companion object {
        private var instance: ForecastRepository? = null

        fun getInstance(): ForecastRepository? {
            if (instance == null) instance = ForecastRepository()

            return instance
        }
    }
}
