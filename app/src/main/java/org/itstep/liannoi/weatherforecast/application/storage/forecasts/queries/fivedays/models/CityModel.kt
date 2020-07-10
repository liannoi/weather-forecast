package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: CoordinateModel,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Int,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int
)
