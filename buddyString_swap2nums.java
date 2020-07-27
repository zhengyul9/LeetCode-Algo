/*
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
Example 6:

Input: A = "abcd", B = "badc"
Output: false
*/
class Solution {
    public boolean buddyStrings(String A, String B) {
        if(A.length() != B.length() || A.equals("") || B.equals("")){
            return false;
        }
        char[] arr1 = A.toCharArray();
        char[] arr2 = B.toCharArray();
        char tempa = 0;
        char tempb = 0;
        int flag = 0;
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] != arr2[i]){
                if(flag == -1)
                    return false;
                if(flag == 0){
                    tempa = arr1[i];
                    tempb = arr2[i];
                    flag = 1;
                }
                else{
                    if(arr1[i] == (tempb) && arr2[i]==(tempa))
                        flag = -1;
                    else
                        return false;
                }
            }             
        }
        int flag2 = 0;
        if(flag == 0 || flag == -1){
            for(int i = 0; i<arr1.length-1; i++){
                for(int j = i+1; j<arr1.length; j++){
                    if(arr1[i] == arr2[j] && arr1[j] == arr2[i])
                        return true;
                    else flag2 = 1;
                }
            }
            if(flag2 == 1)
                return false;
        }
        if(flag == 0 || flag == -1)
            return true;
        else 
            return false;
    }
}