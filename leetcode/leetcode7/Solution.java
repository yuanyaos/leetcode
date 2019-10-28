package leetcode7;

public class Solution {
	public int reverse(int x) {
        int revx=0;
        int last;
        while(x!=0){
        	last = x%10;
        	x = x/10;
        	// Cannot use revx*10>Integer.MAX_VALUE because revx*10 can already overflow 
        	if(revx>Integer.MAX_VALUE/10 || (revx==Integer.MAX_VALUE/10 && last>7))
        		return 0;
        	if(revx<Integer.MIN_VALUE/10 || (revx==Integer.MIN_VALUE/10 && last<-8))
        		return 0;
        	
        	revx = revx*10+last; 
        }
        return revx;
    }
}