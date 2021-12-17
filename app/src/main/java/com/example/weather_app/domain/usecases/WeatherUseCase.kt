package com.example.weather_app.domain.usecases

import com.example.weather_app.domain.entities.*
import com.example.weather_app.domain.repositories.WeatherRepositoryNetwork
import com.example.weather_app.domain.repositories.WeatherRepositoryPreference

open class WeatherUseCase(
    private val weatherRepositoryNetwork: WeatherRepositoryNetwork,
    private val weatherRepositoryPreference: WeatherRepositoryPreference
) {
    open fun getWeather(latitude: Double, longitude: Double, cityCountry: String): CustomResult<WeatherData> {
        val weatherResponse = weatherRepositoryNetwork.getWeather(latitude, longitude)
        when (weatherResponse) {
            is CustomResult.OnSuccess -> {
                val weatherList = weatherRepositoryPreference.getFavoriteCitiesWeather()?.apply {
                    add(
                        weatherResponse.data.apply {
                            lat = latitude
                            lon = longitude
                            country = cityCountry
                        }
                    )
                }
                weatherRepositoryPreference.saveWeathers(weatherList ?: mutableListOf())
            }
        }
        return weatherResponse
    }

    open fun getWeatherDetail(latitude: Double, longitude: Double): CustomResult<WeatherDetail> {
        return weatherRepositoryNetwork.getWeatherDetail(latitude, longitude)
    }

    fun getWeatherByCityId(id: String) = weatherRepositoryPreference.getWeather(id)

    fun getFavoriteCitiesWeather() = weatherRepositoryPreference.getFavoriteCitiesWeather()
}