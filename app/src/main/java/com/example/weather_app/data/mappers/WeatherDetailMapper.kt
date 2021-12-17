package com.example.weather_app.data.mappers

import com.example.weather_app.data.model.*
import com.example.weather_app.domain.entities.*

class WeatherDetailMapper {
    fun map(weatherDetailResponse: WeatherDetailResponse?): WeatherDetail {
        val weatherDetail = WeatherDetail()

        weatherDetailResponse?.let {
            weatherDetail.timezone = it.timezone
            val weatherHourlyList: MutableList<WeatherHourly> = ArrayList()
            it.hourly?.forEach { item ->
                weatherHourlyList.add(mapWeatherHourly(item))
            }
            weatherDetail.hourly = weatherHourlyList
            val weatherDailyList: MutableList<WeatherDaily> = ArrayList()
            it.daily?.forEach { item ->
                weatherDailyList.add(mapWeatherDaily(item))
            }
            weatherDetail.daily = weatherDailyList
        }

        return weatherDetail
    }

    private fun mapWeatherHourly(weatherHourlyResponse: WeatherHourlyResponse?): WeatherHourly {
        val weatherHourly = WeatherHourly()

        weatherHourlyResponse?.let {
            weatherHourly.dateTime = it.dateTime
            weatherHourly.temperature = it.temperature
            val weatherList: MutableList<Weather> = ArrayList()
            it.weather?.forEach { item ->
                weatherList.add(mapWeather(item))
            }
            weatherHourly.weather = weatherList
        }

        return weatherHourly
    }

    private fun mapWeatherDaily(weatherDailyResponse: WeatherDailyResponse?): WeatherDaily {
        val weatherDaily = WeatherDaily()

        weatherDailyResponse?.let {
            weatherDaily.dateTime = it.dateTime
            weatherDaily.humidity = it.humidity
            weatherDaily.pressure = it.pressure
            val weatherList: MutableList<Weather> = ArrayList()
            it.weather?.forEach { item ->
                weatherList.add(mapWeather(item))
            }
            weatherDaily.weather = weatherList
            weatherDaily.temperature = mapTemperatureWeather(it.temperature)
        }

        return weatherDaily
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

    private fun mapTemperatureWeather(temperatureWeatherDailyResponse: TemperatureWeatherDailyResponse?): TemperatureWeatherDaily {
        val temperatureWeather = TemperatureWeatherDaily()

        temperatureWeatherDailyResponse?.let {
            temperatureWeather.temperature = it.temperature
            temperatureWeather.minimumTemperature = it.minimumTemperature
            temperatureWeather.maximumTemperature = it.maximumTemperature
        }

        return temperatureWeather
    }
}