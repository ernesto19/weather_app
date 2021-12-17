package com.example.weather_app.domain.entities

import java.text.SimpleDateFormat
import java.util.*

data class WeatherDaily(
    var dateTime: Int? = 0,
    var temperature: TemperatureWeatherDaily? = null,
    var weather: List<Weather>? = null,
    var humidity: Int? = 0,
    var pressure: Int? = 0
) {
    fun toForecast() = Forecast(
        day = dateTime?.toDayOfWeek(),
        minimumTemperature = temperature?.minimumTemperature?.toInt(),
        maximumTemperature = temperature?.maximumTemperature?.toInt(),
        humidity = humidity,
        icon = weather?.firstOrNull()?.icon ?: ""
    )

    private fun Int.toDayOfWeek(): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date(this.toLong() * 1000)
        val dateFormat = SimpleDateFormat("EEEE", Locale("es", "ES"))
        return if (calendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
            return "Hoy"
        } else {
            dateFormat.format(calendar.time).capitalize().replace(".", "")
        }
    }
}