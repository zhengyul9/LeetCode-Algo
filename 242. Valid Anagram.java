/*
242.
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/
// mapping and counting using array 95.52 82.21
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] sarr = new int[26];
        int[] tarr = new int[26];
        
        for(char c : s.toCharArray()){
            sarr[c-'a']++;
        }
        
        for(char c : t.toCharArray()){
            tarr[c-'a']++;
        }
        
        return Arrays.equals(sarr, tarr);
    }
}