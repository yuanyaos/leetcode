package leetcode29;

public class Solution {
	public int divide(int dividend, int divisor) {
		if(divisor==0)
			return Integer.MAX_VALUE;
		
		long dividend_abs = Math.abs(dividend);
		long divisor_abs = Math.abs(divisor);
		
		long result = 0;
		while(dividend_abs>divisor_abs){
			
			int pow = 0;
			long mul = divisor_abs<<pow;
			while(dividend_abs>mul){
				pow = pow+1;
				mul = divisor_abs<<pow;
			}
			mul = 1<<(pow-1);	// Notice here
			divisor_abs = divisor_abs-mul;
			
			result = result+mul;
		}
		
		if((dividend>0 && divisor>0) || (dividend<0 && divisor<0))
			return (int)result;
		else
			return (int)-result;
    }
}
