package leetcode78;

import java.util.*;

public class Solution {
	private void DSFsearch(int[] nums, int start, List<Integer> temp, List<List<Integer>> result){
		result.add(new ArrayList<>(temp));
		
		if(start>=nums.length)
			return;
		
		for(int i=start;i<nums.length;i++){
			temp.add(nums[i]);
			DSFsearch(nums,i+1,temp,result);
			temp.remove(temp.size()-1);
		}
		
		return;
	}
	
	public List<List<Integer>> subsets(int[] nums) {
		Arrays.sort(nums);
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        
        DSFsearch(nums,0,temp,result);
        
        return result;
    }
}
