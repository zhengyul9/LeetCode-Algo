/*
1232.
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.

Example 1:

Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true
Example 2:

Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false

Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
*/
// using Math.tan()/Math.atan() must convert to doulbe inside the function 23% 28%
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) return true;
        double truth;
        if((coordinates[1][0]-coordinates[0][0]) == 0) truth = Integer.MAX_VALUE; 
        else truth = Math.atan((double)(coordinates[1][1]-coordinates[0][1])/(coordinates[1][0]-coordinates[0][0]));

        for(int i = 2; i < coordinates.length; i++){
            if(truth == Integer.MAX_VALUE){
                if((coordinates[i][0]-coordinates[i-1][0]) != 0)
                    return false;
            }
            else{ 
                //stem.out.println(Math.tan((coordinates[i][1]-coordinates[i-1][1])/(coordinates[i][0]-coordinates[i-1][0])));
                if((coordinates[i][0]-coordinates[i-1][0]) == 0) return false;
                if(truth != Math.atan((double)(coordinates[i][1]-coordinates[i-1][1])/(coordinates[i][0]-coordinates[i-1][0]))){
                    return false;
                }
            }
        }
        return true;
    }
}
// slope formula y2-y1/x2-x1 = y3-y1/x3-y1
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0], y0 = coordinates[0][1], 
            x1 = coordinates[1][0], y1 = coordinates[1][1];
        int dx = x1 - x0, dy = y1 - y0;
        for (int[] co : coordinates) {
            int x = co[0], y = co[1];
            if (dx * (y - y1) != dy * (x - x1))
                return false;
        }
        return true;
    }
}