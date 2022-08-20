package com.example.tbc_couce_21.network

import com.example.tbc_couce_21.models.InfoModel
import com.example.tbc_couce_21.network.RetrofitClient.Companion.BASE_URL
import com.example.tbc_couce_21.network.RetrofitClient.Companion.moshi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitClient {


    @GET("users")
    suspend fun getInfo(@Query("page") page: Int): Response<InfoModel>

    companion object {

        private const val BASE_URL = "https://reqres.in/api/"
        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        fun invoke(): RetrofitClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RetrofitClient::class.java)

    }

}
