/*
149.
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
//1. calculating slope/gradient of each case
//2. using equation (y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
class Solution {
    public int maxPoints(int[][] points) {
        if(points.length == 0 || points[0].length == 0)
            return 0;
        int max = 0;
        for(int i = 0; i < points.length-1; i++){
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
            int rep = 0;
            int maxflag = 0;
            for(int j = i+1; j < points.length; j++){
                double k;
                if((points[i][0]-points[j][0]) != 0){
                    k = (double)(points[i][1]-points[j][1]) / (points[i][0]-points[j][0]);//k = tan(theta) = y/x
                }
                    
                else
                    k = Double.MAX_VALUE;
                if(k == -0.0)
                    k = 0.0;
                if(i == 0)
                    System.out.println(k);
                
                if((points[i][0] == points[j][0]) && (points[i][1] == points[j][1])){
                    rep += 1;
                    if(max == 0)
                        maxflag = 1;
                }
                else if(map.containsKey(k)){
                    int temp = map.get(k);
                    map.put(k,temp+1);
                    if(max < temp+1){
                        max = temp+1;
                        maxflag = 1;
                    }
                }
                else{
                    map.put(k,1);
                    if(max < 1){
                        max = 1;
                        maxflag = 1;
                    }
                }
            }
            if(maxflag == 1)
                max += rep;            
        }
        return max+1;//Math.min(max+1, points.length);
    }
}
//[[0,0],[94911151,94911150],[94911152,94911151]] out of double precision range

//method 2: calculating GCD(greatest common divisor)
/*
         *  A line is determined by two factors,say y=ax+b
         *
         *  If two points(x1,y1) (x2,y2) are on the same line(Of course).

         *  Consider the gap between two points.

         *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant

         *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b

         *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

         *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

         *  So we can use y0&x0 to track a line;
         */

        public int maxPoints(Point[] points) {
            if (points == null) return 0;
            if (points.length <= 2) return points.length;

            Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
            int result = 0;
            for (int i = 0; i < points.length; i++) {
                map.clear();
                int overlap = 0, max = 0;
                for (int j = i + 1; j < points.length; j++) {
                    int x = points[j].x - points[i].x;
                    int y = points[j].y - points[i].y;
                    if (x == 0 && y == 0) {
                        overlap++;
                        continue;
                    }
                    int gcd = generateGCD(x, y);
                    if (gcd != 0) {
                        x /= gcd;
                        y /= gcd;
                    }

                    if (map.containsKey(x)) {
                        if (map.get(x).containsKey(y)) {
                            map.get(x).put(y, map.get(x).get(y) + 1);
                        } else {
                            map.get(x).put(y, 1);
                        }
                    } else {
                        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
                        m.put(y, 1);
                        map.put(x, m);
                    }
                    max = Math.max(max, map.get(x).get(y));
                }
                result = Math.max(result, max + overlap + 1);
            }
            return result;


        }

        private int generateGCD(int a, int b) {
            if (b == 0) return a;
            else return generateGCD(b, a % b);

        }