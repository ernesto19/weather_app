package com.example.weather_app.domain.entities

data class WeatherData(
    var id: Int? = 0,
    var weather: Weather? = null,
    var main: Main? = null,
    var name: String? = null,
    var lat: Double? = 0.0,
    var lon: Double? = 0.0,
    var consultHour: String? = null,
    var country: String? = null
)