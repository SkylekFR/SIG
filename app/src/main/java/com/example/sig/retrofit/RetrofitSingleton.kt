package com.example.sig.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {

    private var RETROFIT_INSTANCE: Retrofit? = null

    fun getRetrofitInstance(): Retrofit{

        if(RETROFIT_INSTANCE == null){
            RETROFIT_INSTANCE = Retrofit.Builder()
                .baseUrl("https://www.makhno.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return RETROFIT_INSTANCE!!
    }

}