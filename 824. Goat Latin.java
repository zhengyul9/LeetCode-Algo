/*
824.
A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 
Notes:

S contains only uppercase, lowercase and spaces. Exactly one space between each word.
1 <= S.length <= 150.
*/
// 70 naive algo
class Solution {
    public String toGoatLatin(String S) {
        String[] arr = S.split(" ");
        StringBuilder res = new StringBuilder();
        String last = "a";
        for(int i = 0; i < arr.length; i++){
            String each = arr[i];
            StringBuilder word = new StringBuilder(each);
            if(each.charAt(0) == 'a' || each.charAt(0) == 'e' || each.charAt(0) == 'i' || 
              each.charAt(0) == 'o' || each.charAt(0) == 'u' || each.charAt(0) == 'A' || 
              each.charAt(0) == 'E' ||each.charAt(0) == 'I' || each.charAt(0) == 'O' || 
              each.charAt(0) == 'U'){
                word.append("ma");
            }
            else{
                word.append(each.charAt(0));
                word.append("ma");
                word.delete(0,1);
            }
            word.append(last);
            last = last + "a";
            res.append(word);
            res.append(" ");
        }
        res.delete(res.length()-1, res.length());
        return res.toString();
    }
}