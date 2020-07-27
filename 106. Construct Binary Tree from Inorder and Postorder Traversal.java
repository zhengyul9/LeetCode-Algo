/*
106.
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
*/
//96.54 14.22 recursively method
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, inorder.length-1, 0, postorder, postorder.length-1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder,
		int postStart) {
	    if (postStart < 0 || inStart < inEnd)
		    return null;

	    TreeNode root = new TreeNode(postorder[postStart]);

	    int rIndex = inStart;
	    for (int i = inStart; i >= inEnd; i--) {
		    if (inorder[i] == postorder[postStart]) {
			    rIndex = i;
			    break;
		    }
	    }

	    root.right = buildTree(inorder, inStart, rIndex + 1, postorder, postStart-1);
	    root.left = buildTree(inorder, rIndex - 1, inEnd, postorder, postStart - (inStart - rIndex) -1);
	    return root;
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