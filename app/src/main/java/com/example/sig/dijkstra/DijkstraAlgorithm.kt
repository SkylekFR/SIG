package com.example.sig.dijkstra

import com.example.sig.dijkstra.exception.PathNotFoundException
import com.example.sig.dijkstra.model.Edge
import com.example.sig.dijkstra.model.Graph
import com.example.sig.dijkstra.model.Vertex
import java.util.*

class DijkstraAlgorithm(graph: Graph) {
    private val edges: List<Edge>
    private var settledNodes: MutableSet<Vertex<*>?>? = null
    private var unSettledNodes: MutableSet<Vertex<*>?>? = null
    private var predecessors: MutableMap<Vertex<*>?, Vertex<*>?>? = null
    private var distance: MutableMap<Vertex<*>?, Int?>? = null
    private var source: Vertex<*>? = null

    @Throws(PathNotFoundException::class)
    fun execute(source: Vertex<*>?): DijkstraAlgorithm {
        this.source = source
        settledNodes = HashSet()
        unSettledNodes = HashSet()
        distance = HashMap()
        predecessors = HashMap()
        (distance as HashMap<Vertex<*>?, Int?>)[source] = 0
        (unSettledNodes as HashSet<Vertex<*>?>).add(source)
        while ((unSettledNodes as HashSet<Vertex<*>?>).size > 0) {
            val node = getMinimum(unSettledNodes)
            (settledNodes as HashSet<Vertex<*>?>).add(node)
            (unSettledNodes as HashSet<Vertex<*>?>).remove(node)
            findMinimalDistances(node)
        }
        return this
    }

    @Throws(PathNotFoundException::class)
    private fun findMinimalDistances(node: Vertex<*>?) {
        val adjacentNodes = getNeighbors(node)
        for (target in adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(
                    node,
                    target
                )
            ) {
                distance!![target] = getShortestDistance(node) + getDistance(node, target)
                predecessors!![target] = node
                unSettledNodes!!.add(target)
            }
        }
    }

    private fun getNeighbors(node: Vertex<*>?): List<Vertex<*>> {
        val neighbors: MutableList<Vertex<*>> = ArrayList()
        for (edge in edges) {
            if (edge.source.equals(node) && !isSettled(edge.destination)) {
                neighbors.add(edge.destination)
            }
        }
        return neighbors
    }

    private fun getMinimum(vertexes: MutableSet<Vertex<*>?>?): Vertex<*>? {
        var minimum: Vertex<*>? = null
        for (vertex in vertexes!!) {
            if (minimum == null) {
                minimum = vertex
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex
                }
            }
        }
        return minimum
    }

    private fun isSettled(vertex: Vertex<*>): Boolean {
        return settledNodes!!.contains(vertex)
    }

    private fun getShortestDistance(destination: Vertex<*>?): Int {
        val d = distance!![destination]
        return d ?: Int.MAX_VALUE
    }

    @Throws(PathNotFoundException::class)
    fun getPath(target: Vertex<*>?): LinkedList<Vertex<*>?> {
        if (target != null) {
            val path = LinkedList<Vertex<*>?>()
            if (target.equals(source)) {
                path.add(target)
                return path
            }
            var step = target
            if (predecessors!![step] == null) throw PathNotFoundException()
            path.add(step)
            while (predecessors!![step] != null) {
                step = predecessors!![step]
                path.add(step)
            }
            path.reverse()
            return path
        }
        throw PathNotFoundException()
    }

    @Throws(PathNotFoundException::class)
    fun getDistance(vertex: Vertex<*>?): Int {
        require(!(vertex == null || distance == null)) { "Destination vertex can not be null" }
        if (distance!![vertex] == null) {
            throw PathNotFoundException()
        }
        return distance!![vertex]!!
    }

    @Throws(PathNotFoundException::class)
    private fun getDistance(from: Vertex<*>?, to: Vertex<*>?): Int {
        for (edge in edges) {
            if (edge.source.equals(from) && edge.destination.equals(to)) {
                return edge.weight
            }
        }
        throw PathNotFoundException()
    }

    @Throws(PathNotFoundException::class)
    fun getShortestPathLength(from: Vertex<*>?, to: Vertex<*>?): Int {
        require(!(from == null || to == null)) { "Source and destination vertices can not be null" }
        return getPath(to).size
    }

    @Throws(PathNotFoundException::class)
    fun getShortestDistance(from: Vertex<*>?, to: Vertex<*>?): Int {
        require(!(from == null || to == null)) { "Source and destination vertices can not be null" }
        val path = getPath(to)
        var distance = -1
        for (i in 1 until path.size) distance += getDistance(path[i - 1], path[i])
        return distance
    }

    init {
        edges = ArrayList(graph.edges)
    }
}