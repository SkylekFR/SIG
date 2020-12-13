package com.example.sig.dijkstra.exception

class PathNotFoundException : Exception {
    constructor() : super("Path from source to destination vertex was not found") {}
    constructor(msg: String?) : super(msg) {}
}