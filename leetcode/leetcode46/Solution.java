package leetcode46;

import java.util.*;

public class Solution {
	private Set<Integer> map = new HashSet<>();
	
	private void DSFsearch(List<List<Integer>> permlist, List<Integer> perm, int[] nums, int count){
		if(count>=nums.length){
			permlist.add(new ArrayList<Integer>(perm));
			return;
		}
		
		for(int i=0;i<nums.length;i++){
			if(!map.contains(nums[i])){
				map.add(nums[i]);
				perm.add(nums[i]);
				DSFsearch(permlist,perm,nums,count+1);
				perm.remove(perm.size()-1);
				map.remove(nums[i]);
			}
		}
		
		return;
	}
	
	public List<List<Integer>> permute(int[] nums) {
		
		List<List<Integer>> permlist = new ArrayList<>();
		List<Integer> perm = new ArrayList<>();
		
		if(nums.length==0)
			return permlist;
		
		if(nums.length==1){
			perm.add(nums[0]);
			permlist.add(perm);
			return permlist;
		}
		
		DSFsearch(permlist,perm,nums,0);
		
		return permlist;
    }
}
