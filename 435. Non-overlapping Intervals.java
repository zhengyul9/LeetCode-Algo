/*
435.
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 
Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
*/
// sorting by first then second. Then one pass 100% 
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;
        //Arrays.sort(intervals, Comparator.comparingInt(a->a[0]).Comparator.comparingInt(a->a[1]));
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(final int[] entry1, final int[] entry2) {
                final int time1 = entry1[0];
                final int time2 = entry2[0];
                final int time3 = entry1[1];
                final int time4 = entry2[1];
                if(time1 == time2){
                    return time3-time4;
                }
                return time1-time2;
            }
        });
        int curStart = intervals[0][0];
        int curEnd = intervals[0][1];
        int res = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] == curStart){
                res++;
            }
            else if(intervals[i][1] <= curEnd){
                curStart = intervals[i][0];
                curEnd = intervals[i][1];
                res++;
            }
            else if(intervals[i][0] < curEnd && intervals[i][1] > curEnd){
                curStart = intervals[i][0];
                res++;
            }
            else{
                curStart = intervals[i][0];
                curEnd = intervals[i][1];
            }
        }
        return res;
    }
}