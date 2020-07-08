package org.itstep.liannoi.weatherforecast.application.storage.forecasts.models

import com.google.gson.annotations.SerializedName

data class CoordinateModel(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)
