package leetcode65;

public class Solution {
	
	public int searchPath(int[][] memo, int m, int n){
		if(m==0 || n==0){
			memo[m][n] = 1;
			return 1;
		}
		
		if(memo[m][n]!=-1)
			return memo[m][n];
		
		memo[m][n] = searchPath(memo,m-1,n)+searchPath(memo,m,n-1);
		return memo[m][n];
	}
	
	public int uniquePaths(int m, int n) {
        // m: width, n: depth
		
		int[][] memo = new int[m][n];
		 
	    //init with -1 value
	    for(int i=0; i<m; i++){
	        for(int j=0; j<n; j++){
	        	memo[i][j]=-1;
	        }
	    }
	    
		return searchPath(memo,m-1,n-1);
    }
}
