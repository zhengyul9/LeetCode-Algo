/*
216.
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/
// dfs keep increasing value to avoid repeated without using set. 100%
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, n, 0, 1, new ArrayList<Integer>());
        return res;
    }
    
    private void dfs(int k, int n, int sum, int start, List<Integer> each){
        if(sum > n){
            return;
        }
        if(sum == n){
            if(each.size()==k) res.add(new ArrayList<>(each));
            return;
        }
        for(int i = start; i <= 9; i++){
            each.add(i);
            dfs(k, n, sum+i, i+1, each);
            each.remove(each.size()-1);
            
        }
    }
}