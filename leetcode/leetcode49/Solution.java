package leetcode49;

import java.util.*; 

public class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        List<List<String>> result = new ArrayList<>();
        
        int ListListcount = 0;
        for(int i=0;i<strs.length;i++){
        	char tchar[] = strs[i].toCharArray();
        	Arrays.sort(tchar);
        	
        	String temp = new String(tchar);
        	if(!map.containsKey(temp)){
        		map.put(temp,ListListcount);
        		result.add(new ArrayList<String>());
        		result.get(ListListcount).add(strs[i]);
        		ListListcount = ListListcount+1;
        	}else{
        		result.get(map.get(temp)).add(strs[i]);
        	}
        }
        
        return result;
    }
}
