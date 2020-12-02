package com.example.sig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.*
import com.example.sig.models.EtatRoute
import com.example.sig.models.PARC
import com.example.sig.models.result
import com.example.sig.retrofit.ParcService
import com.example.sig.retrofit.RetrofitSingleton
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var tv: TextView
    private var resultParcComplet: result? = null
    private var parc: PARC? = null
    private var retourRoute: EtatRoute? = null

/*    private fun initAndGetParc() {
        RetrofitSingleton.getRetrofitInstance().create<ParcService>().getParc()
                .enqueue(object : Callback<result>{
                 override fun onResponse(call: Call<result?>, response: Response<result?>) {
                    resultParcComplet = response.body()
                    parc = resultParcComplet!!.PARC[0] // je choppe les parcpoint comme cela (je recup le parc qui contient juste les PARCPONT)
                    parc?.PARC_ROUTE  = resultParcComplet!!.PARC[1].PARC_ROUTE // puis ensuite je lui ajoute les parcroute avec le membre du parc qui cotient juste les PARCROUTE
                    tv.text = "J'ai reçu quelque chose"
                }
                override fun onFailure(call: Call<result>, t: Throwable?) {
                    tv.text = "J'ai rien \n ${t.toString()}"
                }
                })
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.tv)

        //initAndGetParc()

        RetrofitSingleton.getRetrofitInstance().create<ParcService>().getParc()
            .enqueue(object : Callback<result>{
                override fun onResponse(call: Call<result?>, response: Response<result?>) {
                    resultParcComplet = response.body()
                    parc = resultParcComplet!!.PARC[0] // je choppe les parcpoint comme cela (je recup le parc qui contient juste les PARCPONT)
                    parc?.PARC_ROUTE  = resultParcComplet!!.PARC[1].PARC_ROUTE // puis ensuite je lui ajoute les parcroute avec le membre du parc qui cotient juste les PARCROUTE
                    tv.text = "J'ai reçu quelque chose"
                }
                override fun onFailure(call: Call<result>, t: Throwable?) {
                    tv.text = "J'ai rien \n ${t.toString()}"
                }
            })

        RetrofitSingleton.getRetrofitInstance().create<ParcService>().getOneRoute(numRoute = 1,numMobile = 250)
            .enqueue(object : Callback<EtatRoute>{
                override fun onResponse(call: Call<EtatRoute>, response: Response<EtatRoute>) {
                    retourRoute = response.body()
                    tv.text = "J'ai reçu quelque chose"
                }

                override fun onFailure(call: Call<EtatRoute>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

    }
}