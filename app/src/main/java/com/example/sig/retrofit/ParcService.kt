package com.example.sig.retrofit

import com.example.sig.models.result
import retrofit2.Call
import retrofit2.http.GET

interface ParcService {

    @GET("parcview")
    fun getParc(): Call<result>
}