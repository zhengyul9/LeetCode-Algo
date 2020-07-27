/*
326.
Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
Follow up:
Could you do it without using any loop / recursion?
*/
// math solution, a bit like cheating 100%
class Solution {
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int  
        return ( n>0 &&  1162261467%n==0);
    }
}
// log method, more general 82%
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0)
		    return false;
	    double r = Math.log10(n) / Math.log10(3);
	    if (r % 1 == 0)//check if it is an integer
		    return true;
	    else
		    return false;
    }
}