/*
367.
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.

Example 1:

Input: num = 16
Output: true
Example 2:

Input: num = 14
Output: false

Constraints:

1 <= num <= 2^31 - 1
*/
// O(lgn) binary search using long avoid overflow 100% 5%
class Solution {
    public boolean isPerfectSquare(int num) {
        long lo = 1, hi = (num/2)+1;
        while(lo <= hi){
            long mid = lo + (hi-lo)/2;
            if(mid*mid == num)
                return true;
            else if(mid*mid < num)
                lo = mid+1;
            else
                hi = mid-1;
        }
        return false;
    }
}
//math method a square number is 1+3+5+7+...   O(sqrt(N)) 30%, 5%
class Solution {
	public boolean isPerfectSquare(int num) {
		if (num < 1) return false;
		for (int i = 1; num > 0; i += 2)
			num -= i;
		return num == 0;
    }
}
// newton method O(Constant) 100%, 5%
class Solution {
    public boolean isPerfectSquare(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }
}
	