package leetcode39;

import java.util.*;

public class Solution {
	private void DFSsearch(List<List<Integer>> result, ArrayList<Integer> comb, int[] candidates, int sum, int target){
		if(sum==target){
			result.add(comb);
			return;
		}
		if(sum>target)
			return;
		
		for(int i=0;i<candidates.length;i++){
			comb.add(candidates[i]);
			sum = sum+candidates[i];
			DFSsearch(result, comb, candidates, sum, target);
			comb.remove(comb.size()-1);
			sum = sum-candidates[i];
		}
		return;
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> combination = new List<ArrayList<Integer>>();
		ArrayList<Integer> comb = new ArrayList<Integer>();
		int sum = 0;
		DFSsearch(combination,comb,candidates,sum,target);
		
		return combination;
    }
}
