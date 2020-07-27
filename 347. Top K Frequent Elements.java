/*
347.
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
*/
// hashtable stores frequency of each element, add the same fre element to a list, retrieve from top list 
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
	    List<Integer>[] bucket = new List[nums.length + 1];
	    Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
	    for (int n : nums) {
		    frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	    }
	    for (int key : frequencyMap.keySet()) {
		    int frequency = frequencyMap.get(key);
		    if (bucket[frequency] == null) {
			    bucket[frequency] = new ArrayList<>();
		    }
		    bucket[frequency].add(key);
	    }
	    int[] res = new int[k];
        int j = 0;  
	    for (int pos = bucket.length - 1; pos >= 0 && j < k; pos--) {
		    if (bucket[pos] != null) {
                for(int n : bucket[pos]){
                    res[j] = n;
                    j++;
                }
		    }
	    }
	    return res;
    }
}
