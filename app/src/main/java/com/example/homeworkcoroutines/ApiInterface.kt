package com.example.homeworkcoroutines

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("superhero-api/api/all.json")
    suspend fun getSuperheroes():Response<MutableList<DataClasses.Superheroes>>
}
