/*
46.
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/
//backtracking 50%, 11%
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        //list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, 0);
            if(tempList.size() == nums.length){
                list.add(new ArrayList<>(tempList));                
            }
            tempList.remove(tempList.size() - 1);
        }
    }
}