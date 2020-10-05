/*
题目描述
问题描述：给出4个1-10的数字，通过加减乘除，得到数字为24就算胜利
输入：
4个1-10的数字。[数字允许重复，但每个数字仅允许使用一次，测试用例保证无异常数字]
输出：
true or false

输入描述:
输入4个int整数

输出描述:
返回能否得到24点，能输出true，不能输出false

示例1
输入
复制
7 2 1 10
输出
复制
true
*/
//one pass algo
import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        double result=0.0;
        int[] num=new int[4];
        while(input.hasNext()){
            int[] temp=new int[4];
            for(int i=0;i<4;i++){
                num[i]=input.nextInt();
            }
            System.out.println(check(num,temp,result));
        }
        input.close();
    }
 
    private static boolean check(int[] num,int[] temp,double result) {
        for(int i=0;i<num.length;i++){
            if(temp[i]==0){
                temp[i]=1;
                if(check(num,temp,result+num[i])
                        || check(num,temp,result-num[i])
                        || check(num,temp,result*num[i])
                        || check(num,temp,result/num[i])){
                    return true;
                }
                temp[i]=0;
            }
        }
        if(result==24){
            return true;
        }else{
            return false;
        }
    }
}