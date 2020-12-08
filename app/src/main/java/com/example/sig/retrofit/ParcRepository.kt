package com.example.sig.retrofit

import androidx.lifecycle.MutableLiveData
import com.example.sig.models.PARC
import com.example.sig.models.Retour

object ParcRepository {
    val factory = RetrofitSingleton.backOffService

    suspend fun fetchParc() = factory.getParc().PARC

    suspend fun fetchOneRoute(numRoute: Int, numMobile: Int) = factory.getOneRoute(numRoute, numMobile).retour

}

