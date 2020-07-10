package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int
)
