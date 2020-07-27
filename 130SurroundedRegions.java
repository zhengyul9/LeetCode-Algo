/*
130.
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
//BFS stack/recursion method 7%, 50%
class Solution {
    int mark;
    public void solve(char[][] board) {
        if(board.length != 0){
        int[][] helparr = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'O'){
                    mark = 0;
                    if(helparr[i][j] == 1)
                        continue;
                    Stack<Integer> x = new Stack<Integer>();
                    Stack<Integer> y = new Stack<Integer>();
                    //bfs
                    BFS(board, i, j, x, y, helparr);
                    if(mark == 0){
                        while(!x.isEmpty()){
                            board[x.pop()][y.pop()] = 'X';
                        }
                    }
                    // else{
                    //     while(!x.isEmpty()){
                    //         helparr[x.pop()][y.pop()] = 1;
                    //     }
                    // }
                }
            }
        }
        }
    }
    
    public void BFS(char[][] board, int i, int j, Stack<Integer> x, Stack<Integer> y, int[][] helparr){
        x.push(i);
        y.push(j);
        Stack<Integer> x1 = new Stack<Integer>();
        Stack<Integer> y1 = new Stack<Integer>();
        x1.push(i);
        y1.push(j);
        while(!x1.isEmpty()){
            i = x1.pop();
            j = y1.pop();
            if(helparr[i][j] == 1)
                continue;
            helparr[i][j] = 1;

        //top        
        if(i-1 >= 0){
            if(helparr[i-1][j] != 1){
                if(board[i-1][j] == 'O'){
                    x1.push(i-1);
                    y1.push(j);
                    x.push(i-1);
                    y.push(j);
                }
            }
        }
        else{
            mark = 1;
        }
        //down
        if(i+1 < board.length){ 
            if(helparr[i+1][j] != 1){
            if(board[i+1][j] == 'O'){
                x1.push(i+1);
                y1.push(j);
                x.push(i+1);
                y.push(j);
            }
            }
        }
        else{
            mark = 1;
        }
        //left
        if(j-1 >= 0 ){
            if(helparr[i][j-1] != 1){
            if(board[i][j-1] == 'O'){
                x1.push(i);
                y1.push(j-1);
                x.push(i);
                y.push(j-1);
            }
            }
        }
        else{
            mark = 1;
        }
        //right
        if(j+1 < board[0].length){
            if(helparr[i][j+1] != 1){
            if(board[i][j+1] == 'O'){
                x1.push(i);
                y1.push(j+1);
                x.push(i);
                y.push(j+1);
            }
            }
        }
        else{
            mark = 1;
        }
        }
    }
}
// simliar faster DFS 99%, 50%
class Solution {
    public void solve(char[][] board) {
	if (board.length == 0 || board[0].length == 0)
		return;
	if (board.length < 2 || board[0].length < 2)
		return;
	int m = board.length, n = board[0].length;
	//Any 'O' connected to a boundary can't be turned to 'X', so ...
	//Start from first and last column, turn 'O' to '*'.
	for (int i = 0; i < m; i++) {
		if (board[i][0] == 'O')
			boundaryDFS(board, i, 0);
		if (board[i][n-1] == 'O')
			boundaryDFS(board, i, n-1);	
	}
	//Start from first and last row, turn '0' to '*'
	for (int j = 0; j < n; j++) {
		if (board[0][j] == 'O')
			boundaryDFS(board, 0, j);
		if (board[m-1][j] == 'O')
			boundaryDFS(board, m-1, j);	
	}
	//post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (board[i][j] == 'O')
				board[i][j] = 'X';
			else if (board[i][j] == '*')
				board[i][j] = 'O';
		}
	}
}
//Use DFS algo to turn internal however boundary-connected 'O' to '*';
private void boundaryDFS(char[][] board, int i, int j) {
	if (i < 0 || i > board.length - 1 || j <0 || j > board[0].length - 1)
		return;
	if (board[i][j] == 'O')
		board[i][j] = '*';
	if (i > 1 && board[i-1][j] == 'O')
		boundaryDFS(board, i-1, j);
	if (i < board.length - 2 && board[i+1][j] == 'O')
		boundaryDFS(board, i+1, j);
	if (j > 1 && board[i][j-1] == 'O')
		boundaryDFS(board, i, j-1);
	if (j < board[i].length - 2 && board[i][j+1] == 'O' )
		boundaryDFS(board, i, j+1);
}
}
// Union found method 22.88%, 71%
class Solution {   
        int[] unionSet; // union find set
        boolean[] hasEdgeO; // whether an union has an 'O' which is on the edge of the matrix
        
        public void solve(char[][] board) {
            if(board.length == 0 || board[0].length == 0) return;
            
            // init, every char itself is an union
            int height = board.length, width = board[0].length;
            unionSet = new int[height * width];
            hasEdgeO = new boolean[unionSet.length];
            for(int i = 0;i<unionSet.length; i++) unionSet[i] = i;
            for(int i = 0;i<hasEdgeO.length; i++){
                int x = i / width, y = i % width;
                hasEdgeO[i] = (board[x][y] == 'O' && (x==0 || x==height-1 || y==0 || y==width-1));
            }
            
            // iterate the matrix, for each char, union it + its upper char + its right char if they equals to each other
            for(int i = 0;i<unionSet.length; i++){
                int x = i / width, y = i % width, up = x - 1, right = y + 1;
                if(up >= 0 && board[x][y] == board[up][y]) union(i,i-width);
                if(right < width && board[x][y] == board[x][right]) union(i,i+1);
            }
            
            // for each char in the matrix, if it is an 'O' and its union doesn't has an 'edge O', the whole union should be setted as 'X'
            for(int i = 0;i<unionSet.length; i++){
                int x = i / width, y = i % width;
                if(board[x][y] == 'O' && !hasEdgeO[findSet(i)]) 
                    board[x][y] = 'X'; 
            }
        }
        
        private void union(int x,int y){
            int rootX = findSet(x);
            int rootY = findSet(y);
            // if there is an union has an 'edge O',the union after merge should be marked too
            boolean hasEdgeO = this.hasEdgeO[rootX] || this.hasEdgeO[rootY];
            unionSet[rootX] = rootY;
            this.hasEdgeO[rootY] = hasEdgeO;
        }
        
        private int findSet(int x){
            if(unionSet[x] == x) return x;
            unionSet[x] = findSet(unionSet[x]);
            return unionSet[x];
        }
}