/*
848.
We have a string S of lowercase letters, and an integer array shifts.

Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a'). 

For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.

Return the final string after all such shifts to S are applied.

Example 1:

Input: S = "abc", shifts = [3,5,9]
Output: "rpl"
Explanation: 
We start with "abc".
After shifting the first 1 letters of S by 3, we have "dbc".
After shifting the first 2 letters of S by 5, we have "igc".
After shifting the first 3 letters of S by 9, we have "rpl", the answer.
Note:

1 <= S.length = shifts.length <= 20000
0 <= shifts[i] <= 10 ^ 9
*/
// 44 
class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        shifts[shifts.length-1] = shifts[shifts.length-1] % 26;
        for (int i = shifts.length - 2; i >= 0; i--)
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++){
            int num = S.charAt(i)-'a';
            if(num + shifts[i] >= 26){
                sb.append((char)('a' + num+shifts[i]-26));
            }
            else{
                sb.append((char)('a' + num+shifts[i]));
            }
        }
        return sb.toString();
    }
}
// 9ms 24 8 have to start from end to start and mod 26.
class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        StringBuilder res = new StringBuilder(S);
        for (int i = shifts.length - 2; i >= 0; i--)
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        for (int i = 0; i < S.length(); i++)
            res.setCharAt(i, (char)((S.charAt(i) - 'a' + shifts[i]) % 26 + 'a'));
        return res.toString();
    }
}