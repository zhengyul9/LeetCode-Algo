/*
44.
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
*/
//DFS backtracking recursive method, cannot pass one super long test case due to time limited but the method is correct.
class Solution {
    public boolean isMatch(String s, String p) {        
        return backtracking(s, p, 0, 0);
    }
    
    public boolean backtracking(String s, String p, int i, int j){
        if (i == s.length() && j == p.length()) return true;
        if (j == p.length()) return false;
        if (i == s.length())
        {
            for (int k = j; k < p.length(); ++k) if (p.charAt(k) != '*') return false;
            return true;
        }
        
        if(s.charAt(i) == p.charAt(j)){
            return backtracking(s, p, i+1, j+1);
        }
        else if(p.charAt(j) == '?'){
            return backtracking(s, p, i+1, j+1);
        }

        else if (p.charAt(j) == '*')
        {
            // the * could match 0+ chars in s
            return backtracking(s, p, i + 1, j) || backtracking(s, p, i, j + 1);
        }
        return false;
    }
}
//DP method one-pass constant space 88% 27%
class Solution {
    public boolean isMatch(String str, String pattern) {        
        int s = 0, p = 0, match = 0, starIdx = -1;            
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }
        
        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        
        return p == pattern.length();
    }
}
