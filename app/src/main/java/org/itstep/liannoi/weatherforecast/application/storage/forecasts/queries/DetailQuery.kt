package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries

import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.common.exceptions.DetailForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.models.ForecastModel

class DetailQuery(val city: String, val appId: String, val units: String) {
    constructor(city: String) : this(
        city,
        ApplicationDefaults.API_APP_ID,
        ApplicationDefaults.API_UNITS_METRIC
    )

    interface Handler {
        fun onSuccess(forecast: ForecastModel)
        fun onError(exception: DetailForecastException)
    }
}
