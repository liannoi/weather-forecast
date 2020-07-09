package org.itstep.liannoi.weatherforecast.application.storage.forecasts.models

import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("coord") val coord: CoordinateModel,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: MainModel,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: WindModel,
    @SerializedName("clouds") val clouds: CloudModel,
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: SystemModel,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
) {
    val iconPath: String
        get() = "https://openweathermap.org/img/wn/${this.weather[0].icon}@4x.png"

    fun toShortString(): String {
        return "${this.name} (${this.main.temp.toInt()} °C)"
    }
}
