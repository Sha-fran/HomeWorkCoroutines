package com.example.homeworkcoroutines

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val client = Retrofit.Builder()
        .baseUrl("https://akabab.github.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
}
