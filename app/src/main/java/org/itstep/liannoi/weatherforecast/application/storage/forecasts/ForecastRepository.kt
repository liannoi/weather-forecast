package org.itstep.liannoi.weatherforecast.application.storage.forecasts

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.DetailQuery
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ForecastRepository private constructor() {
    private var forecastService: ForecastService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastService = retrofit.create(ForecastService::class.java)
    }

    fun current(query: DetailQuery, handler: DetailQuery.Handler) {
        forecastService.getCurrent(query.city, query.appId, query.units)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                Consumer(handler::onSuccess),
                Consumer { handler.onError(Exception(it.message)) })
    }

    companion object {
        private const val URL = "http://api.openweathermap.org/data/2.5/"
        private var instance: ForecastRepository? = null

        fun getInstance(): ForecastRepository? {
            if (instance == null) instance = ForecastRepository()

            return instance
        }
    }
}
