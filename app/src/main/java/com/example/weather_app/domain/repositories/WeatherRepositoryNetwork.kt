package com.example.weather_app.domain.repositories

import com.example.weather_app.domain.entities.CustomResult
import com.example.weather_app.domain.entities.WeatherData
import com.example.weather_app.domain.entities.WeatherDetail

interface WeatherRepositoryNetwork {
    fun getWeather(latitude: Double, longitude: Double): CustomResult<WeatherData>
    fun getWeatherDetail(latitude: Double, longitude: Double): CustomResult<WeatherDetail>
}