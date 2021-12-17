package com.example.weather_app.di

import com.example.weather_app.domain.usecases.WeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    single { WeatherUseCase(get(), get()) }
}