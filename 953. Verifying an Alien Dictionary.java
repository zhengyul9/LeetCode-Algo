/*
953.
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 
Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/
// 57 9 hashmap to store, compare letter of each word in order
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if(words.length == 1) return true;
        HashMap<Character, Integer> map = new HashMap<>();
        int k = 1;
        for(char c : order.toCharArray()){
            map.put(c,k);
            k++;
        }
        int len = words[0].length();
        for(String s : words){
            len = Math.max(len, s.length());
        }
        for(int i = 0; i < len; i++){
            int flag = 0;
            for(int j = 0; j < words.length-1; j++){
                if(i < words[j].length() && i >= words[j+1].length())
                    return false;
                else if(i >= words[j].length()){
                    continue;
                }
                else if(i < words[j].length()){
                    if(map.get(words[j].charAt(i)) > map.get(words[j+1].charAt(i))){
                        return false;
                    }
                    else if(map.get(words[j].charAt(i)) == map.get(words[j+1].charAt(i))){
                        flag = 1;
                        continue;
                    }
                }
            }
            if(flag == 1) continue;
            return true;
        }
        return true;
    }
}
//a better one using normal array to store ranking of each letter 100 90 Time O(NS) Space O(1)
class Solution {
    int[] mapping = new int[26];
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i]))
                return false;
        return true;
    }

    boolean bigger(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = 0; i < n && i < m; ++i)
            if (s1.charAt(i) != s2.charAt(i))
                return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
        return n > m;
    }
}