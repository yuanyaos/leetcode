package leetcode36;

public class Solution {
	public boolean isValidSudoku(char[][] board) {
        
		// column-wise
		for(int i=0;i<9;i++){
			boolean[] column = new boolean[9];
			for(int j=0;j<9;j++){
				if(board[i][j]!='.'){					
					if(column[(int) (board[i][j]-'1')])
						return false;
					else
						column[(int) (board[i][j]-'1')] = true;
				}
			}
		}
		
		// row-wise
		for(int j=0;j<9;j++){
			boolean[] row = new boolean[9];
			for(int i=0;i<9;i++){
				if(board[i][j]!='.'){
					if(row[(int) (board[i][j]-'1')])
						return false;
					else
						row[(int) (board[i][j]-'1')] = true;
				}
			}
		}
		
		// block-wise
		for(int k=0;k<9;k++){
			boolean[] block = new boolean[9];
			int x=k/3;
			int y=k-3*x;
			for(int i=0;i<9;i++){
				int a=i/3;
				int b=i-3*a;
				if(board[3*x+a][3*y+b]!='.'){
					if(block[(int) (board[3*x+a][3*y+b]-'1')])
						return false;
					else
						block[(int) (board[3*x+a][3*y+b]-'1')] = true;
				}
			}
		}
		
		return true;
    }
}
