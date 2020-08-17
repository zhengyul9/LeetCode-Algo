/*
994.
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 
Example 1:

Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
*/
// 82 55 BFS
class Solution {
    class Fruit{
        int i;
        int j;
        public Fruit(int ia, int ja){
		//or Fruit(int ia, int ja){
            this.i = ia;
            this.j = ja;
        }
    }
    
    public int orangesRotting(int[][] grid) {
        Queue<Fruit> q = new LinkedList<>();
        int fresh = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2) {
                    q.add(new Fruit(i,j));
                }
                else if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }
        int s = q.size();
        int count = 0;
        int res = 0;
        while(fresh > 0 && s > 0){
            while(count < s){
                int i = q.peek().i;
                int j = q.peek().j;
                q.poll();
                if(i-1>=0 && grid[i-1][j] != 2 && grid[i-1][j] != 0){
                    q.add(new Fruit(i-1, j));
                    grid[i-1][j] = 2;
                }
                if(i+1<grid.length && grid[i+1][j] != 2 && grid[i+1][j] != 0){
                    q.add(new Fruit(i+1, j));
                    grid[i+1][j] = 2;
                }
                if(j-1>=0 && grid[i][j-1] != 2 && grid[i][j-1] != 0){
                    q.add(new Fruit(i, j-1));
                    grid[i][j-1] = 2;
                }
                if(j+1<grid[0].length && grid[i][j+1] != 2 && grid[i][j+1] != 0){
                    q.add(new Fruit(i, j+1));
                    grid[i][j+1] = 2;
                }
                count++;
            }
            s = q.size();
            fresh -= s;
            res++;
            count = 0;
        }
        return fresh == 0 ? res : -1;
    }
}