package com.example.weather_app.data.network

import com.example.weather_app.data.model.WeatherDetailResponse
import com.example.weather_app.data.model.WeatherDataResponse
import com.example.weather_app.utils.Constants.OPEN_WEATHER_API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiConfig {

    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String? = "metric",
        @Query("lang") lang: String? = "es",
        @Query("appid") appId: String? = OPEN_WEATHER_API_KEY
    ): Call<WeatherDataResponse>

    @GET("onecall")
    fun getWeatherDetail(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String? = "metric",
        @Query("lang") lang: String? = "es",
        @Query("exclude") exclude: String? = "current,minutely,alerts",
        @Query("appid") appId: String? = OPEN_WEATHER_API_KEY
    ): Call<WeatherDetailResponse>
}