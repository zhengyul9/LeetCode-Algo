/*
109.
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
*/
//100 32%, find mid, then keep add left to left and right to right
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;
        
        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }
}
//another one 100%, 52%
class Solution {
    private ListNode node;

    public TreeNode sortedListToBST(ListNode head) {
	    if(head == null){
		    return null;
	    }
	
	    int size = 0;
	    ListNode runner = head;
	    node = head;
	
	    while(runner != null){
		    runner = runner.next;
		    size ++;
	    }
	
	    return inorderHelper(0, size - 1);
    }

    public TreeNode inorderHelper(int start, int end){
	    if(start > end){
		    return null;
	    }
	
	    int mid = start + (end - start) / 2;
	    TreeNode left = inorderHelper(start, mid - 1);
	
	    TreeNode treenode = new TreeNode(node.val);
	    treenode.left = left;
	    node = node.next;

	    TreeNode right = inorderHelper(mid + 1, end);
	    treenode.right = right;
	
	    return treenode;
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
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