/*
378.
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
*/
// priority queue insert complexity O(logN)
// priority queue method 35.19 32.39 O(klogN)
class Solution {
    public class Node{
        int val;
        int i;
        int j;
        Node(int value, int i_index, int j_index){
            val = value;
            i = i_index;
            j = j_index;
        }
    }
    
    class NodeComparator implements Comparator<Node>{               
            // Overriding compare()method of Comparator  
            public int compare(Node s1, Node s2) { 
                if (s1.val > s2.val) 
                    return 1; 
                else if (s1.val < s2.val) 
                    return -1; 
                return 0; 
            } 
    } 
    
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix.length == 0) return 0;
        //if(matrix.length == 1) return matrix[0][0];
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new NodeComparator());
        int count = 1;
        Node res = new Node(matrix[0][0], 0, 0);
        pq.add(res);
        while(count < k){
            count++;
            Node temp = pq.poll();
            //System.out.println(temp.val);
            int i = temp.i;
            int j = temp.j;
            if(i+1 < matrix.length && matrix[i+1][j] != -1){
                Node node = new Node(matrix[i+1][j], i+1, j);
                matrix[i+1][j] = -1;
                pq.add(node);
            }
            if(j+1 < matrix[0].length && matrix[i][j+1] != -1){
                pq.add(new Node(matrix[i][j+1], i, j+1));
                matrix[i][j+1] = -1;
            }
            
        }
        return pq.poll().val;
    }
}
//priority queue + binary search 100% O(N∗log(max−min))
class Solution {
  public static int findKthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int start = matrix[0][0], end = matrix[n - 1][n - 1];
    while (start < end) {
      int mid = start + (end - start) / 2;
      // first number is the smallest and the second number is the largest
      int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };

      int count = countLessEqual(matrix, mid, smallLargePair);

      if (count == k)
        return smallLargePair[0];

      if (count < k)
        start = smallLargePair[1]; // search higher
      else
        end = smallLargePair[0]; // search lower
    }

    return start;
  }

  private static int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
    int count = 0;
    int n = matrix.length, row = n - 1, col = 0;
    while (row >= 0 && col < n) {
      if (matrix[row][col] > mid) {
        // as matrix[row][col] is bigger than the mid, let's keep track of the
        // smallest number greater than the mid
        smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
        row--;
      } else {
        // as matrix[row][col] is less than or equal to the mid, let's keep track of the
        // biggest number less than or equal to the mid
        smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
        count += row + 1;
        col++;
      }
    }
    return count;
  }
}