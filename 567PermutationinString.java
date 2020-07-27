/*
567.
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

 

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False
 

Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[26];
        for(char a : s1.toCharArray()){
            map[a -'a']++;
        }
        int start = 0;
        int end = 0;
        while(end - start < s1.length() && end < s2.length()){           
            if(map[s2.charAt(end) - 'a'] > 0){
                map[s2.charAt(end) - 'a']--;
                end++;
            }
            else{
                if(end == start){
                    start++;
                    end = start;
                }
                else{
                    map[s2.charAt(start) - 'a']++;
                    start++;
                }
            }
        }
        
        for(int a : map){
            if(a != 0){
                return false;
            }
        }
        return true;
    }
}