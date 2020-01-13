package leetcode1163;

import java.util.*;

public class Solution {
	private class node{
		int start;
		int index;
		public node(int a, int b){
			this.start = a;
			this.index = b;
		}
	}
	public String lastSubstring(String s) {
		int count;
		for(count=0;count<s.length();count++)
			if(s.charAt(count)!=s.charAt(0))
				break;
		if(count==s.length())
			return s;
		
		char max = 'a';
		for(int i=0;i<s.length();i++)
			if(s.charAt(i)>max)
				max = s.charAt(i);
		
		List<node> list = new LinkedList<node>();
		for(int i=0;i<s.length();i++)
			if(s.charAt(i)==max)
				list.add(new node(i,i));
		if(list.size()==1)
			return s.substring(list.get(0).start);
		
		while(true){
			max = 'a';
			for(node n:list){
				n.index++;
				if(n.index<s.length() && s.charAt(n.index)>max)
					max = s.charAt(n.index);
			}
			
			List<node> newlist = new LinkedList<node>();
			for(node n:list)
				if(n.index<s.length() && s.charAt(n.index)==max)
					newlist.add(n);
			
			if(newlist.size()==1)
				return s.substring(newlist.get(0).start);
			
			list = newlist;
		}
	}
}
