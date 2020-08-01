Dijkstra's algorithm only works when edges are positive values. Negative edge weight values/cycle will affect the greedy strategy of the algorithm.

Time Complexity:
O(NlogN+E) where there N node to be inserted to priority queue and each insert takes O(logN) time maintain the PQ. Each edge will be checked one time.

Space complexity:
O(V+E) stored in the priority queue