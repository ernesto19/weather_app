package com.example.weather_app.di

import com.example.weather_app.data.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        WeatherRepository(get())
    }
}