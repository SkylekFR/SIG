package com.example.sig.dijkstra.model

class Vertex<T>(val payload: T) {

    override fun equals(other: Any?): Boolean {
        return try {
            equals(other as Vertex<*>?)
        } catch (e: ClassCastException) {
            false
        }
    }

    fun equals(other: Vertex<*>?): Boolean {
        return payload == other!!.payload
    }

    override fun hashCode(): Int {
        return payload.hashCode()
    }

    override fun toString(): String {
        return payload.toString()
    }
}