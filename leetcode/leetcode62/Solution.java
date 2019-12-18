package leetcode65;

public class Solution {
	static int count = 0;
	
	public void searchPath(int curi, int curj, int m, int n){		
		if(curi==m-1 && curj==n-1){
			Solution.count++;
			return;
		}
		if(curi>=m || curj>=n){
			return;
		}
		
		searchPath(curi+1,curj,m,n);
		searchPath(curi,curj+1,m,n);
		
		return;
	}
	
	public int uniquePaths(int m, int n) {
        // m: width, n: depth
		searchPath(0,0,m,n);
		
		return count;
    }
}
