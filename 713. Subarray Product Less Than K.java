/*
713.
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
*/
//sliding windows
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int cnt = 0;
        int pro = 1;
        for (int i = 0, j = 0; j < nums.length; j++) {
            pro *= nums[j];
            while (i <= j && pro >= k) {
                pro /= nums[i++];
            }
            cnt += j - i + 1;
        }
        return cnt;        
    }
}
// have no idea why wrong
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int sum = 1;
        int start = 0;
        for(int i = 0; i < nums.length && start < nums.length; i++){
            if(nums[i] > k){
                start = i+1;
                sum = 1;
            }
            else if(sum * nums[i] >= k){
                sum /= nums[start];
                start++;
                i--;
                System.out.println("here");
            }
            else{
                sum *= nums[i];
                res += i-start+1;
            }
        }
        return res;
    }
}