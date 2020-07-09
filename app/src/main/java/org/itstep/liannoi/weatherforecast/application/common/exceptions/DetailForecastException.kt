package org.itstep.liannoi.weatherforecast.application.common.exceptions;

class DetailForecastException(message: String) : Exception(message) {
    constructor() : this("Failed to get current weather forecast from remote REST service.")
}
