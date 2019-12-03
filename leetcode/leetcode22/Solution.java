package leetcode22;

import java.util.*; 

public class Solution {
	public List<String> generateParenthesis(int n) {
		List<String> head = new ArrayList<String>();
		DSFtracking(head,"",0,0,n);		
		return head;
    }
	
	public void DSFtracking(List<String> tree, String current, int leftnum, int rightnum, int max){
		if(leftnum<max){
			DSFtracking(tree,current+"(",leftnum+1,rightnum,max);
		}
		if(rightnum<leftnum){
			DSFtracking(tree,current+")",leftnum,rightnum+1,max);
		}
		
		if(leftnum==max && rightnum==max){
			tree.add(current);
		}
	}
}
