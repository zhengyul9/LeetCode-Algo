/*
88.
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
 */
//dp O(n) 100%, 6%
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;
        m = m - 1;
        n = n - 1;
        while(m >= 0 || n >= 0){
            int tempm = Integer.MIN_VALUE;
            int tempn = Integer.MIN_VALUE;
            if(m >=0){
                tempm = nums1[m];
            }
            if(n >= 0){
                tempn = nums2[n];
            }
            if(tempn>tempm){
                nums1[last] = nums2[n];
                n--;
            }
            else{
                nums1[last] = nums1[m];
                m--;
            }
            last--;
        }
    }
}
//python3  89.23, 6
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        last = m + n - 1
        m = m - 1
        n = n - 1
        while(m >= 0 or n >= 0):
            tempm = -1000
            tempn = -1000
            if(m >= 0):
                tempm = nums1[m]
            if(n >= 0):
                tempn = nums2[n]
            if(tempn>tempm):
                nums1[last] = nums2[n]
                n -= 1
            else:
                nums1[last] = nums1[m]
                m -= 1
            last -= 1
//better one 97.48, 6
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        while m > 0 and n > 0:
            if nums1[m-1] >= nums2[n-1]:
                nums1[m+n-1] = nums1[m-1]
                m -= 1
            else:
                nums1[m+n-1] = nums2[n-1]
                n -= 1
        if n > 0:
            nums1[:n] = nums2[:n]