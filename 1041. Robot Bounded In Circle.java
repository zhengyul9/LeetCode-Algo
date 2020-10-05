/*
1041.
On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.
Example 3:

Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 
Note:

1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}
*/
// 100% check if return to original or directiobn changed at the end
class Solution {
    public boolean isRobotBounded(String instructions) {
        //initDirection = 0; 0 N, 1 W, 2 S, 3 E
        int curD = 0;
        int x = 0, y = 0;
        for(int i = 0; i < instructions.length(); i++){
            if(instructions.charAt(i) == 'G'){
                if(curD == 0) y++;
                else if(curD == 1) x--;
                else if(curD == 2) y--;
                else x++;
            }
            else{
                curD = changeDirection(curD, instructions.charAt(i));
            }
        }
        if(x == 0 && y == 0) return true;
        if(curD != 0) return true;
        return false;
    }
    private int changeDirection(int cur, char d){
        if(d == 'L'){
            cur = (cur+1)%4;
        }
        else{
            cur--;
            if(cur < 0) cur = 3;
        }
        return cur;
    }

}