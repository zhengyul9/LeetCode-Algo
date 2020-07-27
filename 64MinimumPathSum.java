/*
64.
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
//Dynamic programming: formulation: map[i][j] = map[i-1][j]+map[i][j-1];87.51,86.49
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int[][] map = new int[grid.length][grid[0].length];
        //init
        map[0][0] = grid[0][0];        
        for(int i = 1; i<grid.length;i++){
            map[i][0] = grid[i][0]+map[i-1][0];
        }
        for(int j= 1;j<grid[0].length;j++){
            map[0][j]=grid[0][j]+map[0][j-1];
        }
        //dp
        for(int i = 1; i < grid.length; i++){
            for(int j = 1; j<grid[0].length; j++){
                map[i][j] = Math.min(map[i-1][j],map[i][j-1]) + grid[i][j];
            }
        }
        return map[grid.length-1][grid[0].length-1];
    }
}