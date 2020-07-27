/*
342.
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:

Input: 16
Output: true
Example 2:

Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?
*/
// bit wise opeator, 100, 57.15
class Solution {
    public boolean isPowerOfFour(int num) {
        if(num == 0) return false;
        if(num == 1) return true;
        for(int i = 0; i < 2; i++){
            int lastbit = num & 1;
            if(lastbit != 0)
                return false;
            num = num>>>1;
        }
        int flag = 0;
        for(int i = 0; i < 30; i++){
            int lastbit = num & 1;
            if(lastbit == 1 && i%2 == 0 && flag == 0)
                flag = 1;
            else if(lastbit == 1)
                return false; 
            num = num >>> 1;
        }
        return true;
    }
}