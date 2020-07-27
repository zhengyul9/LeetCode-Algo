/*
395.
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/
// divide and conquer 100%
class Solution {
    public int longestSubstring(String s, int k) {
        return helper(s.toCharArray(), 0, s.length(), k);
    }
    
    public int helper(char[] ch, int left, int right, int k){
        if(right - left < k) return 0;
        int[] count = new int[26];
        for(int i = left; i < right; i++){
            count[ch[i] - 'a']++;
        }
        for(int i = left; i < right; i++){
            if(count[ch[i]-'a'] < k){
                int j = i+1;
                while(j < right && count[ch[j]-'a'] < k) j++;
                return Math.max(helper(ch, left, i, k), helper(ch, j, right, k));
            }   
        }
        return right - left;
    }
}
