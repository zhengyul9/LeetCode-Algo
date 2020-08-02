/*
856.
Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.

Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6

Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50
*/
//since all score are positive, '(' can be set to -1.
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == '('){
                stack.push(-1);
            }
            else{
                int count = 0;
                while(stack.peek() != -1){
                    count += stack.pop();
                }
                stack.pop();
                stack.push(count == 0 ? 1 : count * 2);
            }
        }
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}