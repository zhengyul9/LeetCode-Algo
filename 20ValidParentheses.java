/*
20.
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

Input: "]"
Output: false

Input: "["
Output: false
*/
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char temp;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }
            
            else{
                if(stack.isEmpty())
                    return false;
                temp = stack.pop();
                if(s.charAt(i) == ')' && temp != '(')
                    return false;
                else if(s.charAt(i) == '}' && temp != '{')
                    return false;
                else if(s.charAt(i) == ']' && temp != '[')
                    return false;
            }

        }
        if(!stack.isEmpty())
            return false;
        return true;
    }
}