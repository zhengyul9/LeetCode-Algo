/*
1658.
You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.

 

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
*/
//Prim algo without using priority queue. Using set to replace pq 80 100
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0;
        for(int i : nums) total += i;
        int target = total-x;
        if(target == 0) return nums.length;
        int sum = 0;
        int longesti = -1, longestj = -1, longest = 0;
        for(int i = 0, j = i; j < nums.length; j++){
            sum += nums[j];
            while(i <= j && sum > target){
                sum -= nums[i];
                i++;
            }
            if(sum == target && j-i+1 > longest){
                longest = j-i+1;
                longesti = i;
                longestj = j;
            }
        }
        if(longest == 0) return -1;
        return nums.length-longest;
    }
}