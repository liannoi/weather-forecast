package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListModel(
    @SerializedName("dt") val dt: Int,
    @SerializedName("main") val main: MainModel,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("clouds") val clouds: CloudModel,
    @SerializedName("wind") val wind: WindModel,
    @SerializedName("sys") val sys: SystemModel,
    @SerializedName("dt_txt") val dt_txt: String
) : Parcelable {
    val iconPath: String
        get() = "https://openweathermap.org/img/wn/${this.weather[0].icon}@4x.png"
}
