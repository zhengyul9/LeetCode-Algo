/*
题目描述
如果统计的个数相同，则按照ASCII码由小到大排序输出 。如果有其他字符，则对这些字符不用进行统计。

实现以下接口：
输入一个字符串，对字符中的各个英文字符，数字，空格进行统计（可反复调用）
按照统计个数由多到少输出统计结果，如果统计的个数相同，则按照ASCII码由小到大排序输出
清空目前的统计结果，重新统计
调用者会保证：
输入的字符串以‘\0’结尾。

输入描述:
输入一串字符。

输出描述:
对字符中的
各个英文字符（大小写分开统计），数字，空格进行统计，并按照统计个数由多到少输出,如果统计的个数相同，则按照ASII码由小到大排序输出 。如果有其他字符，则对这些字符不用进行统计。

示例1
输入
复制
aadddccddc
输出
复制
dca
*/
//pure math
import java.util.*;

public class Main {
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  while(in.hasNext()) {
   String buf = in.next();
   Map<Character, Integer> map = new HashMap<Character, Integer>(127);
   for(int i = 0; i < buf.length(); i++) {
    char cc = buf.charAt(i);
    if(!map.containsKey(cc)) {
     map.put(cc, 1);
    } else {
     map.put(cc, map.get(cc) + 1);
    }
   }
   Map<Integer, Character> treeMap = new TreeMap<Integer, Character>();
   for(char cc : map.keySet()) {
    treeMap.put(map.get(cc)*128 + 128 - cc, cc);
   }
   StringBuilder res = new StringBuilder();
   for(int times : treeMap.keySet()) {
    res.append(treeMap.get(times));
   }
   System.out.println(res.reverse().toString());
  }
 }
}
