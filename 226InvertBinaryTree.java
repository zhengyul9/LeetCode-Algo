/*
226.
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
*/
//recursion method  100%, 5%
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode res = new TreeNode(root.val);
        recursive(res, root);
        return res;
    }
    
    private void recursive(TreeNode node, TreeNode root){
        if(root.right != null){
            node.left = new TreeNode(root.right.val);
            recursive(node.left,root.right);
        }
        if(root.left != null){
            node.right = new TreeNode(root.left.val);
            recursive(node.right,root.left);
        }
    }
}
// in-place version using same method 100%, 5%
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}