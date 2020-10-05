/*
290.
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
*/
//100 
class Solution {
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> unique = new HashSet<>();
        String[] input = str.split(" ");
		if (input.length != pattern.length())
			return false;
        int i = 0;
        for(char c :pattern.toCharArray()){
            if(!map.containsKey(c)){
                if(unique.contains(input[i])) return false;
                map.put(c, input[i]);
                unique.add(input[i]);
            }
            else 
                if(!map.get(c).equals(input[i]))
                    return false;
            i++;
            
        }
        return true;
    }
}
//shorter and better one
class Solution {
    public boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length())
			return false;
		Map index = new HashMap();
		for (Integer i=0; i<words.length; ++i)
			if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
				return false;
		return true;
	}
}