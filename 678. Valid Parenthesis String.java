/*
678.
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
*/
//using stack and hashmap 21 29
class Solution {
    public boolean checkValidString(String s) {
        Stack<HashMap<Character,Integer>> stack = new Stack<>();
        Stack<HashMap<Character,Integer>> star = new Stack<>();
        int i = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                HashMap<Character,Integer> m = new HashMap<>();
                m.put(c,i);
                stack.push(m);
            }
            else if(c == '*'){
                HashMap<Character,Integer> m1 = new HashMap<>();
                m1.put(c,i);
                star.push(m1);
            }
            else{
                if(!stack.isEmpty()){
                    stack.pop();
                }
                else if(!star.isEmpty()){
                    star.pop();
                }
                else{
                    return false;
                }
            }
            i++;
        }
        while(!stack.isEmpty() && !star.isEmpty()){
            HashMap<Character, Integer> a = stack.pop();
            HashMap<Character, Integer> b = star.pop();
            if(a.get('(') > b.get('*')) return false;
        }
        return stack.isEmpty();
    }
}
// wired solution 100% 26%
class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }
}