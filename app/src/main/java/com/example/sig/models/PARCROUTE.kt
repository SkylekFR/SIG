package com.example.sig.models

data class PARCROUTE(
    val ROU_DISPO: String,
    val ROU_ID: String,
    val ROU_MOB_ID: String,
    val ROU_POI_ID_DEB: String,
    val ROU_POI_ID_FIN: String


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PARCROUTE

        if (ROU_DISPO != other.ROU_DISPO) return false
        if (ROU_ID != other.ROU_ID) return false
        if (ROU_MOB_ID != other.ROU_MOB_ID) return false
        if (ROU_POI_ID_DEB != other.ROU_POI_ID_DEB) return false
        if (ROU_POI_ID_FIN != other.ROU_POI_ID_FIN) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ROU_DISPO.hashCode()
        result = 31 * result + ROU_ID.hashCode()
        result = 31 * result + ROU_MOB_ID.hashCode()
        result = 31 * result + ROU_POI_ID_DEB.hashCode()
        result = 31 * result + ROU_POI_ID_FIN.hashCode()
        return result
    }
}