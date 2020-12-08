package com.example.sig

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sig.models.PARC
import com.example.sig.models.Retour
import com.example.sig.retrofit.ParcRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import okhttp3.Dispatcher

class MainViewModel : ViewModel() {

    val repository = ParcRepository
        val parcList = liveData<List<PARC>>(Dispatchers.IO) {
            emit(repository.fetchParc())
        }
    fun getEtatRoute(numRoute: Int, numMobile: Int) = liveData<List<Retour>>(Dispatchers.IO) {
        emit(repository.fetchOneRoute(numRoute, numMobile))
    }


}