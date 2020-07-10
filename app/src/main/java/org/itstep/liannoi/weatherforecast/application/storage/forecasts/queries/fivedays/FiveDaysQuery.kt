package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays

import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.common.exceptions.FiveDaysForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models.FiveDaysForecastModel

class FiveDaysQuery(val city: String, val appId: String, val units: String) {
    constructor(city: String) : this(
        city,
        ApplicationDefaults.API_KEY,
        ApplicationDefaults.API_UNITS_METRIC
    )

    interface Handler {
        fun onSuccess(model: FiveDaysForecastModel)
        fun onError(exception: FiveDaysForecastException)
    }
}
