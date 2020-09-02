/*
题目描述
统计一个数字在升序数组中出现的次数。
*/
//binary search find lower and upper bound
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        int lbound = 0, rbound = 0;
        // 寻找上界
        int l = 0, r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] < k) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        lbound = l;
        // 寻找下界
        l = 0;
        r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] <= k) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        rbound = l;
        return rbound - lbound;
    }
}