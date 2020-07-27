/*
207.
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 

Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
 */
// naive solution using dynamic programming 5, 5
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0)
            return true;
        HashSet<Integer> learned = new HashSet<Integer>();
        int[][] dp = new int[numCourses][numCourses];
        //build dp, row is pre of dp[i]
        for(int i = 0; i < prerequisites.length; i++){
            int row = prerequisites[i][0];
            int col = prerequisites[i][1];
            dp[row][col] = 1;
        }
        int flag = 0;
        while(flag == 0){
            for(int i = 0; i < dp.length; i++){
                if(learned.contains(i))
                    continue;
                int count = 0;
                for(int j = 0; j < dp[0].length; j++){
                    if(dp[i][j] == 1)
                        continue;
                    else
                        count++;
                    //no prereq
                    if(count == dp[0].length){//all zero in a row, no prereq
                        learned.add(i);//add to learned set
                        for(int k = 0; k < dp.length; k++){// finish prereq
                            dp[k][i] = 0;
                        }
                        i = -1;
                        break;
                    }
                }
                if(learned.size() == numCourses)
                    return true;              
            }
            return false;
        }
        return false;
    }
}
// Naive DP + BFS using a queue to offer class 34.68, 5.39
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0)
            return true;
        int[] numPrereq = new int[numCourses];
        int[][] dp = new int[numCourses][numCourses];
        //build dp, col is pre of dp[i]
        for(int i = 0; i < prerequisites.length; i++){
            int row = prerequisites[i][1];
            int col = prerequisites[i][0];
            numPrereq[col]++;
            dp[row][col] = 1;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i=0; i<numPrereq.length; i++) {
            if (numPrereq[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i=0; i<numCourses; i++) {
                if (dp[course][i] != 0) {
                    if (--numPrereq[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    
    }
}