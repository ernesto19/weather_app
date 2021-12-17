package com.example.weather_app.domain.entities

data class WeatherDetail(
    var timezone: String? = null,
    var hourly: List<WeatherHourly>? = null,
    var daily: List<WeatherDaily>? = null
)