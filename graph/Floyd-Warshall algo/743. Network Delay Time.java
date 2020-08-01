/*
743.
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Example 1:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2
 
Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
*/
//dijkstra's algo 33 5 37ms O(NlogN)+O(E) + O(VE) space
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for(int[] a : times){
            List<int[]> li = map.containsKey(a[0]) ? map.get(a[0]) : new LinkedList<>();
            li.add(new int[]{a[1],a[2]});
            map.put(a[0],li);
        }
        boolean[] arr = new boolean[N+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1]));
        pq.offer(new int[]{K, 0});
        while(!pq.isEmpty()){
            int[] a = pq.poll();  
            int cur = a[0];
            int cost = a[1];
            if(arr[cur] == true) continue;
            arr[cur] = true;
            N--;
            if(map.containsKey(cur)){
                List<int[]> temp = map.get(cur);
                for(int i = 0; i < temp.size(); i++){
                    pq.offer(new int[]{temp.get(i)[0], cost+temp.get(i)[1]});
                }
            }
            if(N == 0) return cost;
        }
        return -1;
    }
}
//Bellman ford 26 51 45ms O(N*E) + O(N) space
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        double[] disTo = new double[N];
        Arrays.fill(disTo, Double.POSITIVE_INFINITY);
        disTo[K - 1] = 0;
        for (int i = 1; i < N; i++) {//N
            for (int[] edge : times) { //E
                int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                disTo[v] = Math.min(disTo[v], disTo[u] + w);
            }
        }
        double res = Double.MIN_VALUE;
        for (double i: disTo) {
            res = Math.max(i, res);
        }
        return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
    }
}
//Floyd-Warshall algorithm O(n^3) + O(n^2) space  55 5 25ms
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        double[][] disTo = new double[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(disTo[i], Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < N; i++) {
            disTo[i][i] = 0;
        }
        for (int[] edge: times) {
            disTo[edge[0] - 1][edge[1] - 1] = edge[2];
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (disTo[i][j] > disTo[i][k] + disTo[k][j])
                        disTo[i][j] = disTo[i][k] + disTo[k][j];
                }
            }   
        }
        double max = Double.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (disTo[K - 1][i] == Double.POSITIVE_INFINITY) return -1;
            max = Math.max(max, disTo[K - 1][i]);
        }
        return (int) max;
    }
}