/*
334.
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false
*/
// clever method 42 80
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
        for(int n : nums){
            if(n <= min)
                min = n;
            else if(n <= max){
                max = n;
            }              
            else 
                return true;
        }
        return false;
    }
}
//same performance array method
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3)
        {
            return false;
        }    
        int[] arr = new int[3];
        Arrays.fill(arr, Integer.MAX_VALUE);    
        for(int i = 0; i < nums.length; i++)
        {
        
            for(int j = 0; j < 3 ; j++)
            {
                if(arr[j] >= nums[i])
                {
                    arr[j] = nums[i];
                    break;
                }
            }
        }    
        for(int i = 0; i < 3 ; i++)
        {
            if(arr[i] == Integer.MAX_VALUE)
            {
                return false;
            }
        }    
        return true;
    }
}