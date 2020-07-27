/*
234.
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
*/
//O(n) stack method, 95.67, 31.71
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return true;
        ListNode temp = head;
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        if(count == 1)
            return true;
        Stack<ListNode> stack = new Stack<ListNode>();
        for(int i = 0; i < count/2; i++){
            stack.push(head);
            head = head.next;
        }
        if(count%2 != 0)
            head = head.next;
        while(!stack.isEmpty()){
            if(stack.pop().val != head.val)
                return false;
            head = head.next;
        }
        return true;
    }
}
//O(n) + O(1) space by reversing half of the list and using pointers to check if they are equal
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;
    
        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}