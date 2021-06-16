val graph = AdjacencyList<String>()
//val graph = AdjacencyMatrix<String>()

fun main() {
    val singapore = graph.createVertex("Singapore")
    val tokyo = graph.createVertex("Tokyo")
    val hongKong = graph.createVertex("Hong Kong")
    val detroit = graph.createVertex("Detroit")
    val sanFrancisco = graph.createVertex("San Francisco")
    val washingtonDC = graph.createVertex("Washington DC")
    val austinTexas = graph.createVertex("Austin Texas")
    val seattle = graph.createVertex("Seattle")
    graph.add(EdgeType.UNDIRECTED, singapore, hongKong, 300.0)
    graph.add(EdgeType.UNDIRECTED, singapore, tokyo, 500.0)
    graph.add(EdgeType.UNDIRECTED, hongKong, tokyo, 250.0)
    graph.add(EdgeType.UNDIRECTED, tokyo, detroit, 450.0)
    graph.add(EdgeType.UNDIRECTED, tokyo, washingtonDC, 300.0)
    graph.add(EdgeType.UNDIRECTED, hongKong, sanFrancisco, 600.0)
    graph.add(EdgeType.UNDIRECTED, detroit, austinTexas, 50.0)
    graph.add(EdgeType.UNDIRECTED, austinTexas, washingtonDC, 292.0)
    graph.add(EdgeType.UNDIRECTED, sanFrancisco, washingtonDC,337.0)
    graph.add(EdgeType.UNDIRECTED, washingtonDC, seattle, 277.0)
    graph.add(EdgeType.UNDIRECTED, sanFrancisco, seattle, 218.0)
    graph.add(EdgeType.UNDIRECTED, austinTexas, sanFrancisco, 297.0)
    println(graph)

    println(graph.weight(singapore, tokyo))

    println("San Francisco Outgoing Flights:")
    println("--------------------------------")
    graph.edges(sanFrancisco).forEach { edge ->
        println("from: ${edge.source.data} to: ${edge.destination.data}")
    }

    println("\nDFS:")
    val verticesDfs = graph.depthFirstSearchRecursive(singapore)
    //val verticesDfs = graph.depthFirstSearchStack(singapore)
    verticesDfs.forEach {
        print(it.data + " -> ")
    }

    println()
    when (graph.hasCycle(singapore)) {
        true -> println("\nGraph has cycle")
        false -> println("\nGraph has not cycle")
    }

    println("\nBFS:")
    val verticesBfs = graph.breadthFirstSearchRecursive(singapore)
    //val verticesBfs = graph.breadthFirstSearchQueue(singapore)
    verticesBfs.forEach {
        print(it.data + " -> ")
    }

    println("\n")
    when (graph.isDisconnected()) {
        true -> println("Graph is disconnected")
        else -> println("Graph is connected")
    }
}