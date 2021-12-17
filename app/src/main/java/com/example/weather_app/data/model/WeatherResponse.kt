package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("main")
    val base: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("icon")
    val icon: String? = null
)
