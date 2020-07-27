/*
202.
Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

Input: 11
Output: false
1^2 + 1^2 = 2
2^2 = 4 ------> notice that from here we are starting with 4
4^2 = 16
1^2 + 6^2 = 37
3^2 + 7^2 = 58
5^2 + 8^2 = 89
8^2 + 9^2 = 145
1^2 + 4^2 + 5^2 = 42
4^2 + 2^2 = 20
2^2 + 0^2 = 4 -------> notice that we just get back to 4 again
*/
// naive solution, set a proper threshold, usually the cycle wont last to long, not guranteed the correct answer but fast! 84%, 7%
class Solution {
    public boolean isHappy(int n) {
        int sum = 0;
        int num = n;
        int counter = 0;
        while(counter <= 20){
            counter++;
            sum = happyNumber(num);
            if(sum == 1)
                return true;
            if(sum == n)
                return false;
            else
                num = sum;
        }
        return false;
    }
    
    public int happyNumber(int n){
        int sum = 0;
        while(n != 0){
            int temp = n % 10;
            sum += temp*temp;
            n = n / 10;
        }
        return sum;
    }
}
//Floyd Cycle detection algorithm, 2 pointers, one move slower(1 each time) one move faster(2 each time). they must equal in a round if there exist a cycle. 84%, 6%
class Solution {
    public int findKthLargest(int[] nums, int k) {

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    
    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, hi);
        return i;
    }
    
    void swap(int[] A, int i, int j) {
	    int tmp = A[i];
	    A[i] = A[j];
	    A[j] = tmp;				
    }
}
//naive algorithm improve: use a hashset to record each result, if equal to a past value, return false. 22%, 5%
public boolean isHappy(int n) {
    Set<Integer> inLoop = new HashSet<Integer>();
    int squareSum,remain;
	while (inLoop.add(n)) {
		squareSum = 0;
		while (n > 0) {
		    remain = n%10;
			squareSum += remain*remain;
			n /= 10;
		}
		if (squareSum == 1)
			return true;
		else
			n = squareSum;

	}
	return false;
}