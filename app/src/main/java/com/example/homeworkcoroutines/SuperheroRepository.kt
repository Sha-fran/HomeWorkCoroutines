package com.example.homeworkcoroutines

import retrofit2.Response
import retrofit2.Retrofit

class SuperheroRepository (client: Retrofit){
    private val apiInterface = client.create(ApiInterface::class.java)

    suspend fun getSuperhero():Response<MutableList<DataClasses.Superheroes>> = apiInterface.getSuperheroes()
}
