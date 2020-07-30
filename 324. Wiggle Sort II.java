/*
324.
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/
// math solution, sort first then retrieve the one larger than its neighbor from bigger number to smaller 100 50
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] arr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            arr[i] = nums[i];
        }
        int j = nums.length-1;
        for(int i = 1; i < nums.length; i += 2){
            nums[i] = arr[j];
            j--;
        }
        for(int i = 0; i < nums.length; i += 2){
            nums[i] = arr[j];
            j--;
        }
    }
}
//python 3
class Solution:
    def wiggleSort(self, nums):
        arr = sorted(nums)
        print(arr)
        for i in range(1, len(nums), 2): nums[i] = arr.pop() 
        for i in range(0, len(nums), 2): nums[i] = arr.pop() 
        