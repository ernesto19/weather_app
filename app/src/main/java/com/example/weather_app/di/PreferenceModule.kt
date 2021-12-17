package com.example.weather_app.di

import com.example.weather_app.data.preferences.WeatherPreference
import com.example.weather_app.data.preferences.manager.PreferencesManager
import com.example.weather_app.domain.repositories.WeatherRepositoryPreference
import org.koin.dsl.module

val preferenceModule = module {
    single { PreferencesManager(get()) }
    single<WeatherRepositoryPreference> { WeatherPreference(get()) }
}