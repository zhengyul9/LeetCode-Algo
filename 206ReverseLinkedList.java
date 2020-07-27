/*
206.
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
// iteratively method, 100%, 5.04%
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode cur = head.next;
        head.next = null;
        while(cur != null){
            ListNode temp = new ListNode(cur.val);
            temp.next = head;
            head = temp;
            cur = cur.next;
        }
        return head;
    }
}
// recursively method, 7%, 5.04%
class Solution {
    public ListNode reverseList(ListNode head) {
        return helper(head);
    }
    
    private ListNode helper(ListNode head){
        if(head == null)
            return null;
        ListNode cur = head.next;
        head.next = null;
        while(cur != null){
            ListNode temp = new ListNode(cur.val);
            temp.next = head;
            head = temp;
            cur = cur.next;
        }
        return head;
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