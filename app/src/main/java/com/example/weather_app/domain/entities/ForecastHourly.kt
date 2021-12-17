package com.example.weather_app.domain.entities

data class ForecastHourly(
    var id: Int? = 0,
    var hour: String? = null,
    var temperature: Int? = 0,
    var icon: String? = null
)