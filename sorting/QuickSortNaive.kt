fun<T: Comparable<T>> List<T>.quicksortNaive(): List<T> {
    if (this.size < 2) return this
    val pivot = this[this.size / 2]
    val less = this.filter { it < pivot }
    val equal = this.filter { it == pivot }
    val greater = this.filter { it > pivot }
    return less.quicksortNaive() + equal + greater.quicksortNaive()
}

fun main() {
    val first_multiple_input = readLine()!!.trimEnd().split(" ")
    val n = first_multiple_input[0].toInt()
    val k = first_multiple_input[1].toInt()
    val prices = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val result = prices.asList().quicksortNaive()
    println(result)
}
