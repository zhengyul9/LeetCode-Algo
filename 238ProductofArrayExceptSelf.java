/*
238.
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/
//DP method 100%, 5%
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int tmp = 1;
        for(int i = 0; i < n; i++){
            res[i] = tmp;
            tmp = nums[i] * tmp;
        }
        int temp = nums[n-1];
        for(int i = n-2; i>=0; i--){
            res[i] = temp * res[i];
            temp = temp * nums[i];
        }
        return res;
    }
}

