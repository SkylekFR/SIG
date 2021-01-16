package com.example.sig.models

data class PARC(
        var PARC_POINT: List<PARCPOINT>,
        var PARC_ROUTE: List<PARCROUTE>


){
        fun getPARCPOINTByID(ID: Int) : PARCPOINT?{

                for (point in PARC_POINT){
                        if (point.POI_ID.toInt() == ID){
                                return point
                        }
                }

                return null
        }

        fun getInteretRoute(route: PARCROUTE) : Int{
                val interetDep = getPARCPOINTByID(route.ROU_POI_ID_DEB.toInt())!!.POI_INT.toInt()
                val interetFin = getPARCPOINTByID(route.ROU_POI_ID_FIN.toInt())!!.POI_INT.toInt()

                return (interetDep + interetFin)/2
        }
}