package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.current.models

import com.google.gson.annotations.SerializedName

data class SystemModel(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int
)
