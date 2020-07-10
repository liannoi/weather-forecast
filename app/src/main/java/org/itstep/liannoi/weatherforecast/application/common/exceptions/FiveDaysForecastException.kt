package org.itstep.liannoi.weatherforecast.application.common.exceptions

class FiveDaysForecastException(message: String) : Exception(message) {
    constructor() : this("Failed to get five-day weather forecast from remote REST service.")
}