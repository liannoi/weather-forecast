package org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models

import com.google.gson.annotations.SerializedName

data class CloudModel(
    @SerializedName("all") val all: Int
)
