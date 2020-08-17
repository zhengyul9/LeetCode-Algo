/*
209.
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
 */
// two pointers slide windows 100% 100%
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0;
        int len = 0;
        for(int i = 0; i < nums.length; i++){
            s -= nums[i];
            while(s <= 0){
                len = len == 0 ? i-start+1 : Math.min(len, i-start+1);
                s += nums[start];
                start++; 
            }
        }
        return len;
    }
}
//python
class Solution:
    def minSubArrayLen(self, s: int, nums: List[int]) -> int:
        start = 0
        length = 0
        for i in range (0,len(nums)):
            s -= nums[i]
            while (s <= 0) :
                if(length == 0):
                    length = i-start+1
                else:
                    length = min(length, i-start+1)
                s += nums[start]
                start += 1
        return length