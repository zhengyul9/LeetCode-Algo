/*
172.
Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.
*/
equation: only 2 * 5 can have an 0 at the end. There are many 2s, so only checking how many 5s in the factorial. 25! = 25/5 = 5 there are five 5s in the factorial but the result is also 5. So add 1 more.
// recursively
 class Solution {
    public int trailingZeroes(int n) {
        if(n < 5)
            return 0;
        if(n < 10)
            return 1;
        return (n/5 + trailingZeroes(n/5))
    }
}
//iteratively
public static int trailingZeroes2(int n) {
        int result=0;
        while (n>0){
            result+=n/5;
            n/=5;
        }
        return result;
    }