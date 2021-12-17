package com.example.weather_app.di

import com.example.weather_app.BuildConfig
import com.example.weather_app.data.network.ApiConfig
import com.example.weather_app.data.repository.WeatherRepository
import com.example.weather_app.domain.repositories.WeatherRepositoryNetwork
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get(), ApiConfig::class.java) }
    single<WeatherRepositoryNetwork> { WeatherRepository(get()) }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
}

fun provideApiService(retrofit: Retrofit, apiService: Class<ApiConfig>) = createService(retrofit, apiService)

fun <T> createService(retrofit: Retrofit, serviceClass: Class<T>): T = retrofit.create(serviceClass)