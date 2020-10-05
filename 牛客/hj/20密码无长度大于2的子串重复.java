/*
题目描述
密码要求:

1.长度超过8位

2.包括大小写字母.数字.其它符号,以上四种至少三种

3.不能有相同长度大于2的子串重复

输入描述:
一组或多组长度超过2的子符串。每组占一行

输出描述:
如果符合要求输出：OK，否则输出NG

示例1
输入
复制
021Abc9000
021Abc9Abc1
021ABC9000
021$bc9000
输出
复制
OK
NG
NG
OK
*/
//devide 2 to n, if it can divided 2&3 & mod is 0, is must be a prime number
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            if(s.length() <= 8) {
                System.out.println("NG");
                continue;
            }
            int low = 0, upper = 0, sign = 0, digital = 0;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(c-'0'>=0 && c-'0'<=9) digital = 1;
                else if(c-'a'>=0 && c-'z'<=26) low = 1;
                else if(c-'A'>=0 && c-'Z'<=26) upper = 1;
                else sign = 1;
            }
            int count = low+upper+sign+digital;
            if(count<3) {
                System.out.println("NG");
                continue;
            }
			#check repeated substring with length greater than 2
            String subStr = new String();
            int flag = 0;
            for(int i = 0; i < s.length() - 4; i++){
                subStr = s.substring(i,i + 3);
                if(s.substring(i + 1).contains(subStr)){
                    flag = 1;
                }
            }
			#end check
            if(flag == 1) {
                System.out.println("NG");
                continue;
            }
			
            System.out.println("OK");
        }
    }
}