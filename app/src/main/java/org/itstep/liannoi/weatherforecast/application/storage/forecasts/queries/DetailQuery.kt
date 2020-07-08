package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries

import org.itstep.liannoi.weatherforecast.application.storage.forecasts.models.ForecastModel

data class DetailQuery(val city: String, val appId: String, val units: String) {
    interface Handler {
        fun onSuccess(forecast: ForecastModel)

        // TODO: Replace with custom specific Exception.
        fun onError(e: Exception)
    }
}
