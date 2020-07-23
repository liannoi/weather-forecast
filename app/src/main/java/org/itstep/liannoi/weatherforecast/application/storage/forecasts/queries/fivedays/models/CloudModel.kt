package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CloudModel(
    @SerializedName("all") val all: Int
) : Parcelable
