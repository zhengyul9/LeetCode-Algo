/*
763.
A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 
Note:

S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.
*/
//dp check each char in one pass.
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] dict = new int[26];
        int[] curr = new int[26];
        for(char c : S.toCharArray()){
            dict[c-'a']++;
        }
        List<Integer> res = new LinkedList<>();
        int len = 0;
        int total = 0;
        for (char c : S.toCharArray()) {
            len ++;
            if (curr[c-'a'] == 0) {
                total ++;
            }
            curr[c-'a']++;
            if (curr[c-'a'] == dict[c-'a']) {
                total --;
            }
            if (total == 0) {
                res.add(len);
                len = 0;
            }
        }
        return res;
    }
}
//using hashset loop back at each char
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] map = new int[26];
        for(char c : S.toCharArray()){
            map[c-'a']++;
        }
        
        List<Integer> list = new LinkedList<>();
        HashSet<Character> set = new HashSet<>();
        int length = 0;
        for(char c : S.toCharArray()){
            set.add(c);
            length++;
            map[c-'a']--;
            int flag = 0;
            for(char d : set){
                if(map[d-'a'] > 0) {flag = 1; break;}
            }
            if(flag == 0){
                list.add(length);
                set = new HashSet<>();
                length = 0;
            }
        }
        return list;
    }
}