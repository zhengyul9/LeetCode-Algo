/*
124.
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */
//1. Each node can be selected as the pivot node, the max is left+right+node.val
//2. If not a pivot node(goes up and checks parent node in the recursion), it can only select one path from left or right. The max value would be max(left,right)+node.val
class Solution {
    int max;
    public int maxPathSum(TreeNode root) {
        max = root.val;//Integer.MIN_VALUE
        traversal(root);
        return max;
    }
    
    private int traversal(TreeNode root){
        if(root == null)
            return 0;
        int left = Math.max(0, traversal(root.left));
        int right = Math.max(0, traversal(root.right));
        max = Math.max(max, root.val + left + right); //global maximum
        return Math.max(root.val, Math.max(root.val+left, root.val+right));//goes up and checks parent
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */