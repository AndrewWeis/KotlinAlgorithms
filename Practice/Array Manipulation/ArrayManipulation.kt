fun arrayManipulation(n: Int, queries: Array<Array<Int>>): Long? {
    val arr = Array<Long>(n) { 0 }

    for (i in queries) {
        val startRange = i[0]-1
        val endRange = i[1]
        val add = i[2]

        arr[startRange] += add.toLong()
        if (endRange <= n-1)
            arr[endRange] -= add.toLong()
    }

    for (i in 0 until (n-1)) {
        arr[i+1] = arr[i+1] + arr[i]
    }

    return arr.maxOrNull()
}

fun main(args: Array<String>) {
    val first_multiple_input = readLine()!!.trimEnd().split(" ")

    val n = first_multiple_input[0].toInt()

    val m = first_multiple_input[1].toInt()

    val queries = Array<Array<Int>>(m, { Array<Int>(3, { 0 }) })

    for (i in 0 until m) {
        queries[i] = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
    }

    val result = arrayManipulation(n, queries)

    println(result)
}
