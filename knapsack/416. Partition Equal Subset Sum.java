/*
416.
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
*/
// 0/1 knapsack(backpack) problem, count sum, odd sum is false. Put value to two packs, each with size sum/2
// O(nm) where m is the sum and n is the array size. O(m) space. 75.14 80.76
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
    
        for (int num : nums) {
            sum += num;
        }
    
        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;
    
        int n = nums.length;
        boolean[] dp = new boolean[sum+1];
        Arrays.fill(dp, false);
        dp[0] = true;
    
        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i-num];
                }
            }
        }
    
        return dp[sum];
    }
}