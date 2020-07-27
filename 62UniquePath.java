/*
62.
Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
 

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 */
//Math binomial coeffiency: combination problem
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
//Dynamic programming: formulation: map[i][j] = map[i-1][j]+map[i][j-1];
public class Solution {
    public int uniquePaths(int m, int n) {
        Integer[][] map = new Integer[m][n];
        for(int i = 0; i<m;i++){
            map[i][0] = 1;
        }
        for(int j= 0;j<n;j++){
            map[0][j]=1;
        }
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                map[i][j] = map[i-1][j]+map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }
}