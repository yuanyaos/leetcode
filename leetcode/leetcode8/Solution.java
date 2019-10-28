package leetcode8;

public class Solution {
	public int myAtoi(String str) {
        if(str==null || str.length()<1)
        	return 0;
        
        int i=0;
        while(i<str.length() && str.charAt(i)==' '){   
        	i++;
        }
        if(i>=str.length())
        	return 0;
        
        char sign = '+';
        if(str.charAt(i)=='+'){
        	sign = '+';
        	i++;
        }        	
        else if(str.charAt(i)=='-'){
        	sign = '-';
        	i++;
        }
        else if(str.charAt(i)<'0' || str.charAt(i)>'9'){
        	return 0;
        }
        
        int number=0, digit;
        while(i<str.length() && str.charAt(i)>='0' && str.charAt(i)<='9'){
        	digit = str.charAt(i)-'0';
        	
        	if(number>Integer.MAX_VALUE/10 || (number==Integer.MAX_VALUE/10 && digit>7) || -number<Integer.MIN_VALUE/10 || (-number==Integer.MIN_VALUE/10 && digit<-8)){
        		if(sign=='+')
        			return Integer.MAX_VALUE;
        		else
        			return Integer.MIN_VALUE;
        	}
        	number = number*10+digit;
        	i++;
        }
        if(sign=='+')
        	return number;
        else
        	return -number;
        
    }
}
