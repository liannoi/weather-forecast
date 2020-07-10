package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.current

import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.common.exceptions.DetailForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.current.models.CurrentForecastModel

class CurrentQuery(val city: String, val appId: String, val units: String) {
    constructor(city: String) : this(
        city,
        ApplicationDefaults.API_KEY,
        ApplicationDefaults.API_UNITS_METRIC
    )

    interface Handler {
        fun onSuccess(forecast: CurrentForecastModel)
        fun onError(exception: DetailForecastException)
    }
}
