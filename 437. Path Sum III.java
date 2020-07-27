/*
437.
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/
// naive algo, traverse the tree, treat each node as root, compute summation of each tree. 33.89, 96.18
class Solution {
    int each = 0;
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        res += traversal(root, sum, 0);
        each = 0;
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return res;
    }
    
    public int traversal(TreeNode node, int sum, int count){
        if(node == null){
            return 0;
        }
        count += node.val;
        if(count == sum){
            each++;
        }
        traversal(node.left, sum, count);
        traversal(node.right, sum, count);
        return each;
    }
}
//100, 80% hashmap key stores prefix sum, value stores number of ways to this sum
class Solution {
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
}