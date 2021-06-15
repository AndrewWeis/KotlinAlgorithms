import kotlin.math.max

var maxRegionList: Int = 0
var n: Int = 0
var m: Int = 0
var grid = arrayOf<Array<Int>>()

fun dfs(i: Int, j: Int) {
    grid[i][j] = 0
    maxRegionList++

    if (i-1 >= 0 && grid[i-1][j] == 1) { dfs(i-1, j) } // up
    if (j+1 < m && grid[i][j+1] == 1) { dfs(i, j+1) } // right
    if (i+1 < n && grid[i+1][j] == 1) { dfs(i+1, j) } // down
    if (j-1 >= 0 && grid[i][j-1] == 1) { dfs(i, j-1) } // left
    if (i-1 >= 0 && j+1 < m && grid[i-1][j+1] == 1) { dfs(i-1, j+1) } // up right diagonal
    if (i+1 < n && j+1 < m && grid[i+1][j+1] == 1) { dfs(i+1, j+1) } //  down right diagonal
    if (i+1 < n && j-1 >= 0 && grid[i+1][j-1] == 1) { dfs(i+1, j-1) } // up left diagonal
    if (i-1 >= 0 && j-1 >= 0 && grid[i-1][j-1] == 1) { dfs(i-1, j-1) } // down left diagonal
}

fun maxRegion(): Int {
    var maxRegion = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (grid[i][j] == 1) {
                dfs(i, j)
                maxRegion = max(maxRegionList, maxRegion)
                maxRegionList = 0
            }
        }
    }
    return maxRegion
}

fun main() {
    n = readLine()!!.trim().toInt()
    m = readLine()!!.trim().toInt()
    grid = Array(n) { Array(m) { 0 } }

    for (i in 0 until n) {
        grid[i] = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
    }

    val res = maxRegion()
    println(res)
}

/** Print the number of cells in the largest region in the given matrix.

Input
4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0

Output
5
*/
