/*
835.
Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes: 

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
*/
// O(abN^2) reduce 2d to one d array. or brute force will be ez and same fast.
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Integer> LA = new ArrayList<>(),  LB = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < N * N; ++i)
            if (A[i / N][i % N] == 1)
                LA.add(i / N * 100 + i % N);
        for (int i = 0; i < N * N; ++i)
            if (B[i / N][i % N] == 1)
                LB.add(i / N * 100 + i % N);
        for (int i : LA) for (int j : LB)
                count.put(i - j, count.getOrDefault(i - j, 0) + 1);
        int res = 0;
        for (int i : count.values())
            res = Math.max(res, i);
        return res;
    }
}
//brute force 82%
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int res = 0;
        int reverseRes = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length; j++){
                int count = 0;
                int reverseCount = 0;
                for(int m = 0; m < B.length; m++){
                    for(int n = 0; n < B.length; n++){
                        if(m+i >= A.length || n+j >= A.length) break;
                        if(A[m][n] == 1 && B[m+i][n+j] == 1){
                            count++;
                        }
                        if(A[m+i][n+j] == 1 && B[m][n] == 1){
                            reverseCount++;
                        }
                    }
                }
                res = Math.max(res, count);
                reverseRes = Math.max(reverseRes, reverseCount);
            }
        }
        
        return Math.max(res,reverseRes);
    }
}