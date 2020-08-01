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
//use this O(mn) where m is word legnth and n is word number. string valueOf takes O(n) but bucket char[26] is constant so its constant
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        if(strs.length == 0) return new LinkedList<>();
        for(int i = 0; i < strs.length; i++){  
            char[] temp = new char[26];
            for(char c : strs[i].toCharArray()){
                temp[c-'a']++;
            }
            String s = String.valueOf(temp);
            if(!map.containsKey(s)) map.put(s,new LinkedList<>());
            map.get(s).add(strs[i]);
        }
        return new LinkedList<>(map.values());
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