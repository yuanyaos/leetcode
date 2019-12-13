package leetcode48;

public class Solution {
	public void helper(int[][] matrix, int r, int c){
		int n = matrix.length;
		int temp1,temp2;
		temp1 = matrix[c][n-1-r];
		matrix[c][n-1-r] = matrix[r][c];	// 1st
		
		temp2 = matrix[n-1-r][n-1-c];
		matrix[n-1-r][n-1-c] = temp1;		// 2nd
		
		temp1 = matrix[n-1-c][r];
		matrix[n-1-c][r] = temp2;			// 3rd
		
		temp2 = matrix[r][c];
		matrix[r][c] = temp1;
		
		return;
	}
	
	public void rotate(int[][] matrix) {		
		int n = matrix.length;
		
		if(n==0 || n==1)
			return;
		
		int half;
		half = n/2;
		
		int bound0;
		if(n%2==0)
			bound0 = half;
		else
			bound0 = half+1;
		
		for(int i=0;i<bound0;i++){			
			for(int j=0;j<half;j++)
				helper(matrix,i,j);
		}
    }
}
