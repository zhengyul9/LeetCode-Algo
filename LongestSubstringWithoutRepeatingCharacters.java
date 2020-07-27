/*

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
			 
Input: "abba"	
Input: "(a)dvdf"		 
*/

class Solution {
public int lengthOfLongestSubstring(String s) {
    int start=0;
    int ans=0;
    HashMap<Character,Integer>hmap=new HashMap<>();
    for(int i=0;i<s.length();i++){
        if(hmap.containsKey(s.charAt(i))){
            start=Math.max(start,hmap.get(s.charAt(i))+1);
            
        }
        hmap.put(s.charAt(i),i);
        ans=Math.max(ans,i-start+1);
    }
    return ans;
    
}
}