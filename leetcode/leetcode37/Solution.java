package leetcode37;

public class Solution {
	private boolean isValid(char[][] board, char s, int i, int j){
		for(int c=0;c<9;c++){
			if(board[i][c]==s)
				return false;
			if(board[c][j]==s)
				return false;
			if(board[3*(i/3)+c/3][3*(j/3)+c%3]==s)
				return false;
		}
		return true;
	}
	
	private boolean solver(char[][] board){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(board[i][j]=='.'){
					for(char s='1';s<='9';s++){
						if(isValid(board,s,i,j)){
							board[i][j] = s;
							if(solver(board))
								return true;
							else
								board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	public void solveSudoku(char[][] board) {
		if(board==null || board.length==0)
			return;
		solver(board);
    }
}
