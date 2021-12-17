package com.example.weather_app.domain.repositories

import com.example.weather_app.domain.entities.WeatherData

interface WeatherRepositoryPreference {
    fun getFavoriteCitiesWeather(): MutableList<WeatherData>?
    fun saveWeathers(weatherList: MutableList<WeatherData>)
    fun getWeather(id: String): WeatherData?
    fun saveWeather(weather: WeatherData)
    fun getWeatherByCityId(id: String): WeatherData?
}