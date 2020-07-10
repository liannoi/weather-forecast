package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import com.google.gson.annotations.SerializedName

data class CoordinateModel(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)
