/*
1512.
Given an array of integers nums.

A pair (i,j) is called good if nums[i] == nums[j] and i < j.

Return the number of good pairs.

Example 1:

Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
Example 2:

Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.
Example 3:

Input: nums = [1,2,3]
Output: 0

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
*/
// from  end to beginning keep adding 1 to prev vale.(1-d array) adding min from i+1 to m to res
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] arr = new int[101];
        for(int n : nums){
            arr[n]++;
        }
        int res = 0;
        for(int i : arr){
            if(i != 0)
                res += ((i-1)*i)/2;
        }
        return res;
    }
}