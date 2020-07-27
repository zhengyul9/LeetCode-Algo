/*
1044.
Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)

 

Example 1:

Input: "banana"
Output: "ana"
Example 2:

Input: "abcd"
Output: ""
 

Note:

2 <= S.length <= 10^5
S consists of lowercase English letters.
*/
//Suffix tree solution 97.2
class Solution {
    String s;
    Node root = new Node(-1, new int[]{-1});
    ActivePoint activePoint = new ActivePoint(root, 0);
    int remainingNumber = 0, prefixCount = 0;
    int[] leafEnd = new int[]{-1}, lcp = new int[]{-1, -1};

    class ActivePoint {
        Node activeNode;
        int activeLength;
        int activeCharIndex;

        public ActivePoint(Node node, int length) {
            this.activeNode = node;
            activeLength = length;
        }

        public boolean walkDown() {
            Node child = activeNode.children.get(s.charAt(activeCharIndex));
            if (activeLength >= child.edgeLength()) {
                activeCharIndex += child.edgeLength();
                activeLength -= child.edgeLength();
                activeNode = child;
                return true;
            }
            return false;
        }
    }

    class Node {
        int start;
        int[] end;
        Map<Character, Node> children;
        Node suffixLink;

        public Node(int start, int[] end) {
            this.start = start;
            this.end = end;
            children = new HashMap<>();
        }

        public int edgeLength() {
            return end[0] - start;
        }
    }

    private void phase(int i) {
        Node lastNewNode = null;
        char sChar = s.charAt(i);
        leafEnd[0] = i + 1;
        remainingNumber++;
        // loop through extensions
        while (remainingNumber > 0) {
            if (activePoint.activeLength == 0) {
                activePoint.activeCharIndex = i;
            }
            char activeChar = s.charAt(activePoint.activeCharIndex);
            if (activePoint.activeNode.children.containsKey(activeChar)) {
                if (activePoint.walkDown()) {
                    continue;
                }

                Node next = activePoint.activeNode.children.get(activeChar);
                int nextCurIndex = next.start + activePoint.activeLength;
                char nextChar = s.charAt(nextCurIndex);

                //Rule 3
                if (nextChar == sChar) {
                    activePoint.activeLength++;
                    prefixCount++;
                    if (lcp[0] == -1 || lcp[1] - lcp[0] < prefixCount) {
                        lcp[0] = nextCurIndex + 1 - prefixCount;
                        lcp[1] = nextCurIndex + 1;
                    }
                    // set the suffix link
                    if (lastNewNode != null) {
                        lastNewNode.suffixLink = activePoint.activeNode;
                    }
                    break;
                }

                //Rule 2
                //// create the internal node
                Node internal = new Node(next.start, new int[]{nextCurIndex});
                activePoint.activeNode.children.put(activeChar, internal);
                if (lcp[0] == -1 || lcp[1] - lcp[0] < prefixCount) {
                    lcp[0] = internal.end[0] - prefixCount;
                    lcp[1] = internal.end[0];
                }

                //// update the next node and put as the internal node children
                next.start = nextCurIndex;
                internal.children.put(nextChar, next);

                //// create the leaf node
                Node leaf = new Node(i, leafEnd);
                internal.children.put(sChar, leaf);

                // set the suffix link
                if (lastNewNode != null) {
                    lastNewNode.suffixLink = internal;
                }
                lastNewNode = internal;
            } else {
                // Rule 2
                Node leaf = new Node(i, leafEnd);
                activePoint.activeNode.children.put(activeChar, leaf);
                // set the suffix link
                if (lastNewNode != null) {
                    lastNewNode.suffixLink = activePoint.activeNode;
                    lastNewNode = null;
                }
            }

            remainingNumber--;
            // update the activePoint
            if (activePoint.activeNode == root && activePoint.activeLength > 0) {
                activePoint.activeCharIndex = i - remainingNumber + 1;
                activePoint.activeLength--;
            } else if (activePoint.activeNode != root) {
                activePoint.activeNode = activePoint.activeNode.suffixLink;
            }

            if (prefixCount > 0) {
                prefixCount--;
            }
        }
    }

    public void buildSuffixTree() {
        for (int i = 0; i < s.length(); i++) {
            phase(i);
        }
    }
    
    public String longestDupSubstring(String S) {
        s = S;
        buildSuffixTree();
        return lcp[0] == -1 ? "" : s.substring(lcp[0], lcp[1]);
    }
}
//binary search + rabin carp algo(binary tree for length, then check if there is a duplicated using hashmap)
class Solution {
    private static final long q = (1 << 31) - 1;
    private static final long R = 256;
        
    public String longestDupSubstring(String S) {      
        int left = 2;
        int right = S.length() - 1;
        int start = 0;
        int maxLen = 0;
        
        while (left <= right) {
            int len = left + (right - left) / 2;
            boolean found = false;
            
            Map<Long, List<Integer>> map = new HashMap<>();
            long hash = hash(S, len);
            map.put(hash, new ArrayList<>());
            map.get(hash).add(0);
            long RM = 1l;
            for (int i = 1; i < len; i++) RM = (R * RM) % q;
            
            loop:
            for (int i = 1; i + len <= S.length(); i++) {
                hash = (hash + q - RM * S.charAt(i - 1) % q) % q;
                hash = (hash * R + S.charAt(i + len - 1)) % q;
                if (!map.containsKey(hash)) {
                    map.put(hash, new ArrayList<>());
                } else {
                    for (int j : map.get(hash)) {
                        if (compare(S, i, j, len)) {
                            found = true;
                            start = i;
                            maxLen = len;
                            break loop;
                        }
                    }
                }
                map.get(hash).add(i);
            }
            if (found) left = len + 1;
            else right = len - 1;
        }
        
        return S.substring(start, start + maxLen);
    }
    
    private long hash(String S, int len) { 
        long h = 0;
        for (int j = 0; j < len; j++) h = (R * h + S.charAt(j)) % q;
        return h;
    }
    
    private boolean compare(String S, int i, int j, int len) {
        for (int count = 0; count < len; count++) {
            if (S.charAt(i++) != S.charAt(j++)) return false ; 
        }
        return true ; 
    }
}