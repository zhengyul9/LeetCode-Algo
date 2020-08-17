/*
409.
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
*/
// 100 check double element and plus res. If a single element exist, plus 1.
class Solution {
    public int longestPalindrome(String s) {
        int[] map = new int[58];
        int res = 0;
        for(char c : s.toCharArray()){
            int i = c - 'A';
            map[i]++;
            if(map[i] == 2){
                map[i] = 0;
                res += 2;
            }
        }
        for(int i : map){
            if(i == 1)
                return res+1;
        }
        return res;
    }
}
//one pass not constant space solution
class Solution {
	public int longestPalindrome(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
	}
}