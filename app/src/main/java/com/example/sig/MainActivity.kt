package com.example.sig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.sig.models.result
import com.example.sig.retrofit.ParcService
import com.example.sig.retrofit.RetrofitSingleton
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var tv: TextView
    private var parc: result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.tv)

        val retrofit =RetrofitSingleton.getRetrofitInstance()

        retrofit.create<ParcService>().getParc()
            .enqueue(object : Callback<result>{
                override fun onResponse(call: Call<result?>, response: Response<result?>) {
                    parc = response.body()
                    tv.text = "J'ai reçu quelque chose"
                }
                override fun onFailure(call: Call<result>, t: Throwable?) {
                    tv.text = "J'ai rien"
                }
            })


    }
}