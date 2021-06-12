fun binarySearchLinear(list: List<Int>, key: Int): Int? {
    var rangeStart = 0
    var rangeEnd = list.count()
    while (rangeStart <= rangeEnd) {
        val midIndex = rangeStart+(rangeEnd-rangeStart)/2
        when {
            list[midIndex] == key -> { return midIndex }
            list[midIndex] < key -> { rangeStart = midIndex + 1 }
            else -> { rangeEnd = midIndex - 1 }
        }
    }
    return null
}

fun binarySearchRecursive(list: List<Int>, rangeStart: Int, rangeEnd: Int, key: Int): Int? {
    if (rangeStart <= rangeEnd) {
        val midIndex = rangeStart+(rangeEnd-rangeStart)/2
        return when {
            list[midIndex] == key -> { midIndex }
            list[midIndex] > key -> { binarySearchRecursive(list, rangeStart, midIndex-1, key) }
            else -> { binarySearchRecursive(list, midIndex+1, rangeEnd, key) }
        }
    }
    return null
}

fun main() {
    val list = listOf(1,2,3,4,5,6,7,8,9,10)
    val key = 5
    val index = binarySearchLinear(list, key)
    //val index = binarySearchRecursive(list, 0, list.count(), key)
    print("Element found at index $index")
}
