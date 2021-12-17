package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class TemperatureWeatherDailyResponse(
    @SerializedName("day")
    val temperature: Double? = 0.0,

    @SerializedName("min")
    val minimumTemperature: Double? = 0.0,

    @SerializedName("max")
    val maximumTemperature: Double? = 0.0
)