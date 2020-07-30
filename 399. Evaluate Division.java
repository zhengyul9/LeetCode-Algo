/*
399.
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/
// naive 3ms 11.55 52 using hashmap to record graph edge weight, using hashset record history, dfs to find res。 HashMap struct: 1 dest node with 1 distance
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < equations.size(); i++){
            List<String> temp;
            if(!map.containsKey(equations.get(i).get(0))){
                temp = new LinkedList<>();
                temp.add(equations.get(i).get(1));
                temp.add(Double.toString(values[i]));
                map.put(equations.get(i).get(0), new LinkedList<>(temp));
            }    
            else{
                temp = map.get(equations.get(i).get(0));
                temp.add(equations.get(i).get(1));
                temp.add(Double.toString(values[i]));
                map.put(equations.get(i).get(0), new LinkedList<>(temp));
            }
            temp = new LinkedList<>();
            if(!map.containsKey(equations.get(i).get(1))){
                temp = new LinkedList<>();
                temp.add(equations.get(i).get(0));
                temp.add(Double.toString(1.0/values[i]));
                map.put(equations.get(i).get(1), new LinkedList<>(temp));
            }  
            else{
                temp = map.get(equations.get(i).get(1));
                temp.add(equations.get(i).get(0));
                temp.add(Double.toString(1.0/values[i]));
                map.put(equations.get(i).get(1), new LinkedList<>(temp));
            }
        }
        //System.out.print(map.get("c").get(1));
        double[] res = new double[queries.size()];
        for(int i = 0; i < queries.size();i++){
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), map, 1, new HashSet<>());
        }
        return res;
    }
    
    public double dfs(String q0, String q1, HashMap<String, List<String>> map, double cur, HashSet<String> set){  
        if(!map.containsKey(q0) || !map.containsKey(q1))
            return -1;
        if(set.contains(q0)) return -1;   
        List<String> l = map.get(q0);
        for(int i = 0; i < l.size(); i += 2){
            if(l.get(i).equals(q1)){
                return cur*Double.parseDouble(l.get(i+1));
            }
        }
        set.add(q0);
        double mul;
        for(int i = 0; i < l.size(); i += 2){
            mul = cur * Double.parseDouble(l.get(i+1));
            double r = dfs(l.get(i), q1, map, mul, set);
            if(r != -1) return r;
        }
        return -1;
    }
}