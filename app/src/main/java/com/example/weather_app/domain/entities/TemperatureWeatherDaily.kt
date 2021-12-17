package com.example.weather_app.domain.entities

data class TemperatureWeatherDaily(
    var temperature: Double? = 0.0,
    var minimumTemperature: Double? = 0.0,
    var maximumTemperature: Double? = 0.0
)