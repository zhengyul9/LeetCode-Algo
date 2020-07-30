/*
424.
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
 

Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
*/
// sliding windows 35 6
class Solution {
    public int characterReplacement(String s, int k) {
        int start = 0, end = 0, res = 0, max = 0;
        int[] arr = new int[26];
        for(end = 0; end < s.length(); end++){
            max = Math.max(++arr[s.charAt(end)-'A'],max);
            if(end-start+1-max > k){
                arr[s.charAt(start) - 'A']--;
                start++;
            }
            res = Math.max(res, end-start+1);
        }
        return res;
    }
}