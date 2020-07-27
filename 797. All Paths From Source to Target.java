/*
797.
Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/
// 56 6 DFS method
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {   
        List<Integer> e = new LinkedList<>();
        e.add(0);
        dfs(graph, 0, e);
        return res;
    }
    
    public void dfs(int[][] graph, int target, List<Integer> each){
        if(target == graph.length - 1) {
            res.add(new LinkedList<>(each));
            return;
        }
        for(int i = 0; i < graph[target].length; i++){
            each.add(graph[target][i]);
            dfs(graph, graph[target][i], each);
            each.remove(each.size()-1);
        }
    }
}