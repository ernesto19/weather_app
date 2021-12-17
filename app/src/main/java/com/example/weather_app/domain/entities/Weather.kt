package com.example.weather_app.domain.entities

data class Weather(
    var id: Int? = 0,
    var base: String? = null,
    var description: String? = null,
    var icon: String? = null
)