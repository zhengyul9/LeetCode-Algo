/*
174.
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/
//iteratively dp  52 26 time complexity: O(m*n) space complexity: O(m*n)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
    
        int m = dungeon.length;
        int n = dungeon[0].length;
    
        int[][] health = new int[m][n];

        health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

        for (int i = m - 2; i >= 0; i--) {            
            health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }

        for (int j = n - 2; j >= 0; j--) {
            health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }
    
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
                health[i][j] = Math.min(right, down);
            }
        }

        return health[0][0];
    }
}
// DFS with life and cur life variables. 
class Solution {
    int min = Integer.MAX_VALUE;
    public int calculateMinimumHP(int[][] dungeon) {
        dfs(dungeon, 0, 0, 1, 1);
        return min;
    }
    
    public void dfs(int[][] d, int i, int j, int life, int cur){
        if(i > d.length-1 || j > d[0].length-1) return;
        
        if(cur + d[i][j] <= 0){
            int temp = 1 - (cur + d[i][j]);
            life += temp;  
            cur += temp;
        }
        cur = cur + d[i][j];
        
        if(i == d.length-1 && j == d[0].length-1){
            min = Math.min(min, life);
        }
        dfs(d, i, j+1, life, cur);
        dfs(d, i+1, j, life, cur);
        
    }
}
//recursive DP with memorization 
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        Integer[][] dp = new Integer[dungeon.length][dungeon[0].length];
        return calculate(dungeon, 0, 0, dp);
    }

    private int calculate(int[][] a, int i, int j, Integer[][] dp) {
        // base case
        if (i == a.length - 1 && j == a[0].length - 1)
            return a[i][j] > 0 ? 1 : -a[i][j] + 1;
        if (dp[i][j] != null)
            return dp[i][j];
        // last row
        if (i == a.length - 1)
            return dp[i][j] = Math.max(1, calculate(a, i, j + 1, dp) - a[i][j]);
        // last column
        if (j == a[0].length - 1)
            return dp[i][j] = Math.max(1, calculate(a, i + 1, j, dp) - a[i][j]);

        return dp[i][j] = Math.max(1, Math.min(calculate(a, i + 1, j, dp) - a[i][j],
                calculate(a, i, j + 1, dp) - a[i][j]));
    }
}