package com.example.weather_app.domain.entities

import java.util.*

data class WeatherHourly(
    var dateTime: Int? = 0,
    var temperature: Double? = 0.0,
    var weather: List<Weather>? = null
) {
    fun toForecastHourly() = ForecastHourly(
        hour = dateTime?.toHourOfDay(),
        temperature = temperature?.toInt(),
        icon = weather?.firstOrNull()?.icon ?: ""
    )

    private fun Int.toHourOfDay(): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date(this.toLong() * 1000)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return if (hour == Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            "Ahora"
        } else {
            "$hour hrs."
        }
    }

    fun isWeatherOfDay(): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date((dateTime?.toLong() ?: 1) * 1000)
        return calendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }
}