/*
17.
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note: Although the above answer is in lexicographical order, your answer could be in any order you want.
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if(digits.length() == 0)
            return res;
        String[] map = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> q = new LinkedList<>();
        int num, size;
        String temp;
        for(int i = 0; i < digits.length(); i++){
            num = Character.getNumericValue(digits.charAt(i));
            size = q.size();
            if(q.isEmpty()){
                for(int j = 0; j < map[num-2].length(); j++){
                    q.add(Character.toString(map[num-2].charAt(j)));
                }
            }
            else{//not empty
                for(int j = 0; j < size; j++){
                    temp = q.remove();
                    for(int k = 0; k < map[num-2].length(); k++){
                        q.add(temp + Character.toString(map[num-2].charAt(k)));
                    }
                }
            }
        }
        while(!q.isEmpty()){
            res.add(q.remove());
        }
        return res;
    }
}