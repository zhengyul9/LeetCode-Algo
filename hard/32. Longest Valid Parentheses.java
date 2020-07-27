/*
32.
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
*/
//using stack record index, check each interval at the end, if needed. 50 50 O(n)
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(')
               stack.push(i);
            else{
                if(!stack.isEmpty()){
                    if(s.charAt(stack.peek()) == '(')
                        stack.pop();
                    else{
                        stack.push(i);
                    }
                }
                else{
                    stack.push(i);
                }
            }
        }
        int len = s.length(), cur = 0, res = 0;
        if(stack.isEmpty()) return len;
        while(!stack.isEmpty()){
            cur = stack.pop();
            res = Math.max(res, len-cur-1);
            len = cur;
        }
        res = Math.max(res,len);
        return res;
    }
}