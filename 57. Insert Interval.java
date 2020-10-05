/*
57.
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
//one pass
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0) return new int[][]{newInterval};
        int i = 0;
        List<int[]> list = new ArrayList<>();
        while(i < intervals.length) {
            int[] interval = intervals[i];
            if(interval[1] < newInterval[0]) {
                list.add(interval);
            }
            else if(interval[0] > newInterval[1]) {
                list.add(newInterval);
                newInterval = interval;
            } else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);    
            }
            i++;
        }
        list.add(newInterval);
       
        int[][] result = new int[list.size()][2];
        for(int j = 0; j < list.size(); j++) result[j] = list.get(j);
        return result;
    }
}