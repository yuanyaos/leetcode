package leetcode40;

import java.util.*;
//import java.util.stream.IntStream; 

public class Solution {
	
	private void DFSsearch(List<List<Integer>> result, List<Integer> comb, int[] candidates, int sum, int target, int start){ // add start index to avoid repeated list
		if(sum>target)
			return;
		
		if(sum==target){
			result.add(new ArrayList<>(comb));
			return;
		}
		
		int prev = -1;
		for(int i=start;i<candidates.length;i++){
			if(prev!=candidates[i]){
				comb.add(candidates[i]);
				sum = sum+candidates[i];
				DFSsearch(result, comb, candidates, sum, target,i+1);
				comb.remove(comb.size()-1);
				sum = sum-candidates[i];
				prev = candidates[i];
			}
		}
		return;
	}
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> combination = new ArrayList<>();
		List<Integer> comb = new ArrayList<>();
		int sum = 0;
		DFSsearch(combination,comb,candidates,sum,target,0);
		
		return combination;
    }
}
