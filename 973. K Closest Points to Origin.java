/*
973.
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
*/
//priority queue + hashmap without removing tie list element 25 50
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Double> pq = new PriorityQueue<>();
        HashMap<Double, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < points.length; i++){
            double x = (double)points[i][0];
            double y = (double)points[i][1];
            double distance = Math.sqrt(x*x+y*y);
            pq.add(distance);
            List<Integer> temp;
            if(!map.containsKey(distance)){
                temp = new ArrayList<>();
            }
            else{
                temp = map.get(distance);
            }
            temp.add(points[i][0]);
            temp.add(points[i][1]);
            map.put(distance, temp);
        }
        int[][] arr = new int[K][2];
        for(int i = 0; i < K; i++){
            double res = pq.poll();
            List<Integer> l = map.get(res);
            for(int j = 0; j < l.size(); j += 2){
                arr[i][0] = l.get(j);
                arr[i][1] = l.get(j+1);
                if(j + 2 < l.size()) i++;
                if(i >= K) break;
            }
        }
        return arr;
    }
}
// short writting version 25% 5%
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }
        return res;
    }   
}
// using array sorting and copy function
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }
}
//fastest  93 13
/*
The last solution is based on quick sort, we can also call it quick select. In the quick sort, we will always choose a pivot to compare with other elements. After one iteration, we will get an array that all elements smaller than the pivot are on the left side of the pivot and all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order). So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be. Then we compare p with the K, if the p is smaller than the K, meaning the all element on the left of the pivot are all proper candidates but it is not adequate, we have to do the same thing on right side, and vice versa. If the p is exactly equal to the K, meaning that we've found the K-th position. Therefore, we just return the first K elements, since they are not greater than the pivot. */
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
// priority queue + hashmap 5% 7%
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Double> pq = new PriorityQueue<>();
        HashMap<Double, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < points.length; i++){
            double x = (double)points[i][0];
            double y = (double)points[i][1];
            double distance = Math.sqrt(x*x+y*y);
            pq.add(distance);
            List<Integer> temp;
            if(!map.containsKey(distance)){
                temp = new ArrayList<>();
            }
            else{
                temp = map.get(distance);
            }
            temp.add(points[i][0]);
            temp.add(points[i][1]);
            map.put(distance, temp);
        }
        int[][] arr = new int[K][2];
        for(int i = 0; i < K; i++){
            double res = pq.poll();
            List<Integer> l = map.get(res);
            arr[i][0] = l.get(0);
            arr[i][1] = l.get(1);
            l.remove(0);
            l.remove(0);
            map.put(res, l);
        }
        return arr;
    }
}