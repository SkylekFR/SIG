package com.example.sig.retrofit

import com.example.sig.models.EtatRoute
import com.example.sig.models.result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ParcService {

    @GET("parcview")
    fun getParc(): Call<result>

    @GET("parcroute")
    fun getOneRoute(@Query("route") numRoute: Int, @Query("mobile")numMobile: Int) : Call<EtatRoute>


}