/*
257.
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
*/
//84 6
class Solution {
    List<String> res = new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        traverse(root, new LinkedList<>());
        return res;
    }
    
    public void traverse(TreeNode node, List<Integer> list){
        if(node == null) return;
        list.add(node.val);
        if(node.left == null && node.right == null){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < list.size(); i++){
                sb.append(Integer.toString(list.get(i)));
                sb.append("->");
            }
            sb.delete(sb.length()-2,sb.length());
            res.add(sb.toString());
            list.remove(list.size()-1);
            return;
        }
        traverse(node.left, list);
        traverse(node.right, list);
        list.remove(list.size()-1);
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