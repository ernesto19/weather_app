package com.example.weather_app.di

import com.example.weather_app.ui.weather_consult.WeatherConsultViewModel
import com.example.weather_app.ui.weather_detail.WeatherDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WeatherConsultViewModel(get()) }
    viewModel { WeatherDetailViewModel(get()) }
}