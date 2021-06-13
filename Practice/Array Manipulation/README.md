###Problem
Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each the array element between two given indices, inclusive. Once all operations have been performed, return the maximum value in the array.

###Returns
int - the maximum value in the resultant array

###Input Format
The first line contains two space-separated integers n and m, the size of the array and the number of operations.
Each of the next m lines contains three space-separated integers a,b and k, the left index, right index and summand.

###Constraints
* 3 <= n <= 10^7
* 1 <= m <= 2*10^5
* 1 <= a <= b <= n
* 0 <= k <= 10^9

###Sample input
5 3
1 2 100
2 5 100
3 4 100

###Sample output
200

###Solution
Time complexity O(m+n) ~ O(10^7)

####Example
    1   2   3   4   5   6
    0   0   0   0   0
1  100    -100  
2      100            -100
3          100    -100
---------------------------
   100 100 0 0 -100
Finally: 100 200 200 200 100

####Explanitation
When we take the starting index, we sum up. When we take the last index we shift by one to right, we subtract.
After that we sum up all the values. Then we sum up according to the formula: arr[i+1] = arr[i] + arr[i+1]
