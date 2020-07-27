/*
28.
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
*/ 
//7.91， 49.62
class Solution {
    public int strStr(String haystack, String needle) {
        int hl = haystack.length();
        int nl = needle.length();
        if(haystack.equals(needle) && hl == 0)
            return 0;
        int count;
        int j;
        for(int i = 0; i < hl; i++){
            count = 0;
            j = i;
            while(j<hl && count<nl && haystack.charAt(j) == needle.charAt(count)){
                count++;
                j++;
            }
            if(count == nl)
                return i;
        }
        return -1;
    }
}
//re-write 32, 64
class Solution {
    public int strStr(String haystack, String needle) {
  for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}
}
/*KMP Algorithm: create a table on pattern. If duplicated, set the latter element value to the former element index.
Notes
Leetcode lists the difficulty as Easy for this question. This question is only easy if you solve it using a brute-force technique.
The below algorithm is the famous "KMP" linear-time solution algorithm. I would classify this question Hard as it may take 1 hour to understand this algorithm before coding.
Algorithm
We must first create a "Longest Prefix Suffix" array, where for each position in our "needle", we see if there's a prefix that matches a suffix
Video1 https://www.youtube.com/watch?v=V5-7GzOfADQ&feature=youtu.be&t=461 - watch from 7:41 to 11:58
*/
class Solution {
  private int[] computeLPS(String str) { // computes Longest Prefix Suffix (LPS) array
    int[] lps = new int[str.length()];
    lps[0] = 0;
    int i = 1; // always walks forward
    int j = 0; // tracks prefix that matches suffix

    while (i < str.length()) {
      if (str.charAt(i) == str.charAt(j)) {
        j++;
        lps[i] = j;
        i++;
      } else { // mismatch
        if (j == 0) { // go onto next character in string
          lps[i] = 0;
          i++;
        } else { // backtrack j to check previous matching prefix
          j = lps[j - 1];
        }
      }
    }
    return lps;
  }

  int strStr(String haystack, String needle) {
      if (haystack == null || needle == null || haystack.length() < needle.length()) {
          return -1;
      } else if (needle.isEmpty()) {
          return 0;
      }

      int[] lps = computeLPS(needle);
      int i = 0;
      int j = 0;

      while (i < haystack.length()) {
          if (needle.charAt(j) == haystack.charAt(i)) {
              i++;
              j++;
              if (j == needle.length()) {
                  return i - j; // match found. Return location of match
              }
          } else {
              if (j == 0) {
                  i++;
              } else {
                  j = lps[j - 1]; // backtrack j to check previous matching prefix
              }
          }
      }

      return -1; // did not find needle
  }
}