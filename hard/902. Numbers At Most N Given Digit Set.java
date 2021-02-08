/*
Given an array of digits, you can write numbers using each digits[i] as many times as we want.  For example, if digits = ['1','3','5'], we may write numbers such as '13', '551', and '1351315'.

Return the number of positive integers that can be generated that are less than or equal to a given integer n.

Example 1:

Input: digits = ["1","3","5","7"], n = 100
Output: 20
Explanation: 
The 20 numbers that can be written are:
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
Example 2:

Input: digits = ["1","4","9"], n = 1000000000
Output: 29523
Explanation: 
We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
In total, this is 29523 integers that can be written using the digits array.
Example 3:

Input: digits = ["7"], n = 8
Output: 1
*/
//recursively method, with math pow 100%
class Solution { 
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String maxnstr = Integer.toString(n);
        
        int lenDigs = maxnstr.length();
        int rslt = 0;
        for(int len = 1; len <= lenDigs; len++){
            rslt += helper(len, digits, maxnstr);
        }
        return rslt;
    }
    private int helper(int len, String[] digs, String max){
        if (max.equals("0"))
            return 0;
        
        if (len < max.length()){
            return (int)Math.pow(digs.length , len);
        }
        int cnt = 0;
        for(int i = 0; i < digs.length; i++){
            char fchar = max.charAt(0);
            if (Integer.parseInt(digs[i]) < (fchar - '0')){
                cnt += helper(len-1, digs, max);
            } else if (Integer.parseInt(digs[i]) == (fchar - '0')){
                if (max.length() > 1){
                    cnt += helper(len-1, digs, max.substring(1));
                } else {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
}