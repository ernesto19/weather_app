package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class WeatherHourlyResponse(
    @SerializedName("dt")
    val dateTime: Int? = 0,

    @SerializedName("temp")
    val temperature: Double? = 0.0,

    @SerializedName("weather")
    val weather: List<WeatherResponse>? = null
)