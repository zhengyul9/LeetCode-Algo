/*
380.
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1

Set union A | B
Set intersection A & B
Set subtraction A & ~B
Set negation ALL_BITS ^ A or ~A
Set bit A |= 1 << bit
Clear bit A &= ~(1 << bit)
Test bit (A & 1 << bit) != 0
Extract last bit A&-A or A&~(A-1) or x^(x&(x-1))
Remove last bit A&(A-1)
Get all 1-bits ~0
*/
// bit wise opeator, 100, 68.66
class Solution {
    public int getSum(int a, int b) {
	    if (a == 0) return b;
	    if (b == 0) return a;

	    while (b != 0) {
		    int carry = a & b;
		    a = a ^ b;
		    b = carry << 1;
	    }
	
	    return a;
    }
}