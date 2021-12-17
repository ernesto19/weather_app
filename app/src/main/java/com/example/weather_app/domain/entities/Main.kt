package com.example.weather_app.domain.entities

data class Main(
    var temperature: Int? = 0,
    var minimumTemperature: Int? = 0,
    var maximumTemperature: Int? = 0,
    var humidity: Double? = 0.0
)