/*
125.
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */
//remove other chars, then from mid to both sides O(n) 22%，23%
class Solution {
    public boolean isPalindrome(String s) {        
        if(s.length() == 0)
            return true;
        String str = s.replaceAll("\\W", "");
        str = str.toLowerCase();
        int n = str.length();
        if(n % 2 == 0){
            int symmetricIndex = n/2-1;
            for(int i = n/2; i < n; i++){
                if(str.charAt(i) != str.charAt(symmetricIndex))
                    return false;
                symmetricIndex--;
            }
        }
        else{
            int symmetricIndex = n/2-1;
            for(int i = n/2+1; i < n; i++){
                if(str.charAt(i) != str.charAt(symmetricIndex)){
                    return false;
                }
                symmetricIndex--;
            }
        }
        return true;
    }
}
//two pointers from both sides to mid stop when i >= j 98%, 23%
public class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
        	return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while(head <= tail) {
        	cHead = s.charAt(head);
        	cTail = s.charAt(tail);
        	if (!Character.isLetterOrDigit(cHead)) {
        		head++;
        	} else if(!Character.isLetterOrDigit(cTail)) {
        		tail--;
        	} else {
        		if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
        			return false;
        		}
        		head++;
        		tail--;
        	}
        }
        
        return true;
    }
}