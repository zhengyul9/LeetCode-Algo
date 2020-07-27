/*
118.
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/
//normal method
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> basecase = new ArrayList<Integer>();
        if(numRows == 0)
            return res;
        basecase.add(1);
        res.add(basecase);
        if(numRows == 1)
            return res;
        List<Integer> basecase1 = new ArrayList<Integer>();
        basecase1.add(1);basecase1.add(1);
        res.add(basecase1);
        for(int i = 2; i < numRows; i++){
            List<Integer> round = new ArrayList<Integer>();
            round.add(1);
            for(int j = 1; j < i; j++){
                round.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
            }
            round.add(1);
            res.add(round);
        }
        return res;  
    }
}