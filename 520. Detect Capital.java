/*
520.
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
 
Example 1:

Input: "USA"
Output: True

Example 2:

Input: "FlaG"
Output: False

Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
*/
//100 naive algo
class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.length() == 1) return true;
        int flag;
        if(Character.isUpperCase(word.charAt(0))){
            if(Character.isUpperCase(word.charAt(1)))
                flag = 1;
            else
                flag = 0;
        }
        else flag = 0;
        if(flag == 1){
            for(int i = 1; i < word.length(); i++){   
                if(Character.isLowerCase(word.charAt(i)))
                    return false;
            }
        }
        else{
            for(int i = 1; i < word.length(); i++){   
                if(Character.isUpperCase(word.charAt(i)))
                    return false;
            }
        }
        
        return true;
    }
}
// one line return using re
class Solution {
    public boolean detectCapitalUse(String word) {
		return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
	}
}
// one line using substring and equals stirng.toLower/uppercase()
class Solution {
    public boolean detectCapitalUse(String word) {
        return word.equals(word.toUpperCase()) || 
               word.equals(word.toLowerCase()) ||
               Character.isUpperCase(word.charAt(0)) && 
               word.substring(1).equals(word.substring(1).toLowerCase());
    }
}
