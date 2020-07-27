/*
113.
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/
//DFS 45, 14% notice that res.add(new LinkedList(each)) must have a new LinkedList
class Solution {
    List<List<Integer>> res = new LinkedList<List<Integer>>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> each = new LinkedList<Integer>();
        traverse(root, sum, each);
        return res;
    }
    
    public void traverse(TreeNode root, int sum, List<Integer> each){
        if(root==null)  {return;}
        each.add(root.val);    
		//each.add(new Integer(root.val));  //both works		
        if(root.left==null && root.right==null && sum-root.val==0) {
            res.add(new LinkedList(each));
            each.remove(each.size()-1);
            return;
        }
        traverse(root.left, sum-root.val, each);
        traverse(root.right, sum-root.val, each);
        each.remove(each.size()-1);
        return;
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