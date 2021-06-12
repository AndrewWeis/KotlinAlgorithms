import java.io.File

fun writeToFile(result: MutableList<Pair<Int, Int>>) {
    val fileName = "output.txt"
    val fileObject = File(fileName)
    fileObject.writeText("")
    fileObject.createNewFile()
    for (i in 0 until result.size) {
        fileObject.appendText(result[i].first.toString() + " " + result[i].second.toString() + "\n")
    }
}

fun iceCreamParlor(m: Int, arr: Array<Int>): Pair<Int, Int> {
    var result: Pair<Int, Int> = Pair(0, 0)
    val map = mutableMapOf<Int, Int>()
    for (i in 0..arr.size) {
        val x = arr[i]
        val y = m - x
        val j = map[y]
        if (j != null) {
            result = Pair(j + 1, i + 1)
            break
        }
        map[x] = i
    }
    return result
}

fun main() {
    val t = readLine()!!.trim().toInt()
    val res = mutableListOf<Pair<Int, Int>>()
    for (tItr in 1..t) {
        val m = readLine()!!.trim().toInt()
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
        val pairRes = iceCreamParlor(m, arr)
        res.add(pairRes)
    }
    writeToFile(res)
}
