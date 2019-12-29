package leetcode79;

public class Solution {
	
	enum DIR{
		left,right,up,down;
	}
	
	private boolean DSF(char[][] board, int boardrow, int boardcol, String word, int indexcharAt) {
		
		if(indexcharAt==word.length())
			return true;
		if(boardrow<0 || boardrow>board.length-1 || boardcol<0 || boardcol>board[0].length-1)
			return false;
		if(board[boardrow][boardcol]!=word.charAt(indexcharAt)) {
			return false;
		}

		char temp = board[boardrow][boardcol];
		board[boardrow][boardcol] = '$';
		boolean val = (DSF(board,boardrow,boardcol-1,word,indexcharAt+1) ||	// from right to left
		DSF(board,boardrow,boardcol+1,word,indexcharAt+1) ||	// from left to right
		DSF(board,boardrow-1,boardcol,word,indexcharAt+1) ||	// from down to up
		DSF(board,boardrow+1,boardcol,word,indexcharAt+1));	// from up to down
		
		board[boardrow][boardcol] = temp;
		return val;
	}
	
	public boolean exist(char[][] board, String word) {
        int r = board.length;
        int c = board[0].length;
        for(int i=0;i<r;i++) {
        	for(int j=0;j<c;j++) {
        		if(DSF(board,i,j,word,0))
        			return true;
        	}
        }
        return false;
    }
}
