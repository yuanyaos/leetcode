package leetcode5;

public class Solution {
	public String longestPalindrome(String s) {
        if(s==null || s.length()<1)
        	return "";
        int len_odd, len_even, len;
        int start=0, end=0;
        for(int i=0; i<s.length(); i++){
        	len_odd = expandfromcenter(s,i,i);
        	len_even = expandfromcenter(s,i,i+1);
        	len = Math.max(len_odd, len_even);
        	if(len>end-start+1){
            	start = i-(len-1)/2;
            	end = i+len/2;
            }
        }
        return s.substring(start, end+1);	// returned substring length: end-start
    }
	
	public int expandfromcenter(String s, int start, int end){
		int pl=start, pr=end;
		while(pl>=0 && pr<s.length() && s.charAt(pl)==s.charAt(pr)){	// conditions must be ordered this way
			pl--;
			pr++;
		}
		return pr-pl-1;
	}
}
