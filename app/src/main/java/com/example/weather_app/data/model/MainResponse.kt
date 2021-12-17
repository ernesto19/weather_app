package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("temp")
    val temperature: Double? = 0.0,

    @SerializedName("temp_min")
    val minimumTemperature: Double? = 0.0,

    @SerializedName("temp_max")
    val maximumTemperature: Double? = 0.0,

    @SerializedName("humidity")
    val humidity: Double? = 0.0
)