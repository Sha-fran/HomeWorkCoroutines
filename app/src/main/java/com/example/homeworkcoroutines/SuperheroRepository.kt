package com.example.homeworkcoroutines

import retrofit2.Response

class SuperheroRepository {
    private val api =ApiClient.client.create(ApiInterface::class.java)

    suspend fun getSuperheroList():Response<MutableList<DataClasses.Superheroes>> = api.getSuperheroes()

}
