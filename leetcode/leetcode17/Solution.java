package leetcode17;

import java.util.*;
//import java.util.ArrayList;

public class Solution {
	
	Map<String, String> hashmap = new HashMap<String, String>(){{
		put("2", "abc");
		put("3", "def"); 
		put("4", "ghi");
		put("5", "jkl");
		put("6", "mno");
		put("7", "pqrs");
		put("8", "tuv");
		put("9", "wxyz");
    }};
    
    List<String> stringout = new ArrayList<String>();
	
//    System.out.println("Values " + hm);
    
    public void getAllCombinations(String comb, String next_digit){
    	if(next_digit.length()==0){
    		stringout.add(comb);
    	}
    	else{
    		String num = next_digit.substring(0,1);
    		String letters = hashmap.get(num);
    		for(int i=0;i<letters.length();i++){
    			String letter = letters.substring(i,i+1);
    			getAllCombinations(comb+letter,next_digit.substring(1));
    		}
    	}
    }
    
	public List<String> letterCombinations(String digits) {
        if(digits.length()>0)
        	getAllCombinations("",digits);
        return stringout;
    }
}
