/*
986.
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:


Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 
Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
*/
//two pointers method 80, 77
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0)
            return new int[][]{};
        List<int[]> res = new ArrayList<>();
    
        int i = 0, j = 0;
        int startMax, endMin;
        while(i < A.length && j < B.length){
            startMax = Math.max(A[i][0], B[j][0]);
            endMin = Math.min(A[i][1], B[j][1]);
        
            if(endMin >= startMax)
                res.add(new int[]{startMax, endMin});
        
            if(A[i][1] == endMin) i++;
            if(B[j][1] == endMin) j++;
        }    
        return res.toArray(new int[res.size()][2]);
    }
}
// line swapping algo, TLE but should be correct.
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A.length == 0 || B.length == 0) return new int[0][0];
        List<int[]> res = new ArrayList<>();
        int max = Math.max(A[A.length-1][1], B[B.length-1][1]);
        int i = 0, j = 0;
        int flag = 0;
        int[] arr = new int[2];
        for(int k = 0; k <= max; k++){
            if(i >= A.length || j >= B.length) continue;
            if(k < A[i][0] || k < B[j][0]) continue;
            else if(k <= A[i][1] && k <= B[j][1]){
                if(flag == 0){//create a new one flag;
                    res.add(arr);
                    arr = new int[2];
                    arr[0] = k;
                    arr[1] = k;
                    flag = 1;
                } 
                else{
                    arr[1] = k;
                }
                if(k == A[i][1]) {i++; flag = 0; k--;}
                else if(k == B[j][1]) {j++; flag = 0; k--;}
            }
            else if(k > A[i][1]){
                i++;
                k--;
                flag = 0;
            }
            else if(k > B[j][1]){
                j++;
                k--;
                flag = 0;
            }
        }
        if(arr.length != 0) res.add(arr);
        res.remove(0);
        int[][] res1 = new int[res.size()][2];
        res1 = res.toArray(res1);
        return res1;
    }
}