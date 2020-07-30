/*
297.
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/
// BFS convert tree to stirng, split and using queue to convert back to tree 61 11
public class Codec {
    private static final String spliter = ",";
    private static final String N = "N";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node == null){
                sb.append(N).append(spliter);
                continue;
            }
            sb.append(node.val).append(spliter);
            q.add(node.left);
            q.add(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>();
        Queue<TreeNode> r = new LinkedList<>();
        q.addAll(Arrays.asList(data.split(spliter)));
        if(q.peek().equals("N")) return null;
        TreeNode res = new TreeNode(Integer.parseInt(q.poll()));
        r.add(res);
        while(!q.isEmpty() && !r.isEmpty()){
            TreeNode left;
            TreeNode right;
            if(q.peek().equals("N")) {
                left = null;
                q.poll();
            }
            else
                left = new TreeNode(Integer.parseInt(q.poll()));
            if(q.peek().equals("N")) {
                right = null;
                q.poll();
            }
            else
                right = new TreeNode(Integer.parseInt(q.poll()));

            TreeNode root = r.poll();
            root.left = left;
            root.right = right;
            if(left != null) r.add(left);
            if(right != null) r.add(right);
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
 *     TreeNode(int x) { val = x; }
 * }
 */
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));