/*
430.
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

 

Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation:

The multilevel linked list in the input is as follows:



After flattening the multilevel linked list it becomes:


Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation:

The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL
Example 3:

Input: head = []
Output: []
 

How multilevel linked list is represented in test case:

We use the multilevel linked list from Example 1 above:

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
The serialization of each level is as follows:

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 

Constraints:

Number of Nodes will not exceed 1000.
1 <= Node.val <= 10^5
*/
// recursively finding the child lists. 100% 58.8%
class Solution {
    public Node flatten(Node head) {
        if(head == null) return null;
        Node res = head;
        link(head);
        return res;
    }
    
    public Node link(Node cur){
        if(cur.next == null && cur.child == null){
            return cur;
        }
        if(cur.child != null){
            Node childHead = cur.child;
            Node childTail = link(cur.child);
            childTail.next = cur.next;
            if(cur.next != null)
                cur.next.prev = childTail;
            cur.next = childHead;
            childHead.prev = cur;
            cur.child = null;
            cur = childTail;
            return link(cur);
        }
        return link(cur.next);
    }
}
//iteratively method 100% 73%
class Solution {
    public Node flatten(Node head) {
        if( head == null) return head;
	// Pointer
        Node p = head; 
        while( p!= null) {
            /* CASE 1: if no child, proceed */
            if( p.child == null ) {
                p = p.next;
                continue;
            }
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            Node temp = p.child;
            // Find the tail of the child
            while( temp.next != null ) 
                temp = temp.next;
            // Connect tail with p.next, if it is not null
            temp.next = p.next;  
            if( p.next != null )  p.next.prev = temp;
            // Connect p with p.child, and remove p.child
            p.next = p.child; 
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }
}
//python recursively method 5.16 8.54
class Solution:
    def flatten(self, head: 'Node') -> 'Node':
        if not head: return None
        def travel(node):
            while node:
                q = node.next
                if not q: tail = node
                if node.child:
                    node.next = node.child
                    node.child.prev = node
                    t = travel(node.child)
                    if q:
                        q.prev = t
                    t.next= q
                    node.child = None
                node = node.next
            return tail
        travel(head)
        return head
//python iteratively method using stack 13% 74.3%
class Solution:
    def flatten(self, head: 'Node') -> 'Node':
        if not head: return None
        stack = [head]; p = None
        while stack:
            r = stack.pop()
            if p:
                p.next,r.prev = r,p
            p = r
            if r.next:
                stack.append(r.next)
            if r.child:
                stack.append(r.child)
                r.child = None
        return head
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/