/*
题目描述
编写一个函数，传入一个int型数组，返回该数组能否分成两组，使得两组中各元素加起来的和相等，并且，所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数），能满足以上条件，返回true；不满足时返回false。 
输入描述:
第一行是数据个数，第二行是输入的数据

输出描述:
返回true或者false

示例1
输入
复制
4
1 5 -5 1
输出
复制
true
*/
//pure math
import java.util.*;
 
public class Main {
     
    public static void main(String[] args) {
         Scanner sc=new Scanner(System.in);
         while(sc.hasNext()){
             int n = sc.nextInt();
             int sum1 = 0, sum2 = 0;
              int[] a = new int[n];
              int count = 0;
             for(int i =0;i<n;i++){
                 int tmp = sc.nextInt();
                 if(tmp%5==0){
                     sum1+=tmp;
                 }
                 else if(tmp%3==0)
                     sum2 += tmp;
                 else{
                     a[count++] = tmp;
                 }
             }
             int sum = Math.abs(sum1-sum2);//这里只考虑绝对值就可以了
             System.out.println(f(0,count,a,0,sum));
         }
    }
 
    public static boolean f(int i ,int n,int[] a,int result,int sum){
        if(i==n){
                return Math.abs(result) == sum;//绝对值相等就可以
        }
        else{
            return (f(i+1,n,a,result+a[i],sum)||f(i+1,n,a,result-a[i],sum));
        }
    }
}