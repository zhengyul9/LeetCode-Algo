/*
1008.
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 10^8
The values of preorder are distinct.
*/
// O(n) algo, 100% 54% set a bound, left element always less than node.val and right less than max bound
class Solution {
    int i = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MAX_VALUE);
    }
    
    public TreeNode helper(int[] preorder, int max){
        if(i == preorder.length || preorder[i] > max) return null;
        TreeNode root = new TreeNode(preorder[i]);
        i++;
        root.left = helper(preorder, root.val);
        root.right = helper(preorder, max);
        return root;
    }
}