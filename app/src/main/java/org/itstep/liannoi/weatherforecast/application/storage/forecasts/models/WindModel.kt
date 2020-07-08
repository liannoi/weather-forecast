package org.itstep.liannoi.weatherforecast.application.storage.forecasts.models

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed") val speed: Int,
    @SerializedName("deg") val deg: Int
)
