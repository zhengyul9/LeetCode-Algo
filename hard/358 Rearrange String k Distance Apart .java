/*
358.
There are N countries, each country has Ai players. You need to form teams of size K such that each player in the team is from a different country. Given N and number of players from each country and size K. Find the maximum number of teams you can form.
*/
//greedy keep selecting 1 people from top k most # of player countries. using priorityqueue, stack to temp store the selected countries.
public class Solution {
    public int maxNumberOfTeams(int[] countries, int k) {
        if (countries == null || countries.length == 0 || k <= 0) return 0;
        PriorityQueue<Integer> candidatePool = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int country : countries) {
            if (country <= 0) return -1;// Invalid input should be warned
            candidatePool.offer(country);
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        while (candidatePool.size() >= k) {
            for (int i = 0; i < k; i++) {
                int country = candidatePool.poll();
                if (--country > 0) stack.push(country);
            }
            while (!stack.empty()) candidatePool.offer(stack.pop());
            res++;
        }
        return res;
    }
}