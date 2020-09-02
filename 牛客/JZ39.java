/*
题目描述
输入一棵二叉树，判断该二叉树是否是平衡二叉树。

在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
*/
//traverse with mark
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null) return true;
        int res = depth(root, 1);
        return res == -1 ? false : true;
    }
    
    public int depth(TreeNode node, int cur){
        if(node == null){
            return 0;
        }
        int left = depth(node.left, cur+1);
        int right = depth(node.right, cur+1);
        if(Math.abs(right-left) > 1) cur = -1;
        if(cur == -1 || left == -1 || right == -1) return -1;
        return Math.max(left,right)+1;
    }
}