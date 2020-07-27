/*
1288.
Given a list of intervals, remove all intervals that are covered by another interval in the list. Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 

Constraints:

1 <= intervals.length <= 1000
0 <= intervals[i][0] < intervals[i][1] <= 10^5
intervals[i] != intervals[j] for all i != j
*/
// O(n^2) algo, sorting first, then check each case. 11%, 85%
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        int count = intervals.length;
        for(int i = 0; i < intervals.length; i++){
            for(int j = 0; j < intervals.length; j++){
                //skip self, removed and processed intervals
                if(i == j || intervals[i][0] == -1 || intervals[i][0] > intervals[j][0])
                    continue;
                //tie start index
                else if(intervals[i][0] == intervals[j][0]){
                    if(intervals[i][1] <= intervals[j][1]){
                        count--;
                        intervals[i][0] = -1;
                        break;
                    }else{
                        intervals[j][0] = -1;
                        count--;
                    }
                }
                //larger start index
                else if(intervals[j][1] <= intervals[i][1]){
                    count--;
                    intervals[j][0] = -1;
                }
            }
        }
        return count;
    }
}
//O(nlgn) after sorting v[0], left>left and right>right would be a new interval so just O(n) check this.
//96.56, 88
class Solution {
    public int removeCoveredIntervals(int[][] A) {
        int res = 0, left = -1, right = -1;
        Arrays.sort(A, (a, b) -> a[0] - b[0]);
        for (int[] v : A) {
            if (v[0] > left && v[1] > right) {
                left = v[0];
                ++res;
            }
            right = Math.max(right, v[1]);
        }
        return res;
    }
}
//sort v[0] ascending v[1] decending, only check right then, same performance
class Solution {
    public int removeCoveredIntervals(int[][] A) {
        int res = 0, right = 0;
        Arrays.sort(A, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int[] v : A) {
            if (v[1] > right) {
                ++res;
                right = v[1];
            }
        }
        return res;
    }
}