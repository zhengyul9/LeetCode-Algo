/*
128.
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
//hashmap method O(n) 45%,28%
class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        for(int n : nums){
            if(map.containsKey(n)){
                continue;
            }
            else{
                int left = 0;
                int right = 0;
                if(map.containsKey(n-1)){
                    left = map.get(n-1);
                }
                if(map.containsKey(n+1)){
                    right = map.get(n+1);
                }
                map.put(n,left+right+1);
                if(map.containsKey(n-1)){
                    map.put(n-left,left+right+1);
                }
                if(map.containsKey(n+1)){
                    map.put(n+right,left+right+1);
                }
                max = Math.max(max, left+right+1);
            }
        }
        return max;
    }
}

// set method 91.67, 15.52
class Solution {
       public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for(int n : nums) {
                set.add(n);
            }
            int best = 0;
            for(int n : set) {
                if(!set.contains(n - 1)) {  // only check for one direction
                    int m = n + 1;
                    while(set.contains(m)) {
                        m++;
                    }
                    best = Math.max(best, m - n);
                }
            }
            return best;
        }
}