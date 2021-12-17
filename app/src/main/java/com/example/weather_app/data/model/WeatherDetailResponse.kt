package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class WeatherDetailResponse(
    @SerializedName("timezone")
    val timezone: String? = null,

    @SerializedName("hourly")
    val hourly: List<WeatherHourlyResponse>? = null,

    @SerializedName("daily")
    val daily: List<WeatherDailyResponse>? = null,
)