package leetcode29;

public class Solution {
	public int divide(int dividend, int divisor) {
		if(divisor==0)
			return Integer.MAX_VALUE;
		if(divisor==-1 && dividend == Integer.MIN_VALUE)
	        return Integer.MAX_VALUE;
		
		long dividend_abs = Math.abs((long)dividend);
		long divisor_abs = Math.abs((long)divisor);
		
		long result = 0;
		long resultt = 0;
		long mul;
		while(dividend_abs>=divisor_abs){
			
			int pow = 0;
			mul = divisor_abs<<pow;
			while(dividend_abs>=mul){
				pow = pow+1;
				mul = divisor_abs<<pow;
			}
			mul = divisor_abs<<(pow-1);
			resultt = 1<<(pow-1);
			result = result+resultt;	// Notice here
			dividend_abs = dividend_abs-mul;
		}
		
		if((dividend>0 && divisor>0) || (dividend<0 && divisor<0))
			return (int)result;
		else
			return (int)-result;
    }
}
