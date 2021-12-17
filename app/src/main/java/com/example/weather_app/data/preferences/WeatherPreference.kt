package com.example.weather_app.data.preferences

import com.example.weather_app.data.preferences.manager.PreferencesManager
import com.example.weather_app.domain.entities.WeatherData
import com.example.weather_app.domain.repositories.WeatherRepositoryPreference
import com.example.weather_app.utils.Constants.WEATHER_SP_TAG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherPreference(private val sharedPreferenceManager: PreferencesManager): WeatherRepositoryPreference {

    override fun getWeather(id: String): WeatherData? {
        val gson = Gson()
        val type = object : TypeToken<WeatherData>() {}.type
        return try {
            gson.fromJson(sharedPreferenceManager.getString("$WEATHER_SP_TAG$id"), type)
        } catch (ex: Exception){
            null
        }
    }

    override fun getFavoriteCitiesWeather(): MutableList<WeatherData>? {
        val idsList = getIdsCities()
        val weather = idsList.map {
            sharedPreferenceManager.getString("$WEATHER_SP_TAG$it")
        }
        val str = "[${weather.joinToString(",")}]"
        val gson = Gson()
        val type = object : TypeToken<List<WeatherData>>() {}.type
        return gson.fromJson(str, type)
    }

    override fun saveWeathers(weatherList: MutableList<WeatherData>) {
        val list = weatherList.sortedByDescending { it.consultHour }.distinctBy { it.id }
        list.forEach {
            saveWeather(it)
        }

        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        val idList = list.map { it.id.toString() }
        sharedPreferenceManager.setValue(WEATHER_SP_TAG, gson.toJson(idList, type))
    }

    override fun saveWeather(weather: WeatherData) {
        val gson = Gson()
        val type = object : TypeToken<WeatherData>() {}.type
        sharedPreferenceManager.setValue("$WEATHER_SP_TAG${weather.id}", gson.toJson(weather, type))
    }

    override fun getWeatherByCityId(id: String): WeatherData? {
        val gson = Gson()
        val type = object : TypeToken<WeatherData>() {}.type
        return try {
            gson.fromJson(sharedPreferenceManager.getString("$WEATHER_SP_TAG$id"), type)
        } catch (ex: Exception) {
            null
        }
    }

    private fun getIdsCities(): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return try {
            gson.fromJson(sharedPreferenceManager.getString(WEATHER_SP_TAG), type)
        } catch (ex: Exception){
            listOf()
        }
    }
}