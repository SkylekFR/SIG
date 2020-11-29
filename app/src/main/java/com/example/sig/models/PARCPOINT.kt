package com.example.sig.models

import kotlin.math.*

data class PARCPOINT(
    val POI_ID: String,
    val POI_INT: String,
    val POI_NOM: String,
    val POI_X: String, // longitude
    val POI_Y: String, // latitude
    val POI_Z: String,
    var latitudeLambert: Double,
    var longitudeLambert: Double
){
    fun deg2Lambert() {

        val n = 0.7289686274
        val C = 11745793.39
        val e = 0.08248325676
        val Xs = 600000
        val Ys = 8199695.768

        var gamma0 = (3600*2)+(60*20)+14.025
        gamma0 = gamma0/(180*3600).toDouble()* PI

        val latitude = (POI_Y.toInt()*3600)/(180*3600)* PI
        val longitude = (POI_X.toInt()*3600)/(180*3600)* PI

        val L = 0.5*ln((1.0+sin(latitude))/(1.0-sin(latitude)))-e/2.0*ln((1.0+e*sin(latitude))/(1.0-e*sin(latitude)))
        val R = C*exp(-n*L)

        val gamma = n*(longitude-gamma0)

        this.longitudeLambert = (Xs +(R* sin(gamma)))
        this.latitudeLambert = (Ys -(R* cos(gamma)))

    }
}
