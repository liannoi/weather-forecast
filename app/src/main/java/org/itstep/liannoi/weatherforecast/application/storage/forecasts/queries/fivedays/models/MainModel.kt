package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainModel(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feels_like: Double,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("sea_level") val sea_level: Int,
    @SerializedName("grnd_level") val grnd_level: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_kf") val temp_kf: Double
) : Parcelable
