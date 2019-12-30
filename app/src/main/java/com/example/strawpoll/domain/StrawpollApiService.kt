package com.example.strawpoll.domain

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://10.0.2.2:5000/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface StrawpollApiService{
    @GET("strawpoll")
    fun getProperties():
            Call<String>
}

object StrawpollApi{
    val retrofitService : StrawpollApiService by lazy {
        retrofit.create(StrawpollApiService::class.java)
    }
}