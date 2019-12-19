package leetcode69;

public class Solution {	
	public int mySqrt(int x) {
		if(x==0 || x==1)
			return x;
		
        // binary search
		int l = 0;
		int r = x;
		int m;
		while(r>l+1){
			m = l+(r-l)/2;
			if(m*m==x)
				return m;
			if(m>x/m)
				r = m;
			else
				l = m;			
		}
		
		return (l+r)/2;
    }
}
