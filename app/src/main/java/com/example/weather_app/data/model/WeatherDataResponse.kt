package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class WeatherDataResponse(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("weather")
    val weather: List<WeatherResponse>? = null,

    @SerializedName("main")
    val main: MainResponse? = null,

    @SerializedName("name")
    val name: String? = null
)