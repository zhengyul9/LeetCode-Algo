/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.


这道题给我们一个二维数组，让我们求矩阵中最长的递增路径，规定我们只能上下左右行走，不能走斜线或者是超过了边界。那么这道题的解法要用递归和DP来解，用DP的原因是为了提高效率，避免重复运算。我们需要维护一个二维动态数组dp，其中dp[i][j]表示数组中以(i,j)为起点的最长递增路径的长度，初始将dp数组都赋为0，当我们用递归调用时，遇到某个位置(x, y), 如果dp[x][y]不为0的话，我们直接返回dp[x][y]即可，不需要重复计算。我们需要以数组中每个位置都为起点调用递归来做，比较找出最大值。在以一个位置为起点用DFS搜索时，对其四个相邻位置进行判断，如果相邻位置的值大于上一个位置，则对相邻位置继续调用递归，并更新一个最大值，搜素完成后返回即可，参见代码如下：

*/

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int length = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                length += 1;
            }
        }
        int[][] current_distance = new int[matrix.length][matrix[0].length];
        int max = 1;
        int tmp;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                tmp = each_point(matrix, current_distance, i, j);
                if(max < tmp){
                    max = tmp;
                }
            }
        }
        return max;   
    }
    
    public static int each_point(int[][] matrix, int[][] current_distance, int row, int col){
        if(current_distance[row][col] != 0)
            return current_distance[row][col];
        int temp = 1;
        int max = 1;
        //right
        if(col + 1 < matrix[0].length){
            if(matrix[row][col] < matrix[row][col+1]){
                temp = 1 + each_point(matrix, current_distance, row, col+1);
                if(temp > max){
                    max = temp;
                }
                //current_distance[row][col] = current_distance[row][col+1] + 1;
            }
        }
        //down
        if(row + 1 < matrix.length){
            if(matrix[row][col] < matrix[row+1][col]){
                temp = 1 + each_point(matrix, current_distance, row+1, col);
                if(temp > max){
                    max = temp;
                }
            }
        }
        //top
        if(row - 1 >= 0){
            if(matrix[row][col] < matrix[row-1][col]){
                temp = 1 + each_point(matrix, current_distance, row-1, col);
                if(temp > max){
                    max = temp;
                }
            }
        }
        //left
        if(col - 1 >= 0){
            if(matrix[row][col] < matrix[row][col-1]){
                temp = 1 + each_point(matrix, current_distance, row, col-1);
                if(temp > max){
                    max = temp;
                }
            }
        }
        current_distance[row][col] = max;
        return max;
    }
}