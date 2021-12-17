package com.example.weather_app.data.repository

import com.example.weather_app.data.mappers.WeatherDataMapper
import com.example.weather_app.data.mappers.WeatherDetailMapper
import com.example.weather_app.data.model.WeatherDetailResponse
import com.example.weather_app.data.model.WeatherDataResponse
import com.example.weather_app.data.network.ApiConfig
import com.example.weather_app.domain.entities.*
import com.example.weather_app.domain.repositories.WeatherRepositoryNetwork

class WeatherRepository(
    private val apiConfig: ApiConfig
): WeatherRepositoryNetwork {

    override fun getWeather(latitude: Double, longitude: Double): CustomResult<WeatherData> {
        return try {
            val callApi = apiConfig.getWeather(latitude, longitude).execute()
            when (callApi.isSuccessful) {
                true -> {
                    val response: WeatherDataResponse? = callApi.body()
                    if (response != null) {
                        CustomResult.OnSuccess(data = WeatherDataMapper().map(response))
                    } else {
                        CustomResult.OnError(CustomNotFoundError())
                    }
                }
                false -> {
                    CustomResult.OnError(CustomError(code = callApi.code(), message = callApi.message()))
                }
            }
        } catch (e: Exception) {
            CustomResult.OnError(CustomError(code = 500, message = e.message))
        }
    }

    override fun getWeatherDetail(latitude: Double, longitude: Double): CustomResult<WeatherDetail> {
        return try {
            val callApi = apiConfig.getWeatherDetail(latitude, longitude).execute()
            when (callApi.isSuccessful) {
                true -> {
                    val response: WeatherDetailResponse? = callApi.body()
                    if (response != null) {
                        CustomResult.OnSuccess(data = WeatherDetailMapper().map(response))
                    } else {
                        CustomResult.OnError(CustomNotFoundError())
                    }
                }
                false -> {
                    CustomResult.OnError(CustomError(code = callApi.code(), message = callApi.message()))
                }
            }
        } catch (e: Exception) {
            CustomResult.OnError(CustomError(code = 500, message = e.message))
        }
    }
}