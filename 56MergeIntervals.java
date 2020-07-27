/*
56.
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
//100%, 7.57%
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0 || intervals.length == 1){
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0]>b[0])
                    return 1;
                else if(a[0] < b[0])
                    return -1;
                else
                    return 0;
            }
        });
        
        int[][] res = new int[intervals.length][2];
        res[0][0] = intervals[0][0];
        res[0][1] = intervals[0][1];
        int size = intervals.length;
        int i = 1;
        int cur = 0;
        int end = 0;
        
        while(i < size){
            if(intervals[i][0] <= res[cur][1]){
                if(intervals[i][1] >= res[cur][1])
                    res[cur][1] = intervals[i][1];
            }
            else{
                cur++;
                res[cur][0] = intervals[i][0];
                res[cur][1] = intervals[i][1];
            }
            i++;
        }
        int[][] newres = Arrays.copyOfRange(res, 0, ++cur);
        return newres;
    }
}