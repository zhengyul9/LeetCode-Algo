/*
168.
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"

Input: 511757
Output: "ACBZY"
 */
// n-- first so that A is 'A'+0 and 'Z' is 'A'+25. Then %26 /26 to get answers 100%
 class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            n--;
            int temp = n%26;
            sb.insert(0,(char)('A' + temp));
            n = n/26;
        }
        return sb.toString();
    }
}
// insert complexity is O(N^2), append then reverse is Linear complexity
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;
            result.append((char)('A' + n % 26));
            n /= 26;
        }
        result.reverse();
        return result.toString();
    }
}