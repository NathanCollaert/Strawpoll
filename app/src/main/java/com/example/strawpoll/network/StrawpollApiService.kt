package com.example.strawpoll.network

import com.example.strawpoll.network.dtos.StrawpollDTO
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:5000/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface StrawpollApiService {
    @GET("strawpoll")
    fun getProperties():
            Deferred<List<StrawpollDTO>>

    @PUT("strawpoll/{id}")
    fun updatePoll(@Path("id") pollId: Int, @Body poll: StrawpollDTO): Deferred<Response<StrawpollDTO>>
}

object StrawpollApi {
    val retrofitService: StrawpollApiService by lazy {
        retrofit.create(StrawpollApiService::class.java)
    }
}