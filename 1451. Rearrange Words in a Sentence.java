/*
1451.
Given a sentence text (A sentence is a string of space-separated words) in the following format:

First letter is in upper case.
Each word in text are separated by a single space.
Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.

Return the new text following the format shown above.

Example 1:

Input: text = "Leetcode is cool"
Output: "Is cool leetcode"
Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
Output is ordered by length and the new first word starts with capital letter.
Example 2:

Input: text = "Keep calm and code on"
Output: "On and keep calm code"
Explanation: Output is ordered as follows:
"On" 2 letters.
"and" 3 letters.
"keep" 4 letters in case of tie order by position in original text.
"calm" 4 letters.
"code" 4 letters.
Example 3:

Input: text = "To be or not to be"
Output: "To be or to be not"
 
Constraints:

text begins with a capital letter and then contains lowercase letters and single space between words.
1 <= text.length <= 10^5
*/
// naive algo 13 5 
class Solution {
    public String arrangeWords(String text) {
        StringBuilder init = new StringBuilder(text);
        init.replace(0,1,Character.toString(Character.toLowerCase(init.charAt(0))));
        text = init.toString();
        String[] arr = text.split(" ");
        int max = 0;
        for(String s : arr){
            max = Math.max(max, s.length());
        }
        String[] res = new String[max+1];
        for(String s : arr){
            StringBuilder temp = new StringBuilder();
            if(res[s.length()] == null) 
                temp = new StringBuilder();
            else 
                temp.append(res[s.length()]);       
            temp.append(s);
            temp.append(" ");
            res[s.length()] = temp.toString();
        }
        StringBuilder r = new StringBuilder();
        for(String s : res){
            if(s == null || s.length() == 0) continue;
            r.append(s);
        }
        r.replace(0,1,Character.toString(Character.toUpperCase(r.charAt(0))));
        r.delete(r.length()-1, r.length());
        return r.toString();
    }
}
//better one Arrays.sort() will make sure the original order when tie 33.2  5
class Solution {
    public String arrangeWords(String text) {
        String []s = text.toLowerCase().split(" ");
        Arrays.sort(s, (a,b) ->  a.length() - b.length());
        String ans = String.join(" ", s);
        return Character.toUpperCase(ans.charAt(0)) + ans.substring(1);
    }
}

/*
To use ProorityQueue and make sure the order the same, using x.timeStamp - y.timeStamp by creating a new class
PriorityQueuePriorityQueue<Word> queue = new PriorityQueue<>((x,y) -> x.word.length() != y.word.length()?x.word.length() - y.word.length(): x.timeStamp - y.timeStamp);
*/ // 28 5
class Solution {
    public String arrangeWords(String text) {
        PriorityQueue<Word> queue = new PriorityQueue<>((x,y) -> x.word.length() != y.word.length()?x.word.length() - y.word.length(): x.timeStamp - y.timeStamp);
        String[] words = text.split(" ");
        int i = 0, n = words.length;
        for (String word: words) queue.offer(new Word(word.toLowerCase(), i++));
        StringBuffer result = new StringBuffer();
        for(i=0;!queue.isEmpty();i++) {
            result.append(queue.poll().word);
            if(i==0) result.setCharAt(0, Character.toUpperCase(result.charAt(0)));
            if(i!=n-1) result.append(" ");
        }
        return result.toString();
    }
}

class Word {
    String word;
    int timeStamp;
    public Word(String word, int timeStamp) {
        this.word = word;
        this.timeStamp = timeStamp;
    }
}