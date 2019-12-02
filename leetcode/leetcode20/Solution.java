package leetcode20;

import java.util.*; 

public class Solution {
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		int a=0,b=0,c=0;
		for(int i=0;i<s.length();i++){
			char cur = s.charAt(i);
			if((cur==')' && a<=0) || (cur=='}' && b<=0) || (cur==']' && c<=0))
				return false;
			
			if(cur=='(' || cur=='{' || cur=='['){
				stack.push(cur);
				switch(cur){
				case '(':
					a=a+1;
					break;
				case '{':
					b=b+1;
					break;
				case '[':
					c=c+1;
					break;
				}
			}
			if(cur==')' || cur=='}' || cur==']'){
				char popval = stack.peek();
				if(cur==')' && popval==cur-1){
					stack.pop();
					a=a-1;
				}
				else if((cur=='}' || cur==']') && popval==cur-2){
					stack.pop();
					if(cur=='}')
						b=b-1;
					else
						c=c-1;
				}					
				else
					return false;
			}
		}
		if(stack.isEmpty())
			return true;
		else
			return false;
    }
}
