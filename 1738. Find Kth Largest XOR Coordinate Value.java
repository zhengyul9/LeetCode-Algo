/*
1738.
You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.

The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).

Find the kth largest value (1-indexed) of all the coordinates of matrix.

 

Example 1:

Input: matrix = [[5,2],[1,6]], k = 1
Output: 7
Explanation: The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
Example 2:

Input: matrix = [[5,2],[1,6]], k = 2
Output: 5
Explanation: The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.
Example 3:

Input: matrix = [[5,2],[1,6]], k = 3
Output: 4
Explanation: The value of coordinate (1,0) is 5 XOR 1 = 4, which is the 3rd largest value.
Example 4:

Input: matrix = [[5,2],[1,6]], k = 4
Output: 0
Explanation: The value of coordinate (1,1) is 5 XOR 2 XOR 1 XOR 6 = 0, which is the 4th largest value.
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
0 <= matrix[i][j] <= 106
1 <= k <= m * n
*/
//O(n^2)
class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        int[] arr = new int[matrix.length * matrix[0].length];
        int m = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(i > 0 && j > 0){
                    dp[i][j] = matrix[i][j] ^ dp[i-1][j] ^ dp[i][j-1] ^ dp[i-1][j-1];
                }
                else if(i > 0){
                    dp[i][j] = matrix[i][j] ^ dp[i-1][j];
                }
                else if(j > 0){
                    dp[i][j] = matrix[i][j] ^ dp[i][j-1];
                }
                arr[m] = dp[i][j];
                m++;
            }
        }
        Arrays.sort(arr);
        return arr[arr.length-k];
    }
}