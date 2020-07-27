/*
139.
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */
//dp method 69, 5
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        //Set<String> dict = new HashSet<String>(wordDict); // beat 8%
        Set<String> dict = new HashSet<>(); //beat 69%
        dict.addAll(wordDict);//beat 69%
        dp[0] = true;    
        
        for(int i = 1; i < s.length()+1; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] == true && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }       
        return dp[s.length()];    
    }
}
//