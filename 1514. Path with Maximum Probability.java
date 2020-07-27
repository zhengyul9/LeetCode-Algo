/*
1514.
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 
Example 1:

Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:

Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:



Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
 

Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
*/
//bellman ford 100% 100%, Analysis: Time: O(n * E), space: O(n + E), where E = edges.length.
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g.computeIfAbsent(a, l -> new ArrayList<>()).add(new int[]{b, i});
            g.computeIfAbsent(b, l -> new ArrayList<>()).add(new int[]{a, i});
        }
        double[] p = new double[n];
        p[start] = 1d;
        Queue<Integer> q = new LinkedList<>(Arrays.asList(start));
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int[] a : g.getOrDefault(cur, Collections.emptyList())) {
                int neighbor = a[0], index = a[1];
                if (p[cur] * succProb[index] > p[neighbor]) {
                    p[neighbor] = p[cur] * succProb[index];
                    q.offer(neighbor);
                }
            }
        }
        return p[end];
    }
}
//Dijkstra algo 100% 100% Analysis: Time: O(n + Elogn), space: O(n + E), where E = edges.length.
class Solution {
     public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g.computeIfAbsent(a, l -> new ArrayList<>()).add(new int[]{b, i});    
            g.computeIfAbsent(b, l -> new ArrayList<>()).add(new int[]{a, i});    
        }
        double[] p = new double[n];
        p[start] = 1d;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> -p[i]));
        pq.offer(start);
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            if (cur == end) {
                return p[end];
            }
            for (int[] a : g.getOrDefault(cur, Collections.emptyList())) {
                int neighbor = a[0], index = a[1];
                if (p[cur] * succProb[index] > p[neighbor]) {
                    p[neighbor] = p[cur] * succProb[index];
                    pq.offer(neighbor);
                }
            }
        }
        return 0d;
    }
}
// Floyd–Warshall Algorithm for Every Pair of Nodes - LTE
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
       double[][] probs = new double[n][n];

        for (int i = 0; i < succProb.length; ++i) {
	        int a = edges[i][0];
	        int b = edges[i][1];
	        probs[a][b] = probs[b][a] = succProb[i];
        }

        for (int k = 0; k < n; ++k) {
	        for (int i = 0; i < n; ++i) {
		        for (int j = 0; j < n; ++j) {
			        probs[i][j] = Math.max(probs[i][j], probs[i][k] * probs[k][j]);
		        }
	        }
        }
        return probs[start][end];
    }   
}
//Python Bellman ford
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start: int, end: int) -> float:
        g, dq = defaultdict(list), deque([start])
        for i, (a, b) in enumerate(edges):
            g[a].append([b, i])
            g[b].append([a, i])
        p = [0.0] * n    
        p[start] = 1.0
        while dq:
            cur = dq.popleft()
            for neighbor, i in g.get(cur,[]):
                if p[cur] * succProb[i] > p[neighbor]:
                    p[neighbor] = p[cur] * succProb[i]
                    dq.append(neighbor)
        return p[end]
//Python Dijkstra
 def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start: int, end: int) -> float:
        p, g = [0.0] * n, defaultdict(list)
        for index, (a, b) in enumerate(edges):
            g[a].append((b, index))
            g[b].append((a, index))
        p[start] = 1.0
        heap = [(-p[start], start)]    
        while heap:
            prob, cur = heapq.heappop(heap)
            if cur == end:
                return -prob
            for neighbor, index in g.get(cur, []):
                if -prob * succProb[index] > p[neighbor]:
                    p[neighbor] = -prob * succProb[index]
                    heapq.heappush(heap, (-p[neighbor], neighbor))
        return 0.0