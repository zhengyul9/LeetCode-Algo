/*
Samantha and Sam are playing a numbers game. Given a number as a string, no leading zeros, determine the sum of all integer values of substrings of the string. For example, if the string is , the substrings are  and . Their sum is .

Given an integer as a string, sum all of its substrings cast as integers. As the number may become large, return the value modulo .

Function Description

Complete the substrings function in the editor below. It should return the sum of the integer values of all substrings in a string representation of a number, modulo .

substrings has the following parameter(s):

n: the string representation of an integer
Input Format

A single line containing an integer as a string without leading zeros.

Constraints

Output Format

A single line which is sum of the substrings, 

Sample Input 0

16
Sample Output 0

23
Explanation 0

The substring of number 16 are 16, 1 and 6 which sums to 23.

Sample Input 1

123
Sample Output 1

164
Explanation 1

The sub-strings of 123 are 1, 2, 3, 12, 23, 123 which sums to 164.
*/

public class Solution {
    static long mod = 1000000007;
    // Complete the substrings function below.
    static int substrings(String s) {
        //return helper(n, 0, 0);
        int n = s.length();
        int i;
        long[] a = new long[s.length()];
        long[] b = new long[s.length()];
        a[0] = 1; b[0] = 1;

        for(i = 1;i<n;i++)
        {
            a[i] = (a[i-1]*10) % mod;
            b[i] = (b[i-1]+a[i]) % mod;
        }

        long ans=0;

        for(i = 0;i<n;i++)
        {
            ans+=((s.charAt(i)-'0')*b[n-i-1]*(i+1))%mod;
            ans%=mod;
        }

        return (int)ans;
    }
}