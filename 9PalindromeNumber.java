/*
9.
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?
*/
//transfer to string/charArray, then let two side indices move to the mid
class Solution {
    public boolean isPalindrome(int x) {
        //int to string to char array 75.73, 52.87
		String str = Integer.toString(x);
        char[] chars = str.toCharArray();
		//int to char array 9.62, 5 
		//char[] chars = (""+x).toCharArray();
        int i = 0;
        int j = chars.length-1;
        while(i != j){
            
            if(i+1 == j && chars[i] == chars[j]){
                return true;
            }
            if(chars[i] == chars[j]){
                i++;
                j--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}