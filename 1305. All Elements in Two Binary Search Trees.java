/*
1305.
Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.

Example 1:

Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]
Example 3:

Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]
Example 4:

Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]
Example 5:

Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
 
Constraints:

Each tree has at most 5000 nodes.
Each node's value is between [-10^5, 10^5].
*/
// 2 pass 
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        
        List<Integer> r1 = new LinkedList<>();
        List<Integer> r2 = new LinkedList<>();
        if(root1 != null)
            r1 = inorder(root1, new LinkedList<>());
        if(root2 != null)
            r2 = inorder(root2, new LinkedList<>());
        List<Integer> res = new LinkedList<>();
        int i = 0, j = 0;
        while(i < r1.size() && j < r2.size()){
            if(r1.get(i) <= r2.get(j)){
                res.add(r1.get(i));
                i++;
            }
            else{
                res.add(r2.get(j));
                j++;
            }
        }
        while(i < r1.size()){
            res.add(r1.get(i));
            i++;
        }
        while(j < r2.size()){
            res.add(r2.get(j));
            j++;
        }
        return res;
    }
    
    private List<Integer> inorder(TreeNode root, List<Integer> res){
        if(root.left != null){
            res = inorder(root.left, res);
        }
        res.add(root.val);
        if(root.right != null){
            res = inorder(root.right, res);
        }
        return res;
    }
}
//one pass 
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res=new ArrayList<>();
        Stack<TreeNode> stack1=new Stack<>();
        Stack<TreeNode> stack2=new Stack<>();
        TreeNode cur1=root1, cur2=root2;
        while(cur1!=null || cur2!=null || !stack1.isEmpty() || !stack2.isEmpty()){
            while(cur1!=null){
                stack1.push(cur1);
                cur1=cur1.left;
            }
            while(cur2!=null){
                stack2.push(cur2);
                cur2=cur2.left;
            }
            if(stack2.isEmpty() || !stack1.isEmpty()&&stack1.peek().val<=stack2.peek().val){
                cur1=stack1.pop();
                res.add(cur1.val);
                cur1=cur1.right;
            }else{
                cur2=stack2.pop();
                res.add(cur2.val);
                cur2=cur2.right;
            }
        }
        return res;
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