/*
1577.
Given a string s and an array of integers cost where cost[i] is the cost of deleting the character i in s.

Return the minimum cost of deletions such that there are no two identical letters next to each other.

Notice that you will delete the chosen characters at the same time, in other words, after deleting a character, the costs of deleting other characters will not change.

Example 1:

Input: s = "abaac", cost = [1,2,3,4,5]
Output: 3
Explanation: Delete the letter "a" with cost 3 to get "abac" (String without two identical letters next to each other).
Example 2:

Input: s = "abc", cost = [1,2,3]
Output: 0
Explanation: You don't need to delete any character because there are no identical letters next to each other.
Example 3:

Input: s = "aabaa", cost = [1,2,3,4,1]
Output: 2
Explanation: Delete the first and the last character, getting the string ("aba").
 
Constraints:

s.length == cost.length
1 <= s.length, cost.length <= 10^5
1 <= cost[i] <= 10^4
s contains only lowercase English letters.
*/
//using hashmap store t%n. 
class Solution {
    public int numTriplets(int[] a, int[] b) {
        return cnt(a, b) + cnt(b, a);
    }
    
    private int cnt(int[] a, int[] b) {
        int res = 0, m = a.length;
        for (int i = 0; i < m; i++) {
            Map<Long, Integer> map = new HashMap<>();
            long t = ((long) a[i]) * ((long) a[i]);
            for (long n : b) {  //must transferred to long
                if (t % n == 0) res += map.getOrDefault(t / n, 0); //new pair formed btw n and previous found t / n
                map.put(n, map.getOrDefault(n, 0) + 1); // cached the count found;
            }
        }
        return res;
    }
}
//another version
class Solution {
    public int numTriplets(int[] n1, int[] n2) {
		long res = 0;
		for (long n : n1)
			res += twoProduct(n * n, n2);
		for (long n : n2)
			res += twoProduct(n * n, n1);
		return (int)res;
	}
	long twoProduct(long i, int[] n) {
		Map<Long, Long> m = new HashMap<>();
		long cnt = 0;
		for (long v : n) {
			if (i % v == 0)
				cnt += m.getOrDefault(i / v, 0l);
			m.put(v, m.getOrDefault(v, 0l) + 1);
		}
		return cnt;
	}  
}
//sort then two pointers l r check each
class Solution {
    public int numTriplets(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        return cnt(a, b) + cnt(b, a);
    }
    
    private int cnt(int[] a, int[] b) {
        int res = 0, m = a.length, n = b.length;
        for (int i = 0; i < m; i++) {
            long t = ((long) a[i]) * ((long) a[i]);
            for (int l = 0, r = n - 1; l < n - 1; l++) { // finding pairs for i, l, r;
                if (((long) b[l]) * ((long) b[r]) < t) continue;
                while (r >= l && ((long) b[l]) * ((long) b[r]) > t) r--;
                if (r <= l) break;
                if (((long) b[l]) * ((long) b[r]) == t) {
                    int orig = r;
                    while(r > l && b[r] == b[orig]) {  // [1, 1], [1, 1, 1]
                        r--;
                        res++;
                    }
                    r = orig;  // reset to next cont;
                }
            }
        }
        return res;
    }
}