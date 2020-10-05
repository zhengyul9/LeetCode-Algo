/*
题目描述
定义一个二维数组N*M（其中2<=N<=10;2<=M<=10），如5 × 5数组下所示： 



它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的最短路线。入口点为[0,0],既第一空格是可以走的路。

输入描述:
输入两个整数，分别表示二位数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。

输出描述:
左上角到右下角的最短路径，格式如样例所示。

示例1
输入
复制
5 5
0 1 0 0 0
0 1 0 1 0
0 0 0 0 0
0 1 1 1 0
0 0 0 1 0
输出
复制
(0,0)
(1,0)
(2,0)
(2,1)
(2,2)
(2,3)
(2,4)
(3,4)
(4,4)
*/
//one pass algo
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] num = new int[n+2][m+2];
            for(int i=0;i<m+2;i++){
                num[0][i] = 1;  // 第一行 最后一行为 1
                num[n+1][i] = 1;
            }
            for(int j=0;j<n+2;j++){
                num[j][0] = 1;
                num[j][m+1] = 1;
            }
            for(int i=1;i<n+1;i++){
                for(int j=1;j<m+1;j++){
                    num[i][j] = sc.nextInt();
                }
            }
            setWay(num,1,1,n,m);
            for(int i=1;i<n+1;i++){
                for(int j=1;j<m+1;j++){
                    if(num[i][j] ==2)
                        System.out.println("("+(i-1)+","+(j-1)+")");
                }
            }
        }
    }
    public static boolean setWay(int[][] num,int i ,int j,int a,int b){
        // 1 表示墙，2表示走且可以走，3表示走过走不通
        if(num[a][b] ==2){ // 右下角走通了
            return true;
        }else{
            if(num[i][j]==0){ // 0 可以走
                // 按照 下右上左
                num[i][j]=2;
                if(setWay(num,i+1,j,a,b)){
                    return true;
                }else if(setWay(num,i,j+1,a,b)){
                    return true;
                }else if(setWay(num,i-1,j,a,b)){
                    return true;
                }else if(setWay(num,i,j-1,a,b)){
                    return true;
                }else{  // 死路
                    num[i][j] = 3;
                    return false;
                }
            }else{ // map[i][j] !=0  1  2  3
                return false;
            }
        }
    }
}