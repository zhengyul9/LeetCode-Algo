/*
735.
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: 
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
Example 2:
Input: 
asteroids = [8, -8]
Output: []
Explanation: 
The 8 and -8 collide exploding each other.
Example 3:
Input: 
asteroids = [10, 2, -5]
Output: [10]
Explanation: 
The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
Example 4:
Input: 
asteroids = [-2, -1, 1, 2]
Output: [-2, -1, 1, 2]
Explanation: 
The -2 and -1 are moving left, while the 1 and 2 are moving right.
Asteroids moving the same direction never meet, so no asteroids will meet each other.
Note:

The length of asteroids will be at most 10000.
Each asteroid will be a non-zero integer in the range [-1000, 1000]..
*/
//10ms add to list when empty and negative value at the last. Otherwise, check from last element to the place to insert the value, if needed. 10ms 20% 5%
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new LinkedList<>();
        for(int i = 0;i < asteroids.length; i++){
            if(list.size() == 0 || list.get(list.size()-1) < 0 || asteroids[i] > 0){
                list.add(asteroids[i]);
            }
            else if(asteroids[i] < 0){   
                int end = 0;
                while(list.size() != 0 && (list.get(list.size()-1) > 0 && list.get(list.size()-1) <= -asteroids[i])){
                    if(list.get(list.size()-1) == -asteroids[i]){
                        list.remove(list.size()-1);
                        end = 1;
                        break;
                    }
                    list.remove(list.size()-1);
                }
                if(end == 1) continue;
                if(list.size()==0 || list.get(list.size()-1) < 0)
                    list.add(asteroids[i]);
            }
        }
        int[] res = new int[list.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = list.get(i);
        }
        return res;
		//return list.stream().mapToInt(i->i).toArray(); without loop to convert list to array
    }
}
// shorter one same performance and method
class Solution {
    public int[] asteroidCollision(int[] a) {
        LinkedList<Integer> s = new LinkedList<>(); // use LinkedList to simulate stack so that we don't need to reverse at end.
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0 || s.isEmpty() || s.getLast() < 0)
                s.add(a[i]);
            else if (s.getLast() <= -a[i])
                if (s.pollLast() < -a[i]) i--;
        }
        return s.stream().mapToInt(i->i).toArray();
    }
}