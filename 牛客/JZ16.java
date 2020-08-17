/*
输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
*/
//recursive method
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
       if(list1 == null){
           return list2;
       }
       if(list2 == null){
           return list1;
       }
       if(list1.val <= list2.val){
           list1.next = Merge(list1.next, list2);
           return list1;
       }else{
           list2.next = Merge(list1, list2.next);
           return list2;
       }       
   }
}
//non-recursive method
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
       if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode mergeHead = null;
        ListNode current = null;     
        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                if(mergeHead == null){
                   mergeHead = current = list1;
                }else{
                   current.next = list1;
                   current = current.next;
                }
                list1 = list1.next;
            }else{
                if(mergeHead == null){
                   mergeHead = current = list2;
                }else{
                   current.next = list2;
                   current = current.next;
                }
                list2 = list2.next;
            }
        }
        if(list1 == null){
            current.next = list2;
        }else{
            current.next = list1;
        }
        return mergeHead;
   }
}
//no result solution, do not know why yet
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        if(list1.val > list2.val){
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }
        ListNode head = list1;
        ListNode cur2 = list2;
        while(list1.next != null){
            System.out.print("here");
            if(cur2 == null) break;
            if(cur2.val < list1.next.val){
                list2 = list2.next;
                cur2.next = list1.next;
                list1.next = cur2;
                cur2 = list2;
                list1 = list1.next;
            }
        }
        while(cur2 != null){
            list1.next = cur2;
            cur2 = cur2.next;
            list1 = list1.next;
        }
        return head;
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