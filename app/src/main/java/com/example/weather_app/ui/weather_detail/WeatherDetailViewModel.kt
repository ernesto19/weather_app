package com.example.weather_app.ui.weather_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_app.core.DispatcherProvider
import com.example.weather_app.domain.entities.CustomResult
import com.example.weather_app.domain.entities.Forecast
import com.example.weather_app.domain.entities.ForecastHourly
import com.example.weather_app.domain.entities.WeatherData
import com.example.weather_app.domain.usecases.WeatherUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherDetailViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val dispatcherProvider: DispatcherProvider = DispatcherProvider()
): ViewModel() {
    val weatherForecastListLiveData = MutableLiveData<MutableList<Forecast>?>()
    val weatherForecastHourlyListLiveData = MutableLiveData<MutableList<ForecastHourly>?>()
    val weatherDataLiveData = MutableLiveData<WeatherData?>()
    val dataLoadingLiveData = MutableLiveData<Boolean>(false)

    fun getWeatherDetail(latitude: Double, longitude: Double) {
        dataLoadingLiveData.postValue(true)
        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, _ ->  }) {
            when (val response = weatherUseCase.getWeatherDetail(latitude, longitude)) {
                is CustomResult.OnSuccess -> {
                    dataLoadingLiveData.postValue(false)
                    val forecastList: MutableList<Forecast> = ArrayList()
                    val forecastHourlyList: MutableList<ForecastHourly> = ArrayList()
                    response.data.daily?.forEach { forecastList.add(it.toForecast()) }
                    response.data.hourly?.filter { it.isWeatherOfDay() }?.forEach { forecastHourlyList.add(it.toForecastHourly()) }

                    weatherForecastListLiveData.postValue(forecastList)
                    weatherForecastHourlyListLiveData.postValue(forecastHourlyList)

                    return@launch
                }
                is CustomResult.OnError -> {
                    dataLoadingLiveData.postValue(false)
                }
            }
        }
    }

    fun getWeatherByCityId(id: String) {
        weatherUseCase.getWeatherByCityId(id)?.let {
            weatherDataLiveData.postValue(it)
        }
    }
}