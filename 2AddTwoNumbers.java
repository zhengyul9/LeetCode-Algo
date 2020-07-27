/*
2.
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

Input: (5) + (5)
Output: 0 -> 1
Explanation: 5 + 5 = 01.
*/
// naive method 55, 99
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode(0);
        ListNode head = res;
        while(l1 != null || l2 != null){
            int temp = 0;
            if(carry == 1){
                temp = 1;
                carry = 0;
            }
            if(l1 != null){
                temp += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                temp += l2.val;
                l2 = l2.next;
            }
            if(temp >= 10){
                temp = temp % 10;
                carry = 1;
            }
            ListNode tail = new ListNode(temp);
            res.next = tail;
            res = res.next;
        }
        if(carry == 1){
            ListNode end = new ListNode(1);
            res.next = end;
        }
        if(head.next != null)
            head = head.next;
        return head;
    }
}
//without carry modification version:  100%, 99
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode head = res;
        int temp = 0;
        while(l1 != null || l2 != null){
            temp = temp/10;
            if(l1 != null){
                temp += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                temp += l2.val;
                l2 = l2.next;
            }
            ListNode tail = new ListNode(temp%10);
            res.next = tail;
            res = res.next;
        }
        if(temp / 10 == 1){
            res.next = new ListNode(1);
        }
        if(head.next != null)
            head = head.next;
        return head;
    }
}