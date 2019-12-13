package leetcode50;

public class Solution {
	public double myPow(double x, int n){
		// convert decimal to binary
		
		 if(n==0)
			return 1;
		
		double result = 1;
		
		int ii=n;
		while(ii!=0){
			if(ii%2!=0)
				result *= x;
			x *= x;
			ii = ii/2;
		}
		
		if(n>0)
			return result;
		else
			return 1/result;
    }
}
