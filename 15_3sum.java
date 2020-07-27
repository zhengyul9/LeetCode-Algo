/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

O(n2) solution below:
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length == 0 || nums.length == 1 || nums.length == 2){
            return res;
        }

        Arrays.sort(nums);

        for(int i = 0;i<nums.length;i++)
        {

            if(nums[i]>0) break;
            if(i>0&&nums[i] == nums[i-1])
                continue;
            int target = 0-nums[i];
            int j = i+1,k = nums.length-1;
            while(j<k)
            {
                List<Integer> each = new ArrayList<Integer>();
                if(nums[j]+nums[k] == target)
                {
                    each.add(nums[i]);
                    each.add(nums[j]);
                    each.add(nums[k]);
                    res.add(each);
                    while(j<k&&nums[j+1] == nums[j]) j++;
                    while(j<k&&nums[k-1] == nums[k]) k--;
                    j++;k--;
                }
                else if(nums[j]+nums[k]<target)
                    j++;
                else
                    k--;
            }
        }
        return res;
    }
}