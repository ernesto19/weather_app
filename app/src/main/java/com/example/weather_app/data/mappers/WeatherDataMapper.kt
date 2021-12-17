package com.example.weather_app.data.mappers

import com.example.weather_app.data.model.MainResponse
import com.example.weather_app.data.model.WeatherDataResponse
import com.example.weather_app.data.model.WeatherResponse
import com.example.weather_app.domain.entities.Main
import com.example.weather_app.domain.entities.Weather
import com.example.weather_app.domain.entities.WeatherData
import java.text.SimpleDateFormat
import java.util.*

class WeatherDataMapper {
    fun map(weatherDataResponse: WeatherDataResponse?): WeatherData {
        val weatherData = WeatherData()
        val dateFormat = SimpleDateFormat("HH:mm")

        weatherDataResponse?.let {
            weatherData.id = it.id
            weatherData.main = mapMain(it.main)
            weatherData.weather = mapWeather(it.weather?.firstOrNull())
            weatherData.name = it.name
            weatherData.consultHour = dateFormat.format(Date())
        }

        return weatherData
    }

    private fun mapWeather(weatherResponse: WeatherResponse?): Weather {
        val weather = Weather()

        weatherResponse?.let {
            weather.id = it.id
            weather.base = it.base
            weather.description = it.description
            weather.icon = it.icon
        }

        return weather
    }

    private fun mapMain(mainResponse: MainResponse?): Main {
        val main = Main()

        mainResponse?.let {
            main.temperature = it.temperature?.toInt()
            main.maximumTemperature = it.maximumTemperature?.toInt()
            main.minimumTemperature = it.minimumTemperature?.toInt()
            main.humidity = it.humidity
        }

        return main
    }
}