/*
204.
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
// naive solution bottom up, 36.7, 5.66
class Solution {
    public int countPrimes(int n) {
        boolean[] arr = new boolean[n];
        int count = 0;
        for(int i = 2; i < n; i++){
            if(arr[i] == false){
                count++;
                for(int j = 2; i*j < n; j++){
                    arr[i*j] = true;
                }   
            }
        }
        return count;
    }
}
// get rid of even number, and number can be mupltiplied by prime odd numbers.
class Solution {
    public int countPrimes(int n) {
        if (n < 3)
            return 0;
        
        boolean[] f = new boolean[n];
        //Arrays.fill(f, true); boolean[] are initialed as false by default
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i])
                continue;
        
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
    return count;
    }
}