/*
60.
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
 */
//backtracking, too slow but method is general, 5, 9
class Solution {
    int count = 0;
    int flag = 0;
    
    public String getPermutation(int n, int k) {
        return backtracking(n, k, new StringBuilder()).toString();
    }
    
    public StringBuilder backtracking(int n, int k, StringBuilder each){
        if(each.length() == n){
            count++;
            if(count == k){
                flag = 1;
                return each;
            }
            return each;
        }
        for(int i = 1; i<=n; i++){
            if(each.toString().indexOf(Character.forDigit(i, 10)) != -1) continue;
            each.append(i);
            StringBuilder res = backtracking(n, k, each);
            if(flag == 1)
                return res;
            each.setLength(each.length() - 1);
        }
        return each;
    }
}
//math solution, 98.52, counting where should k be and find that specific one
class Solution {
    public String getPermutation(int n, int k) {       
        int pos = 0;
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();
    
        int sum = 1;
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }

        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        
        k--;
    
        for(int i = 1; i <= n; i++){
            int index = k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k-=index*factorial[n-i];
        }
    
        return String.valueOf(sb);
    }
}