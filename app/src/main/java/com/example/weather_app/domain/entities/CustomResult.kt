package com.example.weather_app.domain.entities

sealed class CustomResult<out T> {
    data class OnSuccess<out T>(val data: T): CustomResult<T>()
    data class OnError<out T>(val error: CustomError): CustomResult<T>()
}

open class CustomError (
    private val code: Int? = 0,
    private val message: String? = null
) {
    override fun toString(): String {
        return message ?: ""
    }
}

class CustomNotFoundError(code: Int? = 0, message: String? = null):
    CustomError(code, message ?: "Data not found")