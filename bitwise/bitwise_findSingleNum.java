/*
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
*/
class Solution {
    public int singleNumber(int[] nums) {
        int[] arr = new int[32];
        for(int num : nums){
            for(int i = 0; i < arr.length; i++){
                arr[i] += num & 1;
                num >>= 1;
            }
        }
        int num = 0;
        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i] % 3;
            num += arr[i] << i;
        }
        return num;
    }
}

//without bitwise idea, using Arrays.sort():
class Solution {
    public int singleNumber(int[] nums) {
        int output = 0;
        try{
            
            Arrays.sort(nums);
            for(int i=0;i<nums.length;i=i+3){
                output = nums[i];
                if(nums[i] != nums[i+2])
                    return nums[i];
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            
        }
        finally{
            return output;
        }
    }
}