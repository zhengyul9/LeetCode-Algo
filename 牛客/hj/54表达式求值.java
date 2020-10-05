/*
题目描述
给定一个字符串描述的算术表达式，计算出结果值。

输入字符串长度不超过100，合法的字符包括”+, -, *, /, (, )”，”0-9”，字符串内容的合法性及表达式语法的合法性由做题者检查。本题目只涉及整型计算。

输入描述:
输入算术表达式

输出描述:
计算出结果值

示例1
输入
复制
400+5
输出
复制
405
*/
//one pass algo
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            System.out.println(compute(s));
        }
    }
    
    private static int compute(String s){
        int num = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i)-'0'>=0 &&  s.charAt(i)-'0'<=9){
                num = num * 10 + s.charAt(i)-'0';
            }
            else{
                if(s.charAt(i) == '('){
                    num = compute(s.substring(i+1));
                    int left = 1;
                    while(left != 0){
                        i++;
                        if(s.charAt(i) == '(') left++;
                        else if(s.charAt(i) == ')') left--;
                    }
                }
                else{
                    if(s.charAt(i) == ')')break;
                    if(sign == '+')
                        stack.push(num);
                    else if(sign == '-')
                        stack.push(-num);
                    else if(sign == '*')
                        stack.push(stack.pop()*num);
                    else if(sign == '/')
                        stack.push(stack.pop()/num);
                    num = 0;
                    sign = s.charAt(i);
                }
            }
        }
        if(sign == '+')
            stack.push(num);
        else if(sign == '-')
            stack.push(-num);
        else if(sign == '*')
            stack.push(stack.pop()*num);
        else if(sign == '/')
            stack.push(stack.pop()/num);
        num = 0;
        while(!stack.isEmpty()){
            num += stack.pop();
        }
        return num;
    }
    
}