package org.itstep.liannoi.weatherforecast.application.storage.forecasts

import io.reactivex.Observable
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.models.ForecastModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("weather")
    fun getCurrent(
        @Query("q") city: String?,
        @Query("appid") appId: String?,
        @Query("units") units: String?
    ): Observable<ForecastModel>
}
