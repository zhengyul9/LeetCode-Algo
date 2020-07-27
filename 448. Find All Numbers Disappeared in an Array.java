/*
448.
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/
// Each index store number of index + 1(nums[0]=1,nums[1]=2), return index+1 when nums[index]=number 0. 83.26， 95.3
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i+1 && nums[i] != 0){
                if(nums[i] == nums[nums[i]-1]){
                    nums[i] = 0;
                    continue;
                }
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
                i--;
            }
        }
        List<Integer> res = new LinkedList<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                res.add(i+1);
            }
        }
        return res;
    }
}