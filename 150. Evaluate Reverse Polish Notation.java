/*
150.
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*/
// stack and flag naive algo, 98.06, 78.70
class Solution {
    public int evalRPN(String[] tokens) {
        int res = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int count = 0;
        int computeFlag = 0;
        for(String s : tokens){
            int temp;
            if(s.equals("+")){
                if(count == 0){
                    temp = stack.pop();
                    int temp1 = stack.pop();
                    res = temp1 + temp;
                    count = 1;
                }
                else{
                    temp = stack.pop();
                    res += temp;
                }
                computeFlag = 1;
            }
            else if(s.equals("-")){
                if(count == 0){
                    temp = stack.pop();
                    int temp1 = stack.pop();
                    res = temp1 - temp;
                    count = 1;
                }
                else{
                    temp = stack.pop();
                    res = temp - res;
                }
                computeFlag = 1;
            }
            else if(s.equals("*")){
                 if(count == 0){
                    temp = stack.pop();
                    int temp1 = stack.pop();
                    res = temp1 * temp;
                    count = 1;
                }
                else{
                    temp = stack.pop();
                    res = temp * res;
                }
                computeFlag = 1;
            }
            else if(s.equals("/")){
                if(count == 0){
                    temp = stack.pop();
                    int temp1 = stack.pop();
                    res = temp1 / temp;
                    count = 1;
                }
                else{
                    temp = stack.pop();
                    res = temp / res;
                }
                computeFlag = 1;
            }
            else{//number
                if(computeFlag == 1) {stack.push(res); count = 0;computeFlag = 0;}
                stack.push(Integer.parseInt(s));
            }
        }
        if(computeFlag == 0)
            return stack.pop();
        return res;
    }
}