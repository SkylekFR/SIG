package com.example.sig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.*
import com.example.sig.models.EtatRoute
import com.example.sig.models.PARC
import com.example.sig.models.result
import com.example.sig.retrofit.ParcRepository
import com.example.sig.retrofit.ParcService
import com.example.sig.retrofit.RetrofitSingleton
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var tv: TextView
    private var resultParcComplet: result? = null
    private var parc: PARC? = null
    private var retourRoute: EtatRoute? = null

    private var mViewModel = MainViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.tv)
        mViewModel.parcList.observe(this, Observer {
            tv.text = it[0].PARC_POINT[0].POI_ID
        })
        mViewModel.getEtatRoute(1, 103).observe(this, Observer {
            tv.text = tv.text.toString() + "     " +  it[0].delai
        })



    }
}