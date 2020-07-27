/*
22. jump game
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.
Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 3 * 10^4
0 <= nums[i][j] <= 10^5
*/
//naive 22.14%, 33% 
class Solution {
    public boolean canJump(int[] nums) {
        int des = nums.length - 1;
        int index = 0;
        int far = nums[index];
        while(index < des){
            if(nums[index] == 0){
                return false;
            }
            int max = 0;
            int temp = 0;
            for(int i = 1; i <= nums[index]; i++){
                if((index+i)>=des)
                    return true;
                if(max <= nums[index+i]){
                    max = nums[index+i];
                    temp = index+i;
                }    
            }
            far = Math.max(far,nums[index]+index);
            if(max == 0){
                index += nums[index];
                if(index >= des)
                    return true;
                else if(index < far){
                    index = far;
                }
                else 
                    return false;
            }
            else{
                index = temp;
            }
        }
        return true;
    }
}
//dp 98.69%, 49.57%
class Solution {
    public boolean canJump(int[] nums) {
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(i>max){
                return false;
            }
            else{
                max = Math.max(max,i+nums[i]);
                if(max >= nums.length-1)
                    return true;
            }
        }        
        return true;
    }
}