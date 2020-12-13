package com.example.sig.dijkstra.model

class Edge(val source: Vertex<*>, val destination: Vertex<*>, val weight: Int) {
    override fun toString(): String {
        return "$source -($weight)- $destination"
    }
}