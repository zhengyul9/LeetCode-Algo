/*
404.
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/
//traverse the tree with left node flag 100%
class Solution {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        helper(root, 0);
        return sum;
    }
    
    public void helper(TreeNode node, int flag){
        if(node == null) return;
        if(flag == 1 && node.left == null && node.right == null) sum += node.val;
        flag = 0;
        helper(node.left, flag+1);
        helper(node.right, flag);
    }
}
//one function only 100%
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) ans += root.left.val;
            else ans += sumOfLeftLeaves(root.left);
        }
        ans += sumOfLeftLeaves(root.right);   
        return ans;
    }
}