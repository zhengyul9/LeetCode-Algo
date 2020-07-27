/*
39.
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        combinationSum(result,new ArrayList<Integer>(),candidates,target,0);
        return result;
    }
    public void combinationSum(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target,int start) {
        if (target > 0) {
            for (int i = start;i < candidates.length;i++) { // not using the condition "target >= candidates[i]"
                cur.add(candidates[i]);
                combinationSum(result, cur, candidates, target-candidates[i],i);
                cur.remove(cur.size() - 1);
            }
        }
        if (target == 0) result.add(new ArrayList<Integer>(cur));
    }
}
