/*
154.
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
*/
// binary search, the only diff with no duplicate is to add an else to minus hi pointer
class Solution {
    public int findMin(int[] num) {
        int low = 0, high = num.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (num[mid] < num[high])
                // the mininum is in the left part
                high = mid;
            else if (num[mid] > num[high])
                // the mininum is in the right part
                low = mid + 1;
            else{
                high--;
            }
        }

        return num[low];
    }
}
//40.5 5 O(n) one pass improved
class Solution {
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length-1;
        while(lo < hi){
            if(nums[lo] >= nums[hi])
                lo++;
            else{
                return nums[lo];
            }
        }
        return nums[lo];
    }
}