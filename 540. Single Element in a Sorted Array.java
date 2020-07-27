/*
540.
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.

Follow up: Your solution should run in O(log n) time and O(1) space.

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5
*/
//bit manipulation 12.55 17.36
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int res = 0;
        for(int i : nums){
            res = res ^ i;
        }
        return res;
    }
}
//binary search 100% 70% 
class Solution{   
    public static int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (mid % 2 == 1) mid--; // find the first one of duplicate element
            if (nums[mid] != nums[mid + 1]) end = mid;//at first part
            else start = mid + 2;//at second part
        }
        return nums[start];
    }
}
//another binary search from lo, high to mid, check 2 at one time 100% 5%
class Solution {
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1) return nums[0];
        int res = 0;
        int lo = 0;
        int hi = nums.length-1;
        while(hi - lo >= 3){// out when length < 3 or only res in the mid 3 elements
            int lolo = lo+1;
            int hihi = hi-1;
            if(nums[lo]+nums[hi] == nums[lolo]+nums[hihi]){
                lo += 2;
                hi -= 2;
                continue;
            }
            //find
            if(nums[hihi] == nums[hi]){// find in low
                if(lo == 0 && nums[lo] != nums[lolo])
                    return nums[lo];
                else if(nums[lo] != nums[lolo] && nums[lo] != nums[lo-1])
                    return nums[lo];
                else{
                    return nums[lolo];
                }
            }
            else{//find in hi
                if(hi == nums.length-1 && nums[hi] != nums[hihi])
                    return nums[hi];
                else if(nums[hi] != nums[hihi] && nums[hi] != nums[hi+1])
                    return nums[hi];
                else{
                    return nums[hihi];
                }
            }
            
        }
        if(lo == hi)
            return nums[lo];
        int m = lo + (hi-lo)/2;
        return nums[m] == nums[m+1] ? nums[m-1] : nums[m+1];
    }
}