/*
152.
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Input: [0,2]
Output: 2

Input: [-2,3,-4]
Output: 24

Input: [-1,-2,-9,-6]
Output: 108
*/
// DP find local-op depending on the sign of nums[i], 95.29, 8
class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0)
            return 0;
        int max = nums[0]; 
        int imax = nums[0];
        int imin = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < 0){
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(nums[i], imax * nums[i]);
            imin = Math.min(nums[i], imin * nums[i]);
            max = Math.max(max, imax);
        }
        return max;
        
    }
}
