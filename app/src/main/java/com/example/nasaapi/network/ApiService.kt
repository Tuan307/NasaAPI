package com.example.nasaapi.network

import com.example.nasaapi.model.DAY.Day
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



    @GET("planetary/apod")
    suspend fun getImageOfTheDay(
        @Query("api_key") api: String,
        @Query("date") data: String
    ):Response<Day>
}