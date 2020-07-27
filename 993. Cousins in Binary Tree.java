/*
993.
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

Example 1:

Input: root = [1,2,3,4], x = 4, y = 3
Output: false
Example 2:

Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
Example 3:

Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 
Constraints:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
*/
// 2 pass naive algo 38% 5% traverse twice binary tree, find x, y and their parent, compare
class Solution {
    int depthx = 0;
    int depthy = 0;
    int parentx = 0;
    int parenty = 0;
    public boolean isCousins(TreeNode root, int x, int y) {

        traverseX(root, 0, x, 0);
        traverseY(root, 0, y, 0);
        if(depthx == depthy && parentx != parenty)
            return true;
        return false;
        
    }
    
    public boolean traverseX(TreeNode root, int parent, int x, int depth){
        if(root == null) return false;     
        parentx = parent;
        if(root.val == x) {
            depthx = depth;
            return true;
        }
        if(traverseX(root.left, root.val, x, depth+1)) return true;
        if(traverseX(root.right, root.val, x, depth+1)) return true;
        return false;
    }
    
    public boolean traverseY(TreeNode root, int parent, int x, int depth){
        if(root == null) return false;     
        parenty = parent;
        if(root.val == x) {
            depthy = depth;
            return true;
        }
        if(traverseY(root.left, root.val, x, depth+1)) return true;
        if(traverseY(root.right, root.val, x, depth+1)) return true;
        return false;
    }
    
}
//one pass naive algo
class Solution {
    TreeNode xParent = null;
    TreeNode yParent = null;
    int xDepth = -1, yDepth = -1;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        getDepthAndParent(root, x, y, 0, null);
        return xDepth == yDepth && xParent != yParent? true: false;
    }
    //get both the depth and parent for x and y
    public void getDepthAndParent(TreeNode root, int x, int y, int depth, TreeNode parent){
        if(root == null){
            return;
        }
        if(root.val == x){
            xParent = parent;
            xDepth = depth;
        }else if(root.val == y){
            yParent = parent;
            yDepth = depth;
        }       
        getDepthAndParent(root.left, x, y, depth + 1, root);
        getDepthAndParent(root.right, x, y, depth + 1, root);
    }
}
//BFS beats 100%
public boolean isCousins(TreeNode root, int A, int B) {
    if (root == null) return false;
	Queue<TreeNode> queue = new LinkedList<>();
	queue.offer(root);
	while (!queue.isEmpty()) {
		int size = queue.size();
		boolean isAexist = false;		
		boolean isBexist = false;		
		for (int i = 0; i < size; i++) {
			TreeNode cur = queue.poll();
            if (cur.val == A) isAexist = true;
            if (cur.val == B) isBexist = true;
			if (cur.left != null && cur.right != null) { 
				if (cur.left.val == A && cur.right.val == B) { 
					return false;
				}
				if (cur.left.val == B && cur.right.val == A) { 
					return false;
				}
			}
			if (cur.left != null) {
				queue.offer(cur.left);
			}
			if (cur.right != null) {
				queue.offer(cur.right);
			}
		}
		if (isAexist && isBexist)  return true;
	}
	return false;
}