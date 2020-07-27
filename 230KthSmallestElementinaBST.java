/*
230.
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 

Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
*/
//DFS recursively method
class Solution {
    int count = 0;
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {//inorder traversal left-mid-right        
        inorder(root, k);
        return res;
    }  
    private void inorder(TreeNode root, int k){
        if(root.left != null){
            inorder(root.left, k);
        }
        count++;
        if(count == k){
            res = root.val;
            return;
        }
        if(root.right != null){
            inorder(root.right, k);
        }
    }
}
//DFS iteration method
class Solution {
    public int kthSmallest(TreeNode root, int k) {//inorder traversal left-mid-right      
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int res = 0;
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        while(!stack.isEmpty() && k > 0){
            TreeNode temp = stack.pop();
            k--;
            if(k == 0)
                return temp.val;
            TreeNode right = temp.right;
            while(right != null){
                stack.push(right);
                right = right.left;
            }
        }
        return res;
    }
    
}
//binary search
class Solution {
    public int kthSmallest(TreeNode root, int k) {
      int count = countNodes(root.left);
      if (k <= count) {
          return kthSmallest(root.left, k);
      } else if (k > count + 1) {
          return kthSmallest(root.right, k-1-count); // 1 is counted as current node
      }
      
      return root.val;
    }
    public int countNodes(TreeNode n) {
      if (n == null) return 0;
      
      return 1 + countNodes(n.left) + countNodes(n.right);
    }  
}