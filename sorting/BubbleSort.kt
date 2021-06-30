fun countSwaps(a: Array<Int>): Unit {
    var countSwaps = 0
    for (i in a.indices) {
        for (j in 0 until a.size-1) {
            if (a[j] > a[j+1]) {
                a[j] = a[j+1].also { a[j+1] = a[j] }
                countSwaps++
            }
        }
    }
    println("Array is sorted in $countSwaps swaps.")
    println("First Element: ${a.first()}")
    println("Last Element: ${a.last()}")
}

fun main() {
    val n = readLine()!!.trim().toInt()
    val a = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
    countSwaps(a)
}
