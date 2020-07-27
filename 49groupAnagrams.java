/******************************************************************************
49. Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

*******************************************************************************/
//Naive Algo O(mnl) where m is # of strings in strs, n is # of char in each string, l is the length of answer list
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<int[]> letterArrayList = new ArrayList<int[]>(strs.length);
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> each = new ArrayList<String>();
        if(strs.length == 0 || strs == null){
            return res;
        }
        each.add(strs[0]);
        res.add(each);
        if(strs.length == 1){
            return res;
        }
        char ch = 'a';
        int[] letter1 = new int[26];
            for (int j = 0; j < strs[0].length(); j++){
                int pos = strs[0].charAt(j) - ch;
                letter1[pos] ++;    
            }
        letterArrayList.add(letter1); 
        int round = 0;
        for (String i : strs){
            if(round == 0){
                round++;
                continue;
            }
            int[] letter = new int[26];
            for (int j = 0; j < i.length(); j++){
                int pos = i.charAt(j) - ch;
                letter[pos] ++;    
            }
            int flag = 0;
            for(int j = 0; j < letterArrayList.size(); j++){
                int k;
                for(k = 0; k < letter.length; k++){
                    if(letter[k] != letterArrayList.get(j)[k])
                        break;
                }
                if(k == letter.length && letter[k-1] == letterArrayList.get(j)[k-1]){
                    res.get(j).add(i);
                    break;
                }
                if(j == letterArrayList.size()-1)
                    flag = 1;
            }
            if(flag == 1){
                letterArrayList.add(letter);
                List<String> each1 = new ArrayList<String>();
                each1.add(i);
                res.add(each1);
                flag = 0;
            }
        }
            
        return res;
    }
}
//O(mnlogn) same mn
	public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
// modification of O(mnlogn) to O(mn) by not using sorting
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) ca[c - 'a']++;
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }