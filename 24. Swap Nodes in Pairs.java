/*
24.
Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
*/
// 100% 5%, naive solution
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head.next;
        first.next = second.next;
        second.next = first;        
        head = second;
        ListNode prev = first;      
        while(first.next != null && first.next.next != null){          
            ListNode temp1 = first;
            first = second;
            second = temp1;
            first = first.next.next;
            second = second.next.next;         
            first.next = second.next;
            second.next = first;    
            prev.next = second;
            prev = first;
        }
        return head;
    }
}
//100% 80% solution  same idea but using a dummy at the beginning to avoid start case
class Solution {
   public ListNode swapPairs(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = dummy;
		while (current.next != null && current.next.next != null) {
			ListNode first = current.next;
			ListNode second = current.next.next;
			first.next = second.next;
			current.next = second;
			current.next.next = first;
			current = current.next.next;
		}
		return dummy.next;
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