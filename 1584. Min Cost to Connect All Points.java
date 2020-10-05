/*
1584.
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

Example 1:

Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
Example 3:

Input: points = [[0,0],[1,1],[1,0],[-1,1]]
Output: 4
Example 4:

Input: points = [[-1000000,-1000000],[1000000,1000000]]
Output: 4000000
Example 5:

Input: points = [[0,0]]
Output: 0

Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
*/
//Prim algo without using priority queue. Using set to replace pq 80 100
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, ans = 0;
        HashSet<Integer> mst = new HashSet<>();
        mst.add(0);
        int[] dist = new int[n];
        for(int i = 1; i < n; i++) dist[i] = findDist(points, 0, i);
        while(mst.size() != n) {
            // Find the node that has shortest distance
            int next = -1;
            for(int i = 0; i < n; i++) {
                if(mst.contains(i)) continue;
                if(next == -1 || dist[next] > dist[i]) next = i;
            }
            // Put the node into the Minning Spanning Tree
            mst.add(next);
            ans += dist[next];
            // Update distance array
            for(int i = 0; i < n; i++) {
                if(!mst.contains(i)) {
                    dist[i] = Math.min(dist[i], findDist(points, i, next));
                }
            }
        }
        return ans;
    }
    private int findDist(int[][] points, int a, int b) {
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }
}
//union find using pq 82 70
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                pq.add(new int[]{ findDist(points, i, j), i, j });
            }
        }
        int count = 0;
        UnionFind uf = new UnionFind(n);
        while(count < n-1) {
            int[] edge = pq.poll();
            if(uf.find(edge[1]) != uf.find(edge[2])) {
                ans += edge[0];
                count++;
                uf.union(edge[1], edge[2]);
            }
        }
        return ans;
    }
    private int findDist(int[][] points, int a, int b) {
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }
    class UnionFind {   
        int[] parent;
        UnionFind(int n) {
            this.parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }		
        public void union(int a, int b) {
            parent[find(a)] = parent[find(b)];
        }
        public int find(int x) {
            if(parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
    }
}