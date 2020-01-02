package com.example.weatherandroid.model

import com.example.weatherandroid.utils.apiKey
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?appid=${apiKey}&units=metric&q=Sydney")
    fun getWeather(): Single<RootObject>
}