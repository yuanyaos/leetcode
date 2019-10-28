package leetcode6;

public class Solution {
	public String convert(String s, int numRows) {
		if(numRows==1)
			return s;
		StringBuilder str=new StringBuilder();
		
		int cycle=2*numRows-2;
		int n=s.length();
		for(int r=0;r<numRows;r++){
			for(int j=0;j+r<n;j+=cycle){
				str.append(s.charAt(j+r));
				if(r!=0 && r!=numRows-1 && j+cycle-r<n){
					str.append(s.charAt(j+cycle-r));
				}
			}
			
		}
		return str.toString();
    }
}
