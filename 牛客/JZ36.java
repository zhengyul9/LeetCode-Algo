/*
题目描述
输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
*/
//O(n) + O(1) space
import java.util.*;
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while(p1 != p2) {
            if(p1 != null) p1 = p1.next;   
            if(p2 != null) p2 = p2.next;
            if(p1 != p2) {                  
                if(p1 == null) p1 = pHead2;
                if(p2 == null) p2 = pHead1;
            }
        }
        return p1;
    }
}
//O(n) + O(n) space solution
import java.util.*;
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        HashSet<ListNode> set = new HashSet<>();
        while(pHead1!=null){
            set.add(pHead1);
            pHead1 = pHead1.next;
        }
        while(pHead2!=null){
            if(set.contains(pHead2)) return pHead2;
            pHead2 = pHead2.next;
        }
        return null;
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