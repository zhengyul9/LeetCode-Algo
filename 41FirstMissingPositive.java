/*
41.
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
*/
//naive O(n) one-pass algo with O(n) space. 40, 27
class Solution {
    public int firstMissingPositive(int[] nums) {
        int small = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i : nums){
            if(i > 0){
                if(!set.contains(i))
                    set.add(i);
                if(small > i){
                    small = i;
                }
            }
        }
        if(small > 1){
            return 1;
        }
        int i = 2;
        while(set.contains(i)){
            i++;
        }
        return i;
    }
}
//100, 70 op solution. set nagative & bigger than length elements to invalid, then mapping to index with keeping the same value of each index. 
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num > n) {
                continue;
            }
            num--; 
            if (nums[num] > 0) {
                nums[num] = -1 * nums[num];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}