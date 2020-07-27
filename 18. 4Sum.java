/*
18.
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/
// 3-sum cloest added a loop and a set to memorize repeated. 18% 5%
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        HashSet<String> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length == 0 || nums.length == 1 || nums.length == 2 || nums.length == 3){
            return res;
        }
        int result = nums[0] + nums[1] + nums[2] + nums[nums.length - 1];
        Arrays.sort(nums);      

        for (int i = 0; i < nums.length - 3; i++) {
         for(int j = i+1; j < nums.length -2; j++){
            int start = j + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[j] + nums[start] + nums[end];
                if (sum == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[start]);
                    temp.add(nums[end]);
                    int[] arr = new int[]{nums[i],nums[j],nums[start],nums[end]};
                    Arrays.sort(arr);
                    StringBuilder sb = new StringBuilder();
                    for(int x : arr){
                        sb.append(Integer.toString(x));
                    }
                    
                    if(!set.contains(sb.toString()))  {  
                        set.add(sb.toString());
                        res.add(new ArrayList<>(temp));
                    }
                    start++;
                }
                else if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                
            }
         }
        }
        return res;
    }
}