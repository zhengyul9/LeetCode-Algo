/*
289.
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/
//state machine naive algo 100%, 82.2%
class Solution {
    public void gameOfLife(int[][] board) {
        if(board.length == 0) return;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                int val = value(board[i][j]);
                int count = 0;
                if(i-1 >= 0){//top layer
                    count = value(board[i-1][j]) == 1 ? count+1 : count;
                    if(j-1 >= 0){
                        count = value(board[i-1][j-1]) == 1 ? count+1 : count;
                    }
                    if(j+1 < board[0].length){
                        count = value(board[i-1][j+1]) == 1 ? count+1 : count;
                    }
                }
                if(j-1 >= 0){//left
                    count = value(board[i][j-1]) == 1 ? count+1 : count;
                    
                }
                if(i+1 < board.length){//down layer
                    count = value(board[i+1][j]) == 1 ? count+1 : count;
                    if(j-1 >= 0){
                        count = value(board[i+1][j-1]) == 1 ? count+1 : count;
                    }
                    if(j+1 < board[0].length){
                        count = value(board[i+1][j+1]) == 1 ? count+1 : count;
                    }
                }
                if(j+1 < board[0].length){//right
                    count = value(board[i][j+1]) == 1 ? count+1 : count;
                }

                if(val == 0){//when cur cell is 0 (dead)
                    if(count == 3)
                        board[i][j] = -2;//dead to live
                }
                else{//when cur cell is 1 (live)
                    if(count < 2 || count > 3)
                        board[i][j] = -1;
                        
                }
            }
        }
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == -2) board[i][j] = 1;
                else if(board[i][j] == -1) board[i][j] = 0;
            }
        }
    }
    
    private int value(int n){
        if(n == -1)
            return 1;
        if(n == -2)
            return 0;
        return n;
        
    }
}