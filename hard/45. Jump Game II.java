/*
45.
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
*/
//greedy algo keep jump fastest 100 72
class Solution {
    public int jump(int[] A) {
	    int jumps = 0, curEnd = 0, curFarthest = 0;
	    for (int i = 0; i < A.length - 1; i++) {
		    curFarthest = Math.max(curFarthest, i + A[i]);
		    if (i == curEnd) {
			    jumps++;
			    curEnd = curFarthest;
		    }
	    }
	    return jumps;
    }
}