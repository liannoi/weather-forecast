package org.itstep.liannoi.weatherforecast.application

object ApplicationDefaults {
    const val ENDPOINT_BASE = "http://api.openweathermap.org/data/2.5/"
    const val API_KEY = "d1bd10872a0a7d4757bd4670cbc32ef4"
    const val API_UNITS_METRIC = "metric"
    const val ENDPOINT_FORECASTS_CURRENT = "weather"
    const val ENDPOINT_FORECASTS_FIVE_DAYS = "forecast"

    const val EXTRA_FORECASTS_FIVE_DAYS =
        "org.itstep.liannoi.weatherforecast.presentation.forecasts.detail.FORECAST"

    const val COUNT_CARDS_FORECASTS_FIVE_DAYS = 2
}
