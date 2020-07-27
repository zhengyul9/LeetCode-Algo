/*
240.
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/
//2 binary search 7.21, 99.33
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return false;
        int lo = 0, hi = matrix.length-1;
        int mid = 0;
        while(lo <= hi){
            mid = (lo + hi)/2;
            if(matrix[mid][0] == target)
                return true;
            if(matrix[mid][0] < target){
                lo = mid+1;
            }else{
                hi = mid-1;
            }
        }
        System.out.println("mid: "+mid);
        for(int i = 0; i <= mid; i++){
            lo = 0; hi = matrix[i].length-1;
            //System.out.print(i);
            while(lo <= hi){
                int mid1 = (lo + hi)/2;
                //System.out.println("mid1: "+ mid1);
                if(matrix[i][mid1] == target)
                    return true;
                else if(matrix[i][mid1] < target){
                    lo = mid1+1;
                }else{
                    hi = mid1-1;
                }
            }
        }
        return false;
    }
}
//100% solution due to both row column in acsending order
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int n = matrix.length, m = matrix[0].length;
        int i = 0, j = m - 1;
    
        while (i < n && j >= 0) {
            int num = matrix[i][j];
        
            if (num == target)
                return true;
            else if (num > target)
                j--;
            else
                i++;
        }   
        return false;
    }
}
// DFS
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return false;
        return dfs(matrix, target, 0, 0);
    }
    
    public boolean dfs(int[][] matrix, int target, int i, int j){
        if(matrix[i][j] == target)
            return true;
        if(i+1 < matrix.length){
            if(dfs(matrix, target, i+1, j) == true){
                return true;
            };
        }
        if(j+1 < matrix[0].length){
            if(dfs(matrix, target, i, j+1) == true){
                return true;
            };
        }
        return false;
    }
}