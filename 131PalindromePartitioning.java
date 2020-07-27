/*
131.
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 */
//backtracking 54.97%%, 18.18%
class Solution {
    public List<List<String>> partition(String s) {
        if(s.length() == 0)
            return new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        List<String> round = new ArrayList<>();
        backtracking(s, res, round);
        return res;
    }
    
    public void backtracking(String s, List<List<String>> res, List<String> round){
        if(s.length() == 0){
            res.add(new ArrayList<>(round));
            return;
        }
        for(int i = 1; i <= s.length(); i++){
            String substr = s.substring(0, i);
            if(isPalindrome(substr)){
                round.add(substr);
                backtracking(s.substring(i,s.length()), res, round);
                round.remove(round.size()-1);
            }
        }
        return;
    }
    
    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length()-1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}