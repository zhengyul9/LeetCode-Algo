/*
40.
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/
//36 5 O(n * 2 ^ n) DFS
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<>(), 0);
        return res;
    }
    
    public void dfs(int[] c, int target, int start, List<Integer> each, int sum){
        if(sum == target) {
            res.add(new ArrayList<>(each));
            return;
        }
        else if(sum > target) return;
        
        for(int i = start; i < c.length; i++){
            if(i > start && c[i] == c[i-1]) continue; // avoid duplicate
            sum += c[i];
            each.add(c[i]);
            dfs(c, target, i+1, each, sum);
            each.remove(each.size()-1);
            sum -= c[i];
        }
    }
}