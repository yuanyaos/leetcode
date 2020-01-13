import java.util.*;

public class Solution {
	private int gcd(int a, int b, int[][] dp) {
		if(dp[a][b]!=0 || dp[b][a]!=0)
			return dp[a][b];
		
		if(b==0) {
			return a;
		}
	    dp[a][b] = gcd(b,a%b,dp);
	    dp[b][a] = dp[a][b];
	    return dp[a][b];
	}

	public int[] TravelisFun(int n, int g, int[] oriCities, int[] desCities) {
		int q = oriCities.length;
		int[][] dp = new int[n+1][n+1];
		int[][] connect = new int[n+1][n+1];
		int[][] reachable = new int [n+1][n+1];
		int[] result = new int[q];
		
//		connectivity matrix
		for(int i=1;i<=n;i++) {
			for(int j=i+1;j<=n;j++) {
				dp[i][j] = gcd(i,j,dp);
				dp[j][i] = dp[i][j];
				if(dp[i][j]>g) {
					connect[i][j] = 1;
					connect[j][i] = 1;
				}
			}
		}
		
//		BSF traverse
		for(int i=0;i<q;i++) {
			int[] mark = new int[n+1];
			int ori = oriCities[i];
			int des = desCities[i];
			mark[ori] = 1;
			
			Queue<Integer> que = new LinkedList<>();
			que.add(ori);
			while(!que.isEmpty()) {
				int cur = que.poll();
				for(int j=1;j<=n;j++) {
					if(connect[cur][j]>0 && mark[j]==0) {
						que.add(j);
						mark[j] = 1;
						if(j==des)
							result[i] = 1;
					}
				}
			}
		}
		return result;
	}
}
