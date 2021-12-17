package com.example.weather_app.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class DispatcherProvider {
    open val IO: CoroutineDispatcher = Dispatchers.IO
}