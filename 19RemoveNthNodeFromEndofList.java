/*
19.
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

Hint: Maintain two pointers and update one with a delay of n steps.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int num = 0;
        ListNode h = head;
        while(head.next != null){
            head = head.next;
            num++;
        }
        if(num == 0)
            return null;
        num = num - n;//prev node before nth
        ListNode head1 = h;
        if(num < 0)
            return head1.next;
        for(int i = 0; i < num; i++){
            h = h.next;
        }
        h.next = h.next.next;
        return head1;
    }
}