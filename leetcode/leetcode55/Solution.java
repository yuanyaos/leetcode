package leetcode55;



public class Solution {
	enum flag{
		UNKNOW, GOOD, BAD;
	}
	
	private boolean DSFsearch(int[] nums, int position, flag[] memo){
		if(memo[position]!=flag.UNKNOW){
			if(memo[position]==flag.GOOD)
				return true;
			else
				return false;
		}
		
		// set the boundary for memorization
		int maxnext = Math.min(position+nums[position],nums.length-1);
		maxnext = maxnext-position;
		for(int i=1;i<=maxnext;i++){
			if(DSFsearch(nums,position+i,memo)){
				memo[position] = flag.GOOD;
				return true;
			}
		}
		
		memo[position] = flag.BAD;
		return false;
	}
	
	public boolean canJump(int[] nums) {
		flag[] memo = new flag[nums.length];
		for(int i=0;i<memo.length;i++)
			memo[i] = flag.UNKNOW;
		memo[memo.length-1] = flag.GOOD;
		
        if(DSFsearch(nums,0,memo))
        	return true;
        else
        	return false;
    }
}
