/*
69.
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
 */
//binary search 25, 5
class Solution {
    public int mySqrt(int x) {
	    if (x == 0) return 0;
	    int start = 1, end = x;
	    while (start < end) { 
		    int mid = start + (end - start) / 2;
		    if (mid <= x / mid && (mid + 1) > x / (mid + 1))// Found the result
			    return mid; 
		    else if (mid > x / mid)// Keep checking the left part
			    end = mid;
		    else
			    start = mid + 1;// Keep checking the right part
	    }
	    return start;
    }
}
//binary search 100%, 20%
class Solution {
    public int mySqrt(int x) {
        long r = x;
		while (r*r > x)
			r = (r + x/r) / 2;
		return (int) r;
    }
}
//bit manipulation 100, 100%
class Solution {
    public int mySqrt(int x) {
        if(x==0)
            return 0;
        int h=0;
        while((long)(1<<h)*(long)(1<<h)<=x) // firstly, find the most significant bit
            h++;
        h--;
        int b=h-1;
        int res=(1<<h);
        while(b>=0){  // find the remaining bits
            if((long)(res | (1<<b))*(long)(res |(1<<b))<=x)
                res|=(1<<b);
            b--;
        }
        return res;
    }
}