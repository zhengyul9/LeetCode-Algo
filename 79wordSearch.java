/*
79.
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
Constraints:
board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
 */
// 88 56 DFS without bitwise mark and extra space
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
    	    for (int j = 0; j < board[0].length; j++) {
    		    if (dfs(board, i, j, w, 0)) return true;
    	    }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, int i, int j, char[] w, int k){
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != w[k]) return false;
        if(k == w.length-1) return true;
        char temp = board[i][j];
        board[i][j] = '1';
        if(dfs(board, i-1, j, w, k+1)) return true;
        if(dfs(board, i+1, j, w, k+1)) return true;
        if(dfs(board, i, j-1, w, k+1)) return true;
        if(dfs(board, i, j+1, w, k+1)) return true;
        board[i][j] = temp;
        return false;
    }
}


//98.48, 28.57. DFS with bitwise mark traversed element
class Solution {
    public boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int y=0; y<board.length; y++) {
			for (int x=0; x<board[y].length; x++) {
				if (exist(board, y, x, w, 0)) return true;
			}
		}
		return false;
	}

	private boolean exist(char[][] board, int y, int x, char[] word, int i) {
		if (i == word.length) return true;
		if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
		if (board[y][x] != word[i]) return false;
		board[y][x] ^= 256;
		boolean exist = exist(board, y, x+1, word, i+1)
			|| exist(board, y, x-1, word, i+1)
			|| exist(board, y+1, x, word, i+1)
			|| exist(board, y-1, x, word, i+1);
		board[y][x] ^= 256;
		return exist;
	}
}


//recursive DFS: 79.71, 26.53
class Solution {
    static boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];    
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)){
                    return true;
                }
            }
        }      
        return false;
    }
   private boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }  
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }        
        visited[i][j] = true;
        if(search(board, word, i-1, j, index+1) || 
           search(board, word, i+1, j, index+1) ||
           search(board, word, i, j-1, index+1) || 
           search(board, word, i, j+1, index+1)){
            return true;
        }        
        visited[i][j] = false;
        return false;
    }
}
