package com.example.sig.retrofit

import com.example.sig.models.EtatRoute
import com.example.sig.models.result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ParcService {

    @GET("parcview")
    suspend fun getParc(): result

    @GET("parcroute")
    suspend fun getOneRoute(@Query("route") numRoute: Int, @Query("mobile")numMobile: Int) : EtatRoute


}