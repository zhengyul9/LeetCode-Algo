/*
300.
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/
//dp O(n^2) 40, 68.79
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
//O(nlogn) using treemap
class Solution {
    public int lengthOfLIS(int[] nums) {
        int max = 1;

        // save previus result
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0); // base case

        // count mapping to remove duplicate count
        Map<Integer, Integer> count = new HashMap<>();

        for(int a : arr) {
            int floor = dp.floorKey(a - 1); // strictly descending floor
            int c = dp.get(floor);
            int nc = c + 1; // new sequence count
            if(count.containsKey(nc)) {
                // remove old entry if exists
                int old = count.get(nc);
                dp.remove(old);
            }

            if(nc > max) {
                // new max
                max = nc;
            }

            // both way mapping
            dp.put(a, nc);
            count.put(nc, a);
        }

        return max;
    }
}

//O(nlgn) binary search， 100%， 87.11
class Solution {
    public int lengthOfLIS(int[] nums) {
        int size = nums.length;
        if(size == 0)
            return 0;
        // Add boundary case, when array size is one 
  
        int[] tailTable = new int[size]; 
        int len; // always points empty slot 
  
        tailTable[0] = nums[0]; 
        len = 1; 
        for (int i = 1; i < size; i++) { 
            if (nums[i] < tailTable[0]) 
                // new smallest value 
                tailTable[0] = nums[i]; 
  
            else if (nums[i] > tailTable[len - 1]) 
                // A[i] wants to extend largest subsequence 
                tailTable[len++] = nums[i]; 
            else
                // A[i] wants to be current end candidate of an existing 
                // subsequence. It will replace ceil value in tailTable 
                tailTable[CeilIndex(tailTable, -1, len - 1, nums[i])] = nums[i]; 
        } 
  
        return len; 
    }
    
    private int CeilIndex(int A[], int l, int r, int key) 
    { 
        while (r - l > 1) { 
            int m = l + (r - l) / 2; 
            if (A[m] >= key) 
                r = m; 
            else
                l = m; 
        } 
        return r; 
    } 
}