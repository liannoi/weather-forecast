package org.itstep.liannoi.weatherforecast.application.storage.forecasts

import io.reactivex.Observable
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.current.models.CurrentForecastModel
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models.FiveDaysForecastModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET(ApplicationDefaults.ENDPOINT_FORECASTS_CURRENT)
    fun getCurrent(
        @Query("q") city: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Observable<CurrentForecastModel>

    @GET(ApplicationDefaults.ENDPOINT_FORECASTS_FIVE_DAYS)
    fun getFiveDays(
        @Query("q") city: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Observable<FiveDaysForecastModel>
}
