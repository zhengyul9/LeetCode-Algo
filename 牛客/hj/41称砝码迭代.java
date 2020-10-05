/*
题目描述
现有一组砝码，重量互不相等，分别为m1,m2,m3…mn；
每种砝码对应的数量为x1,x2,x3...xn。现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。

注：

称重重量包括0

方法原型：public static int fama(int n, int[] weight, int[] nums)

输入描述:
输入包含多组测试数据。

对于每组测试数据：

第一行：n --- 砝码数(范围[1,10])

第二行：m1 m2 m3 ... mn --- 每个砝码的重量(范围[1,2000])

第三行：x1 x2 x3 .... xn --- 每个砝码的数量(范围[1,6])
输出描述:
利用给定的砝码可以称出的不同的重量数

示例1
输入
2
1 2
2 1
输出
5
*/
//one pass algo
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int[] arr = new int[n];
            int[] num = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            for(int i = 0; i < n; i++){
                num[i] = sc.nextInt();
            }
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0; i <= num[0]; i++){
                set.add(arr[0] * i);
            }
            for(int k = 1; k < num.length; k++){
                List<Integer> list = new LinkedList<>(set);
                for(int i = 1; i <= num[k]; i++){
                    for(int j = 0; j < list.size(); j++){
                        set.add(list.get(j) + arr[k] * i);
                    }
                }
            }
            System.out.println(set.size());
        }
    }
}