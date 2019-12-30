package leetcode91;

public class Solution {
	private void DSF(String s, int start, int[] count, int[] dp) {
		
		if(start==s.length()-1) {
			dp[start] = 1;
//			count[0] = count[0]+1;
			return;
		}else if(start==s.length()-2) {
			String ss = s.substring(start);
			int t = Integer.parseInt(ss);
			if(t>0 && t<=26)
				dp[start] = 2;
			else
				dp[start] = 1;
			return;
		}
		
		if(dp[start]!=0) {
			count[0] = count[0]+1;
			return;
		}
		
		String s1 = s.substring(start, start+1);
		int t1 = Integer.parseInt(s1);
		if(t1>0 && t1<=26)
			DSF(s,start+1,count,dp);
		else
			return;
		
		if(start<=s.length()-2) {
			String s2 = s.substring(start, start+2);
			int t2 = Integer.parseInt(s2);
			if(t2>0 && t2<=26)
				DSF(s,start+2,count,dp);
		}
		
		return;
	}
	
	public int numDecodings(String s) {
        int[] count = new int[] {0};
        int[] dp = new int[s.length()];
        DSF(s,0,count,dp);
        
        return count[0];
    }
}
