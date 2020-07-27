/*
560.
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2
 

Constraints:

The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/
//naive algo, s[i,j] = s[0,j]-s[0,i], then compute all O(n^2), can be O(1) 9.45, 22.32
class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = 0;
        if(dp[0] == k)
            res++;
        for(int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + dp[i-1];
            if(dp[i] == k)
                res++;
        }
        for(int i = 0; i < nums.length-1; i++){
            for(int j = i+1; j < nums.length; j++){
                if(dp[j] - dp[i] == k)
                    res++;
            }
        }
        return res;
    }
}
//O(n) hashmap store presum history. 80, 61
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        
        return result;
    }
}