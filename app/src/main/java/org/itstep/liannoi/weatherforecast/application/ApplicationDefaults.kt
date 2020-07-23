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

    // Presentation Forecasts Service

    const val ACTION_FORECASTS_FIVE_DAYS =
        "org.itstep.liannoi.weatherforecast.presentation.forecasts.list.ForecastsListActivity"

    const val ACTION_FORECASTS_FIVE_DAYS_STATUS: String = "status"
    const val ACTION_FORECASTS_FIVE_DAYS_KEYCODE_START: Int = 2
    const val ACTION_FORECASTS_FIVE_DAYS_KEYCODE_STOP: Int = 1
    const val ACTION_FORECASTS_FIVE_DAYS_RESULT: String = "result"
    const val ACTION_FORECASTS_FIVE_DAYS_CITY: String = "city"
}
