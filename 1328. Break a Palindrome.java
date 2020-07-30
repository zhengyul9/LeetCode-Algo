/*
1328.
Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.

After doing so, return the final string.  If there is no way to do so, return the empty string.

Example 1:

Input: palindrome = "abccba"
Output: "aaccba"
Example 2:

Input: palindrome = "a"
Output: ""

Constraints:

1 <= palindrome.length <= 1000
palindrome consists of only lowercase English letters.
*/
// greedy algo, if not a, change to a. 
class Solution {
    public String breakPalindrome(String palindrome) {
        if(palindrome.length() == 1) return ""; // corner case 1: length of 1, replace to ""
        StringBuilder res = new StringBuilder();
        for(char c : palindrome.toCharArray()){
            res.append(c);
        }
        for(int i = 0; i < res.length(); i++){
            if(i == res.length()-1 && res.charAt(i) == 'a'){//corner case 2: all a, change the last a to b.
                res.replace(i,i+1,"b");
                break;
            }
            else if(res.charAt(i) != 'a'){
				//Corner case 3: aba aca aabaa aaaaabaaaaa, change the last a to b. 
                if(i>0 && i < res.length() && res.charAt(i-1) == res.charAt(i+1)){
                    res.replace(res.length()-1,res.length(),"b");
                    break;
                }
                res.replace(i,i+1,"a");
                break;
            }
        }
        return res.toString();
    }
}
//using value of  a better solution
class Solution {
    public String breakPalindrome(String palindrome) {
        char[] s = palindrome.toCharArray();
        int n = s.length;

        for (int i = 0; i < n / 2; i++) {
            if (s[i] != 'a') {
                s[i] = 'a';
                return String.valueOf(s);
            }
        }
        s[n - 1] = 'b'; //if all 'a'
        return n < 2 ? "" : String.valueOf(s);
    }
}