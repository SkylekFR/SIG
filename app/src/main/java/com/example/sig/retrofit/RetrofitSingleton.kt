package com.example.sig.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    private val baseURL = "https://www.makhno.fr/"
    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val backOffService: ParcService = retrofit().create(ParcService::class.java)
}