package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WindModel(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int
) : Parcelable
