/*
输入一个链表，输出该链表中倒数第k个结点
*/
//one pass two pointers method
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode p,q;
        p = head;
        q = head;
        int i = 0;
        while(p != null){
            if(i >= k){
                q = q.next;              
            }
            p = p.next;
            i++;
        }
        return i < k ? null : q;
    }
}
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/