/*
1035. uncrossed line ( the same as longest common subsequence)
We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

Example 1:

Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
Example 2:

Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3
Example 3:

Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2
 
Note:

1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
*/
// 2d array dp 20% 5%
class Solution {
      public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length, dp[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        return dp[m][n];
    }
}
// 1d dp 20% 10%
class Solution {
      public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        if (m < n) return maxUncrossedLines(B, A);

        int dp[] = new int[n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0, prev = 0, cur; j < n; j++) {
                cur = dp[j+1];
                if (A[i] == B[j]) dp[j+1] = 1+prev;
                else dp[j+1] = Math.max(dp[j+1], dp[j]);
                prev = cur;
            }
        }
        return dp[n];
    }
}
// DFS with dp memo  27% 68%
class Solution {
    Integer[][] dp = null;
    public int maxUncrossedLines(int[] A, int[] B) {
        dp = new Integer[A.length][B.length];
        return helper(A, 0, B, 0);
    }
    private int helper(int[] A, int i, int[] B, int j){
        if(i == A.length || j == B.length) return 0;
        if(dp[i][j] != null) return dp[i][j];
        if(A[i] == B[j]) 
            dp[i][j] = 1+helper(A, i+1, B, j+1);
        else
        	dp[i][j] = Math.max(helper(A, i+1, B, j), helper(A, i, B, j+1));
        return dp[i][j];
    }
}
// hashmap + DFS slow but work  TLE
class Solution {
    int max = 0;
    public int maxUncrossedLines(int[] A, int[] B) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < B.length; i++){
            List<Integer> t;
            if(map.containsKey(B[i])){
                t = map.get(B[i]);
            }
            else{
                t = new ArrayList<>();
            }
            t.add(i);
            map.put(B[i],t);
        }
        backtracking(A, map, 0, -1, 1);
        return max;
    }
    
    public void backtracking(int[] A, HashMap<Integer, List<Integer>> map, int astart, int bstart, int count){
        for(int i = astart; i < A.length; i++){
            if(map.containsKey(A[i])){
                List<Integer> temp = map.get(A[i]);
                for(int j = 0; j < temp.size(); j++){
                    int s = temp.get(j);
                    if(s > bstart){
                        max = Math.max(count, max);
                        backtracking(A, map, i+1, s, count+1);
                    }
                }
            }
        }
    }
}
// eaiser and faster (better) dfs
public int maxUncrossedLines(int[] A, int[] B) {
        return helper(A, 0, B, 0);
    }
    private int helper(int[] A, int i, int[] B, int j){
        if(i == A.length || j == B.length) return 0;
        int count = 0;
        if(A[i] == B[j]) 
            count = 1+helper(A, i+1, B, j+1);
        else
        	count += Math.max(helper(A, i+1, B, j), helper(A, i, B, j+1));
        return count;
    }