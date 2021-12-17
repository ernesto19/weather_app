package com.example.weather_app.application

import android.app.Application
import com.example.weather_app.R
import com.example.weather_app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@WeatherApplication)
            modules(appModules)
        }

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder().setDefaultFontPath("font/Lato-Light.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
        )
    }
}