package com.example.weather_app.ui.weather_consult

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.DispatcherProvider
import com.example.weather_app.domain.entities.CustomResult
import com.example.weather_app.domain.entities.WeatherData
import com.example.weather_app.domain.usecases.WeatherUseCase
import kotlinx.coroutines.*

class WeatherConsultViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val dispatcherProvider: DispatcherProvider = DispatcherProvider()
): ViewModel() {
    val selectedWeatherLiveData = MutableLiveData<WeatherData?>()
    val favoriteCitiesWeatherLiveData = MutableLiveData<List<WeatherData>?>()
    val errorLiveData = MutableLiveData(false)
    val emptyListLiveData = MutableLiveData(false)

    fun getWeather(latitude: Double, longitude: Double, country: String) {
        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, _ ->  }) {
            when (val response = weatherUseCase.getWeather(latitude, longitude, country)) {
                is CustomResult.OnSuccess -> {
                    selectedWeatherLiveData.postValue(response.data)
                    return@launch
                }
                is CustomResult.OnError -> {
                    errorLiveData.postValue(true)
                    return@launch
                }
            }
        }
    }

    fun getFavoriteCitiesWeather() {
        weatherUseCase.getFavoriteCitiesWeather()?.let {
            if (it.isNullOrEmpty()) {
                emptyListLiveData.postValue(true)
            } else {
                emptyListLiveData.postValue(false)
                favoriteCitiesWeatherLiveData.postValue(it)
            }
        }
    }
}