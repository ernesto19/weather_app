package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class WeatherDailyResponse(
    @SerializedName("dt")
    val dateTime: Int? = 0,

    @SerializedName("temp")
    val temperature: TemperatureWeatherDailyResponse? = null,

    @SerializedName("weather")
    val weather: List<WeatherResponse>? = null,

    @SerializedName("humidity")
    val humidity: Int? = 0,

    @SerializedName("pressure")
    val pressure: Int? = 0
)