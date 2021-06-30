// Solved problem from https://www.hackerrank.com/
// BFS: Shortest Reach in a Graph

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

interface Graph<T> {
    fun createVertex(data: T): Vertex<T>

    fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)

    fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }

    fun add(edge: EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        when (edge) {
            EdgeType.DIRECTED -> addDirectedEdge(source, destination, weight)
            EdgeType.UNDIRECTED -> addUndirectedEdge(source, destination, weight)
        }
    }

    fun edges(source: Vertex<T>): ArrayList<Edge<T>>

    fun weight(source: Vertex<T>, destination: Vertex<T>): Double?

    fun breadthFirstSearchQueue(source: Vertex<T>): Array<Int>
}

enum class EdgeType {
    DIRECTED,
    UNDIRECTED
}

val graph = AdjacencyList<Int>()

data class Edge<T>(
    val source: Vertex<T>,
    val destination: Vertex<T>,
    val weight: Double? = null
)

data class Vertex<T>(
    val index: Int,
    val data: T
)

class AdjacencyList<T> : Graph<T> {
    private val adjacencies: HashMap<Vertex<T>, ArrayList<Edge<T>>> = HashMap()

    fun clearData() = adjacencies.clear()

    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(adjacencies.count(), data)
        adjacencies[vertex] = arrayListOf()
        return vertex
    }

    override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        val edge = Edge(source, destination, weight)
        adjacencies[source]?.add(edge)
    }

    override fun edges(source: Vertex<T>): ArrayList<Edge<T>> = adjacencies[source] ?: arrayListOf()

    override fun weight(source: Vertex<T>, destination: Vertex<T>): Double? {
        return edges(source).firstOrNull { it.destination == destination }?.weight
    }

    override fun breadthFirstSearchQueue(source: Vertex<T>): Array<Int> {
        var moveCount = 0
        var nodesLeft = 1
        var nodesNext = 0
        val queue = ArrayDeque<Vertex<T>>()
        val enqueued = ArrayList<Vertex<T>>()
        val visited = ArrayList<Vertex<T>>()
        val result = Array(1000){-1}

        queue.add(source)
        enqueued.add(source)
        while (true) {
            val vertex = if (!queue.isEmpty()) queue.removeFirst() else break
            visited.add(vertex)
            result[vertex.index+1] = 6*moveCount
            val neighborEdges = edges(vertex)
            neighborEdges.forEach {
                if (!enqueued.contains(it.destination)) {
                    queue.add(it.destination)
                    enqueued.add(it.destination)
                    nodesNext++
                }
            }

            nodesLeft--
            if (nodesLeft == 0) {
                nodesLeft = nodesNext
                nodesNext = 0
                moveCount++
            }
        }
        return result
    }
}

fun main() {
    val q = readLine()!!.trim().toInt()
    val vertexes: ArrayList<Vertex<Int>> = arrayListOf()

    repeat(q) {
        val (n, m) = readLine()!!.trim().split(" ").map { it.toInt() }
        for (j in 1..n) { vertexes.add(graph.createVertex(j)) }
        for (j in 0 until m) {
            val (sour, dest) = readLine()!!.trim().split(" ").map { it.toInt() }
            graph.add(EdgeType.UNDIRECTED, vertexes[sour-1], vertexes[dest-1], 6.0)
        }
        val startVertex = readLine()!!.trim().toInt()
        val result: Array<Int> = graph.breadthFirstSearchQueue(vertexes[startVertex-1])
        (1..n).forEach {
            if (it != startVertex)
                print("${result[it]} ")
        }
        println()

        graph.clearData()
        vertexes.clear()
    }
}
