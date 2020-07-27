/*
58.
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word (last word means the last appearing word if we loop from left to right) in the string.

If the last word does not exist, return 0.

Note: A word is defined as a maximal substring consisting of non-space characters only.

Example:

Input: "Hello World"
Output: 5
 */
//100%, 7.57%
class Solution {
    public int lengthOfLastWord(String s) {
        char[] arr = s.toCharArray();
        int count = 0;
        int flag = 0;
        for(int i = arr.length-1; i>=0; i--){
            if(arr[i] != ' '){
                count++;
                flag = 1;
            }
            else if(arr[i] == ' ' && flag == 0)
                continue;
            else if(arr[i] == ' ')
                return count;
        }
        return count;
    }
}