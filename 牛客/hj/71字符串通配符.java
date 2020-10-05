/*
题目描述
问题描述：在计算机中，通配符一种特殊语法，广泛应用于文件搜索、数据库、正则表达式等领域。现要求各位实现字符串通配符的算法。
要求：
实现如下2个通配符：
*：匹配0个或以上的字符（字符由英文字母和数字0-9组成，不区分大小写。下同）
？：匹配1个字符


输入：
通配符表达式；
一组字符串。


输出：
返回匹配的结果，正确输出true，错误输出false

输入描述:
先输入一个带有通配符的字符串，再输入一个需要匹配的字符串

输出描述:
返回匹配的结果，正确输出true，错误输出false

示例1
输入
复制
te?t*.*
txt12.xls
输出
复制
false
*/
//one pass algo
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s1 = in.nextLine();
            String s2 = in.nextLine();
            System.out.println(helper(s1, s2, 0, 0));
        }
    }
 
    private static boolean helper(String s1, String s2, int p1, int p2){
        //base case
        if (p1 == s1.length() && p2 == s2.length()){
            return true;
        }
        else if (p1 == s1.length() || p2 == s2.length()){
            return false;
        }
        //遇到'*'两种情况，要不就各跳过一个比较后面，要不就s2继续往后跳先不比较
        if (s1.charAt(p1) == '*'){
            return helper(s1, s2, p1, p2+1) || helper(s1, s2, p1+1, p2+1);
        //遇到'?'跟两个一样操作一样，直接指针都往后移一个继续比较
        }else if (s1.charAt(p1) == '?' || s1.charAt(p1) == s2.charAt(p2)){
            return helper(s1, s2, p1+1, p2+1);
        }else {
            return false;
        }
    }
}