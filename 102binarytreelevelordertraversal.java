/*
102.
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
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
 //BFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        List<List<Integer>> res = new ArrayList<>();
        q1.add(root);
        while(q1.size() != 0 || q2.size() != 0){
            if(q1.size() > 0){
            List<Integer> round = new ArrayList<>();
            while(q1.size() > 0){
                TreeNode temp = q1.poll();
                if(temp.left != null)
                    q2.add(temp.left);
                if(temp.right != null)
                    q2.add(temp.right);
                round.add(temp.val);
            }
            res.add(round);
            }
            if(q2.size()>0){
            List<Integer> round2 = new ArrayList<>();
            while(q2.size() > 0){
                TreeNode temp = q2.poll();
                if(temp.left != null)
                    q1.add(temp.left);
                if(temp.right != null)
                    q1.add(temp.right);
                round2.add(temp.val);
            }
            res.add(round2);
            }
        }
        return res;
    }
}