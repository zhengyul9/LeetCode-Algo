/*Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

Input: "ccc"
Output: "ccc"

Input: "ccccc"
Output: "ccccc"

Input: "abcda"
Output: "a"

*/
class Solution {
    public String longestPalindrome(String s) {
        if(s.length() == 0 || s.length() == 1)
            return s;
        char[] ch = s.toCharArray();    
        int[] dp = new int[s.length()];
        int counter, j, k, flag = 0;
        int max = dp[0];
        int index = 0, index1 = -1;
        for(int i = 0; i < ch.length-1; i++){
            if(ch[i] != ch[i+1] || flag == -2){
                counter = 1;
                j = i - 1;
                k = i + 1;
                if(flag == -2)
                    flag = 0;
            }
            else{ //if(ch[i] == ch[i+1])
                counter = 2;
                j = i - 1;
                k = i + 2;
                flag = 1;
            }
            while(j >= 0 && k < ch.length){
                if(ch[j] == ch[k]){
                    counter += 2;
                }
                else
                    break;
                j--;
                k++;
            }
            if(counter > dp[i])
                dp[i] = counter;
            if(max < dp[i] && flag == 0){
                max = dp[i];
                index = i;
                index1 = -1;
            }
            else if(max < dp[i] && flag == 1){
                max = dp[i];
                index = i;
                index1 = i+1;
            }
            if(flag == 1){
                flag = -2;
                i = i - 1;
            }
        }
        if(index1 == -1)
            return s.substring((index-(max-1)/2),index+(max-1)/2+1);
        else
            return s.substring((index-(max-2)/2),index1+(max-2)/2+1);
    }
}