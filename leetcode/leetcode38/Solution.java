package leetcode38;

import java.util.*;

public class Solution {
	public String countAndSay(int n) {
		if(n==1)
			return "1";
		if(n==2)
			return "11";
		
		ArrayList<Integer> say = new ArrayList<Integer>();		
		say.add(1);
		say.add(1);
		
		for(int t=3;t<=n;t++){
			ArrayList<Integer> saynew = new ArrayList<Integer>();
			int i=0;
			while(i<say.size()){
				int count = 1;
				int p1 = count;
				int p2 = say.get(i);
				while(i+count<say.size() && say.get(i)==say.get(i+count)){
					count = count+1;
					p1 = count;
				}
				saynew.add(p1);
				saynew.add(p2);
//				if(i+1<say.size() && say.get(i)==say.get(i+1)){
//					int p1 = 2;
//					int p2 = say.get(i);
//					saynew.add(p1);
//					saynew.add(p2);
//					count = 2;
//				}else{
//					saynew.add(1);
//					saynew.add(say.get(i));
//					count = 1;
//				}
				i = i+count;
	        }
			// replace the old array with the new one
			say.clear();
			say.addAll(saynew);
		}
		
		String saystring = "";
		for(int i=0;i<say.size();i++){
			saystring = saystring+say.get(i).toString();
		}
		
		return saystring;
    }
}
