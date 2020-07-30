/*
1020. 
Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)

A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.

Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:

Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: 
There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
Example 2:

Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: 
All 1s are either on the boundary or can reach the boundary.
 

Note:

1 <= A.length <= 500
1 <= A[i].length <= 500
0 <= A[i][j] <= 1
All rows have the same size.
*/
// DFS 40 11
class Solution {
    public int numEnclaves(int[][] A) {
        for(int i = 0; i < A.length; i++){
            dfs(A, i, 0);
        }
        for(int j = 0; j < A[0].length; j++){
            dfs(A, 0, j);
        }
        for(int i = 0; i < A.length; i++){
            dfs(A, i, A[0].length-1);
        }
        for(int j = 0; j < A[0].length; j++){
            dfs(A, A.length-1, j);
        }
        int count = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++){
                if(A[i][j] == 1) count++;
            }
        }
        return count;
    }
    
    private void dfs(int[][] a, int i, int j){
        if(i >= a.length || i < 0 || j >= a[0].length || j < 0 || a[i][j] == 0){
            return;
        } 
        a[i][j] = 0;
        dfs(a, i-1, j);
        dfs(a, i+1, j);
        dfs(a, i, j-1);
        dfs(a, i, j+1);
    }
}