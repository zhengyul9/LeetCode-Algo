/*
190.
Reverse bits of a given 32 bits unsigned integer.

Example 1:

Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
*/
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int last = 1;
        int temp = 0;
        int res = 0;
        for(int i = 0; i < 32; i++){
            temp = n & last;
            n >>= 1;
            res <<= 1;
            if(temp == 1){
                res = res | 1;
            }
        }
        return res;
    }
}