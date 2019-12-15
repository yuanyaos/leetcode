package leetcode55;

public class Solution {
	private boolean DSFsearch(int[] nums, int position){
		if(position==nums.length-1)
			return true;
		else if(position>=nums.length)
			return false;
		else if(nums[position]==0)
			return false;
		
		int maxnext = Math.min(position+nums[position],nums.length-1);
		maxnext = maxnext-position;
		for(int i=1;i<=maxnext;i++){
			if(DSFsearch(nums,position+i))
				return true;
		}
		return false;
	}
	
	public boolean canJump(int[] nums) {
        if(DSFsearch(nums,0))
        	return true;
        else
        	return false;
    }
}
