import java.util.*
import kotlin.collections.ArrayList

/**
 * createVertex(): Creates a vertex and adds it to the graph.
 * addDirectedEdge(): Adds a directed edge between two vertices.
 * addUndirectedEdge(): Adds an undirected (or bi-directional) edge between two vertices.
 * add(): Uses EdgeType to add either a directed or undirected edge between two vertices.
 * edges(): Returns a list of outgoing edges from a specific vertex.
 * weight(): Returns the weight of the edge between two vertices.
 */

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

    fun depthFirstSearchStack(source: Vertex<T>): ArrayList<Vertex<T>> {
        val stack = Stack<Vertex<T>>()
        val visited = arrayListOf<Vertex<T>>()
        val pushed = mutableSetOf<Vertex<T>>()
        stack.push(source)
        pushed.add(source)
        visited.add(source)

        outer@ while (true) {
            if (stack.isEmpty()) break
            val vertex: Vertex<T> = stack.peek()!!
            val neighbors = graph.edges(vertex as Vertex<String>)
            if (neighbors.isEmpty()) {
                stack.pop()
                continue
            }
            for (i in 0 until neighbors.size) {
                val destination: Vertex<T> = neighbors[i].destination as Vertex<T>
                if (destination !in pushed) {
                    stack.push(destination)
                    pushed.add(destination)
                    visited.add(destination)
                    continue@outer
                }
            }
            stack.pop()
        }
        return visited
    }

    fun depthFirstSearchRecursive(start: Vertex<T>): ArrayList<Vertex<T>> {
        val visited = arrayListOf<Vertex<T>>()
        val pushed = mutableSetOf<Vertex<T>>()
        depthFirstSearchHelper(start, visited, pushed)
        return visited
    }

    fun depthFirstSearchHelper(source: Vertex<T>, visited: ArrayList<Vertex<T>>, pushed: MutableSet<Vertex<T>>) {
        pushed.add(source)
        visited.add(source)
        val neighbors = edges(source)
        neighbors.forEach {
            if (it.destination !in pushed) {
                depthFirstSearchHelper(it.destination, visited, pushed)
            }
        }
    }

    fun hasCycle(source: Vertex<T>): Boolean {
        val pushed = arrayListOf<Vertex<T>>()
        return hasCycle(source, pushed.toMutableSet())
    }

    fun hasCycle(source: Vertex<T>, pushed: MutableSet<Vertex<T>>): Boolean {
        pushed.add(source)
        val neighbors = edges(source)
        neighbors.forEach {
            if (it.destination !in pushed && hasCycle(it.destination, pushed)) {
                return true
            } else if (it.destination in pushed) {
                return true
            }
        }
        pushed.remove(source)
        return false
    }

    fun breadthFirstSearchQueue(source: Vertex<T>): ArrayList<Vertex<T>> {
        val queue = ArrayDeque<Vertex<T>>()
        val enqueued = ArrayList<Vertex<T>>()
        val visited = ArrayList<Vertex<T>>()

        queue.add(source)
        enqueued.add(source)
        while (true) {
           // val vertex = queue.removeFirst() ?: break
            val vertex = if (!queue.isEmpty()) queue.removeFirst() else break
            visited.add(vertex) // 3
            val neighborEdges = edges(vertex) // 4
            neighborEdges.forEach {
                if (!enqueued.contains(it.destination)) { // 5
                    queue.add(it.destination)
                    enqueued.add(it.destination)
                }
            }
        }
        return visited
    }

    fun breadthFirstSearchRecursive(source: Vertex<T>): ArrayList<Vertex<T>> {
        val queue = ArrayDeque<Vertex<T>>()
        val enqueued = arrayListOf<Vertex<T>>()
        val visited = arrayListOf<Vertex<T>>()
        queue.add(source)
        enqueued.add(source)
        breadthFirstSearchHelper(queue, enqueued, visited)
        return visited
    }

    private fun breadthFirstSearchHelper(queue: ArrayDeque<Vertex<T>>, enqueued: ArrayList<Vertex<T>>, visited: ArrayList<Vertex<T>>) {
        val vertex = if (!queue.isEmpty()) queue.removeFirst() else return
        visited.add(vertex)
        val neighborEdges = edges(vertex)
        neighborEdges.forEach {
            if (!enqueued.contains(it.destination)) {
                queue.add(it.destination)
                enqueued.add(it.destination)
            }
        }
        breadthFirstSearchHelper(queue, enqueued, visited)
    }

    val allVertices: ArrayList<Vertex<T>>
    fun isDisconnected(): Boolean {
        val firstVertex = allVertices.firstOrNull() ?: return false
        val visited = breadthFirstSearchQueue(firstVertex)
        allVertices.forEach { if (!visited.contains(it)) return true }
        return false
    }
}

enum class EdgeType {
    DIRECTED,
    UNDIRECTED
}