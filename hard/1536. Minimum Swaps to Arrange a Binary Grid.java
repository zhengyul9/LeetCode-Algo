/*
1536.
Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.

A grid is said to be valid if all the cells above the main diagonal are zeros.

Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.

The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).

Example 1:

Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
Output: 3
Example 2:

Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
Output: -1
Explanation: All rows are similar, swaps have no effect on the grid.
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
Output: 0
 
Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 200
grid[i][j] is 0 or 1
*/
//DP 100 100 if smaller add, if equal, add max, finally compare a and b
class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        HashSet<Integer> found = new HashSet();
        int steps = 0;
        
        int[] arr = new int[n];        
        for(int i=0; i<n; i++){
            int count = 0;
            int j = n-1;
            while(j>=0 && grid[i][j]==0) {
                count++;
                j--;
            }            
            for(int k=n-1; k>=0; k--){
                if(count>=k && !found.contains(k)){ //move from row i to row n-1-k
                    found.add(k);               
                    arr[i] = n-1-k;
                    break;
                }
            }
        }
        
        if(found.size()!=n) return -1;
        
        return bubblesort(arr);
    }
    private int bubblesort(int[] arr){
        int k=0;
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    k++;
                }
            }
        }
        
        return k;
    }
}