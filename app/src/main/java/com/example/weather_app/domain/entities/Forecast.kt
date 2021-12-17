package com.example.weather_app.domain.entities

data class Forecast(
    var id: Int? = 0,
    var day: String? = null,
    var minimumTemperature: Int? = 0,
    var maximumTemperature: Int? = 0,
    var humidity: Int? = 0,
    var icon: String? = null
)