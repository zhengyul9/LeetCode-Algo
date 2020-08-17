/*
题目描述
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。
*/
//left < right for each node. Check each node from root to the first element
public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        int size = sequence.length;
        if(0==size)return false;
 
        int i = 0;
        while(size>0)
        {
            size--;
            while(i<sequence.length && sequence[i++]<sequence[size]);
            while(i<sequence.length && sequence[i++]>sequence[size]);
 
            if(i<size)return false;
            i=0;
        }
        return true;
    }
}