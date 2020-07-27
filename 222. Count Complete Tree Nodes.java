/*
222.
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
*/
//recursion method  100%, 30%
class Solution {
    int count = 0;
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        count++;
        countNodes(root.left);
        countNodes(root.right);
        return count;
    }
}
//better one cuz no global variable
class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
       
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        
        return 1 + left + right;
    }
}
//python same solution 50, 30
class Solution:
    def countNodes(self, root: TreeNode) -> int:
        if not root:
            return 0
        left = self.countNodes(root.left)
        right = self.countNodes(root.right)
        return 1 + left + right
//python 96.02 39.12 check depth then 2^depth+left/right 
class Solution:
        # @param {TreeNode} root
        # @return {integer}
        def countNodes(self, root):
            if not root:
                return 0
            leftDepth = self.getDepth(root.left)
            rightDepth = self.getDepth(root.right)
            if leftDepth == rightDepth:
                return pow(2, leftDepth) + self.countNodes(root.right)
            else:
                return pow(2, rightDepth) + self.countNodes(root.left)
    
        def getDepth(self, root):
            if not root:
                return 0
            return 1 + self.getDepth(root.left)