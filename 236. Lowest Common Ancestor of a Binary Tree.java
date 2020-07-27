/*
236.
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 

Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.
*/
//search p in the tree, add all ancestor to hashset, then search q, check all the first ancestor in the hashset
//34.26, 32.91
class Solution {
    int flag = 0;
    HashSet<TreeNode> set = new HashSet<TreeNode>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(q == root)
            return root;
        if(p == root)
            return root;
        search(root,p);
        return result(root,q);
    }
    
    private void search(TreeNode node, TreeNode p){
        if(node.val == p.val){
            flag = 1;
            set.add(node);
            return;
        }
        if(node.left != null)
            search(node.left,p);
        if(flag == 1){
            set.add(node);
            return;
        }
        if(node.right != null)
            search(node.right,p);
        if(flag == 1){
            set.add(node);
            return;
        }
    }
    
    private TreeNode result(TreeNode node, TreeNode q){
        if(node.val == q.val){
            flag = 0;
            if(set.contains(node))
                return node;
            else
                return null;
        }
        if(node.left != null){
            TreeNode temp = result(node.left, q);
            if(temp != null)
                return temp;
        }
        if(flag == 0){
            if(set.contains(node))
                return node;
            else
                return null;
        }
        if(node.right != null){
            TreeNode temp1 = result(node.right, q);
            if(temp1 != null)
                return temp1;
        }
        if(flag == 0){
            if(set.contains(node))
                return node;
            else return null;
        }
        return null;
    }
}
//32.19, 5.01 short solution only using recursion
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   
            return root;
        return left != null ? left : right;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */