/*
451.
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/
//bucket sorting 37.32 13.00
public class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) 
            map.put(c, map.getOrDefault(c, 0) + 1);
						
        List<Character> [] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }
				
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >= 0; pos--)
            if (bucket[pos] != null)
                for (char c : bucket[pos])
                    for (int i = 0; i < pos; i++)
                        sb.append(c);

        return sb.toString();
    }
}
//Priority queue 25 10
public class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
						
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
				
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            for (int i = 0; i < (int)e.getValue(); i++) 
                sb.append(e.getKey());
        }
        return sb.toString();
    }
}
// my version: works when only letters(lower or upper case) appears in the string
class Solution {
    public String frequencySort(String s) {
        int[] count = new int[26];
        //int[] count = new int[58];
        
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
            //count[s.charAt(i) - 'A']++;
        }
        List<List<Character>> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++)
            list.add(new ArrayList<>());
        for(int i = 0; i < count.length; i++){
            if(count[i] > 0){
                list.get(count[i]-1).add((char)('a'+i));
                //list.get(count[i]-1).add((char)('A'+i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = list.size()-1; i >= 0; i--){
            for(int k = 0; k < list.get(i).size(); k++){
                for(int j = 0; j < i+1; j++){
                    sb.append(list.get(i).get(k));
                }
            }
        }
        return sb.toString();
        
    }
}