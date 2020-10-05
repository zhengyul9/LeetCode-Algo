/*
题目描述
分子为1的分数称为埃及分数。现输入一个真分数(分子比分母小的分数，叫做真分数)，请将该分数分解为埃及分数。如：8/11 = 1/2+1/5+1/55+1/110。


接口说明

功能: 将分数分解为埃及分数序列
输入参数：
String pcRealFraction:真分数(格式“8/11”)
返回值：
String pcEgpytFraction:分解后的埃及分数序列(格式“1/2+1/5+1/55+1/100”)


public static String  ConvertRealFractToEgpytFract(String pcRealFraction)
{
return null;
}

如有多个解，输出任意一个



输入描述:
输入一个真分数，String型

输出描述:
输出分解后的string

示例1
输入
复制
8/11
输出
复制
1/2+1/5+1/55+1/110
*/
//pure math
import java.util.*;

public class Main {
    public static String f(int a,int b){
        if (a==1) return ("+"+"1/"+String.valueOf(b));
        else{
            int x=0;
            if (b%a==0) x=b/a;
            else x=(b/a)+1;
            a=a*x-b;
            b=b*x;
            if (a==0)return ("+"+"1/"+String.valueOf(x));
            else return "+"+"1/"+(x)+f(a,b);
        }
    }
 
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
 
        while(in.hasNext()) {
            String s=in.next();
            String[] ss=s.split("/");
            int a=Integer.valueOf(ss[0]);
            int b=Integer.valueOf(ss[1]);
            //ArrayList list=new ArrayList();
            System.out.println(f(a,b).substring(1));
 
        }
    }
}