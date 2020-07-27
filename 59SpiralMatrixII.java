/*
59.
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */
//naive algorithm, set marker on each turn. 100%, 87%
class Solution {
    public int[][] generateMatrix(int n) {
        if(n == 0){
            return new int[0][0];
        }
        int[][] res = new int[n][n];
        int i = 0;
        int j = 0;
        int counter = 0;
        int sqn = n * n;
        int iflag = 1;
        int jflag = 0;
        while(counter < sqn){
            counter++;
            if((jflag == 0 && j == n-1) || (jflag == 0 && res[i][j+1] != 0) ){
                jflag = 1; 
                iflag = 0;
            }else if(i == n-1 && j == n-1){
                jflag = -1;
                iflag = 1;
                n = n -1;
            }else if((jflag == -1 && j==0) || (jflag == -1 && j > 0 && res[i][j-1] != 0) ){
                iflag = -1;
                jflag = 1;
            }else if(iflag == -1 && res[i-1][j] != 0){
                iflag = 1;
                jflag = 0;
            }
            res[i][j] = counter;
            if(jflag == -1) j--;
            if(iflag == -1) i--;
            if(jflag == 0) j++;
            if(iflag == 0) i++;
        }
        return res;
    }
}