/*
739.
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/
//using stack save index 82.42 74.54
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }
}
//using array implement stack 100%, 100%
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            stack[++top] = i;
        }
        return ret;
    }
}
//python using stack 100% 73%
class Solution:
    def dailyTemperatures(self, T: List[int]) -> List[int]:
        res = [0] * len(T)
        stack = []
        for i in range(len(T)):
            while stack and T[i] > T[stack[-1]]:
                temp = stack.pop()
                res[temp] = i - temp
            stack.append(i)
        return res
// naive algo O(n^2) 11, 60
class Solution {
    public int[] dailyTemperatures(int[] T) {
        if(T.length == 0) return new int[0];
        int[] res = new int[T.length];
        for(int i = 0; i < res.length-1; i++){
            int day = 1;
            int j = i + 1;
            for(; j < T.length; j++){
                if(T[i] >= T[j])
                    day++;
                else
                    break;
            }
            if(j == T.length && day != 0)
                day = 0;
            res[i] = day;
        }
        res[res.length-1] = 0;
        return res;
    }
}