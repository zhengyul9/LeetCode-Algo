/*
34.
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int i = 0, j = nums.length-1;
        int[] res = new int[] {-1,-1};
        if(nums.length == 0)
            return res;
        while (i < j)
        {
            int mid = (i + j) /2;
            if (nums[mid] < target) i = mid + 1;
            else j = mid;
        }
        if (nums[i]!=target) return res;
        else res[0] = i;
    
        j = nums.length-1;  // We don't have to set i to 0 the second time.
        while (i < j)
        {
            int mid = (i + j) /2 + 1;	// Make mid biased to the right
            if (nums[mid] > target) j = mid - 1;  
            else i = mid;				// So that this won't make the search range stuck.
        }
        res[1] = j;
        return res; 
    }
}