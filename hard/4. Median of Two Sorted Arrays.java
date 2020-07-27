/*
4.
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/
//Binary search 100%, 59.4
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        if(nums1.length>nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int x = nums1.length, y = nums2.length;
        
        int l = 0;
        int r = x;
        while(l<=r)
        {
            int px = (l+r)/2;
            int py = (x+y+1)/2 - px;
            
         //   System.out.println(l + " " + r);
            int maxLeftX = (px==0)? Integer.MIN_VALUE: nums1[px-1];
            int maxLeftY = (py==0)? Integer.MIN_VALUE: nums2[py-1];
            int minRightX = (px==x)? Integer.MAX_VALUE: nums1[px];
            int minRightY = (py==y)? Integer.MAX_VALUE: nums2[py];
          //  System.out.println( maxLeftX+" , "+ maxLeftY+" , "+ minRightX +" , "+ minRightY);
            if(maxLeftX<=minRightY && maxLeftY<=minRightX)
            {
                if(((x+y)&1)==1)
                    return (double)Math.max(maxLeftX, maxLeftY);
                return ((double)Math.max(maxLeftX,maxLeftY) + Math.min(minRightX, minRightY))/2;
            }
            if(maxLeftX>minRightY)
                r = px-1;
            else
                l = px+1;
        }
        return -1.0;
    }
}
//binary search 18.63,9.61
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 < N2) return findMedianSortedArrays(nums2, nums1);	// Make sure A2 is the shorter one.
    
        int lo = 0, hi = N2 * 2;
        while (lo <= hi) {
            int mid2 = (lo + hi) / 2;   // Try Cut 2 
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
            
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];
        
            if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
            else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
            else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	// Otherwise, that's the right cut.
        }
        return -1;
    }
}
//improved 38.38, 13.14
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
	    int m = A.length, n = B.length;
	    int l = (m + n + 1) / 2;
	    int r = (m + n + 2) / 2;
	    return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
	}

public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
	if (aStart > A.length - 1) return B[bStart + k - 1];            
	if (bStart > B.length - 1) return A[aStart + k - 1];                
	if (k == 1) return Math.min(A[aStart], B[bStart]);
	
	int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
	if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1]; 
	if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];        
	
	if (aMid < bMid) 
	    return getkth(A, aStart + k/2, B, bStart,       k - k/2);// Check: aRight + bLeft 
	else 
	    return getkth(A, aStart,       B, bStart + k/2, k - k/2);// Check: bRight + aLeft
}
}