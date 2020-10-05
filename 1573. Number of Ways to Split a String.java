/*
1573.
Given a binary string s (a string consisting only of '0's and '1's), we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).

Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.

Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:

Input: s = "10101"
Output: 4
Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
"1|010|1"
"1|01|01"
"10|10|1"
"10|1|01"
Example 2:

Input: s = "1001"
Output: 0
Example 3:

Input: s = "0000"
Output: 3
Explanation: There are three ways to split s in 3 parts.
"0|0|00"
"0|00|0"
"00|0|0"
Example 4:

Input: s = "100100010100110"
Output: 12
 
Constraints:

s[i] == '0' or s[i] == '1'
3 <= s.length <= 10^5
*/
//use long for num1*num2 96.54 82.4
class Solution {
    private static final int m = 1_000_000_007;
    public int numWays(String s) {
        int sum = 0;
        for(char c : s.toCharArray()){
            if(c == '1')
                sum++;
        }
        if(sum % 3 != 0) return 0;
        if (sum == 0) {
            return (int)((s.length() - 2L) * (s.length() - 1L) / 2 % m);
			//return (int)((long)(s.length() - 2) * (long)(s.length() - 1) / 2 % m); 
        }
        sum = sum / 3;
        int l = 1, r=s.length()-2;
        int left = s.charAt(0)-'0';
        int right = s.charAt(s.length()-1)-'0';
        while(l < r){
            if(left == sum && right == sum){
                int temp = l, num1 = 1, num2 = 1;
                while(temp < r){
                    if(s.charAt(temp)-'0' == 1)
                        break;
                    temp++;
                    num1++;
                }
                temp = r;
                while(temp > l){
                    if(s.charAt(temp)-'0' == 1)
                        break;
                    temp--;
                    num2++;
                }
                return (int)((long)num1*(long)num2%m);
            }
            else if(left < sum){
                left += s.charAt(l)-'0';
                l++;
            }
            else{ 
                right += s.charAt(r)-'0';
                r--;
            }
        }
        return 1;
    }
}
//short version
class Solution {
    private static final int m = 1_000_000_007;
    public int numWays(String s) {
        int ones = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            ones += s.charAt(i) - '0';
        }
        if (ones == 0) {
            return (int)((n - 2L) * (n - 1L) / 2 % m);
        }
        if (ones % 3 != 0) {
            return 0;
        }
        int onesInEachSplitedBlock = ones / 3;
        long waysOfFirstCut = 0, waysOfSecondCut = 0;
        for (int i = 0, count = 0; i < n; ++i) {
            count += s.charAt(i) - '0';
            if (count == onesInEachSplitedBlock) {
                ++waysOfFirstCut;
            }else if (count == 2 * onesInEachSplitedBlock) {
                ++waysOfSecondCut;
            }
        }
        return (int)(waysOfFirstCut * waysOfSecondCut % m);     
    }
}