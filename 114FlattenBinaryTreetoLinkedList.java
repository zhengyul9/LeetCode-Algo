/*
114.
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
*/
//BFS 6, 99%
class Solution {
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        TreeNode cur = root;
        help(root);
        q.poll();
        while(!q.isEmpty()){
            System.out.println(q.peek().val);
            cur.right = q.poll();
            cur.left = null;
            cur = cur.right;
        }
    }
    private void help(TreeNode node){
        if(node != null){
            q.offer(node);
            if(node.left != null){
                help(node.left);
            }
            if(node.right != null){
                help(node.right);
            }
        }
    }
}
//in-place recursion
class Solution {
    public void flatten(TreeNode root) {
        flatten(root,null);
    }
    private TreeNode flatten(TreeNode root, TreeNode pre) {
        if(root==null) return pre;
        pre=flatten(root.right,pre);    
        pre=flatten(root.left,pre);
        root.right=pre;
        root.left=null;
        pre=root;
        return pre;
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