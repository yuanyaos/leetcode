package leetcode127;

import java.util.*;

public class Solution {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> hashset = new HashSet<String>();
        for(String t:wordList)
        	hashset.add(t);
        
        if(!hashset.contains(endWord))
        	return 0;
        
        Queue<String> q = new LinkedList<String>();
        q.offer(beginWord);
        int level = 1;
        while(!q.isEmpty()){
        	level++;
        	for(int count=q.size();count>0;--count){
	        	String cur = q.poll();
	        	char[] cha = cur.toCharArray();
	        	for(int i=0;i<cha.length;i++){
	        		char store = cha[i];
	        		for(int j=0;j<26;j++){
	        			char t = (char) ('a'+j);
	        			if(t==store)
	        				continue;
	        			cha[i] = t;
	        			String newstring = String.copyValueOf(cha);
	        			if(newstring.equals(endWord))
	        				return level;
	        			if(!hashset.contains(newstring))
	        				continue;
	        			q.offer(newstring);
	        			hashset.remove(newstring);
	        		}
	        		cha[i] = store;
	        	}
        	}
        }
        return 0;
    }
}
