package leetcode3;

import java.util.*;

public class Solution {
	public int lengthOfLongestSubstring(String s) {
        int n = s.length(), len = 0;
        Map<Character,Integer> map=new HashMap<Character,Integer>();
        for(int i=0, j=0; j<n; j++){
        	if(map.containsKey(s.charAt(j))){
        		// must use max. e.g. 'akbkac'
        		i = Math.max(i,map.get(s.charAt(j))+1);
        	}
        	len = Math.max(len,j-i+1);
        	map.put(s.charAt(j),j);
        }
        return len;
    }
	
}
