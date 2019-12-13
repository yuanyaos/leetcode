package leetcode47;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	private Set<ArrayList<Integer>> map = new HashSet<ArrayList<Integer>>();
	
	private void DSFsearch(List<List<Integer>> permlist, List<Integer> perm, int[] nums, int count, int[] index){
		if(count>=nums.length){
			if(!map.contains(perm)){
				permlist.add(new ArrayList<Integer>(perm));
				map.add(new ArrayList<Integer>(perm));
			}
				
			return;
		}
		
		for(int i=0;i<nums.length;i++){
			if(index[i]==0){
				index[i] = 1;
				perm.add(nums[i]);
				DSFsearch(permlist,perm,nums,count+1,index);
				perm.remove(perm.size()-1);
				index[i] = 0;
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
		
		int[] index = new int[nums.length];
		DSFsearch(permlist,perm,nums,0,index);
		
		return permlist;
    }
}
