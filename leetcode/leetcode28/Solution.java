package leetcode28;

public class Solution {
	public int strStr(String haystack, String needle) {
		if(haystack==null || needle==null)
			return 0;
		if(haystack.length()<needle.length())
			return -1;
		
		int i,j;
        for(i=0;i<=haystack.length()-needle.length();i++){
        	for(j=0;j<needle.length();j++){
        		if(haystack.charAt(i+j)!=needle.charAt(j))
        			break;
        	}
        	if(j==needle.length())
        		return i;
        }
        return -1;
    }
}