/*
1081
Return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.

Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 
Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
*/
// one array records last index of each appeared element, one seen array records selected elements and ignore element after first selection. Stack is the sequence O(N)
class Solution {
    public String removeDuplicateLetters(String S) {
        Stack<Integer> stack = new Stack<>();
        int[] last = new int[26], seen = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;
        for (int i = 0; i < S.length(); ++i) {
            int c = S.charAt(i) - 'a';
            if (seen[c]++ > 0) continue;
            while (!stack.isEmpty() && stack.peek() > c && i < last[stack.peek()])
                seen[stack.pop()] = 0;
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i : stack) sb.append((char)('a' + i));
        return sb.toString();
    }
}