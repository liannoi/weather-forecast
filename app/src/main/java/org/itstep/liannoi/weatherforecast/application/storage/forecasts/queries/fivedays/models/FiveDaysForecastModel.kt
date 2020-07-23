package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FiveDaysForecastModel(
    @SerializedName("cod") val cod: Int,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<ListModel>,
    @SerializedName("city") val city: CityModel
) : Parcelable
