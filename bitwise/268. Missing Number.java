/*
268.
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/
//using math O(n) 100%, 73.35%
class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        
        for(int i = 1; i <= nums.length; i++){
            sum -= i;
        }
        
        return 0 - sum;
    }
}
// bitwise very easy 100%, 68.45
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res^nums.length;
    }
}