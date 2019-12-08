package leetcode34;

public class Solution {
	public int leftrightmosttarget(int[] nums, int target, int flag){
		int left = 0;
        int right = nums.length-1;
        int med;
        
        if(nums[left]==nums[right] && nums[left]==target){
        	if(flag==0)
        		return left;
        	else
        		return right;
        }
        
        while(right-left>1){
        	med = (left+right)/2;
        	if(nums[med]==target){
        		if(flag==0)
        			right = med;	// leftmost
        		else
        			left = med;
        		continue;
        	}
        	
        	if(nums[med]<target)
        		left = med;
        	else if(nums[med]>target)
        		right = med;
        }
        
        if(nums[left]==target && nums[right]==target){
        	if(flag==0)
        		return left;
        	else
        		return right;
        }
        else if(nums[left]==target)
        	return left;
        else if(nums[right]==target)
        	return right;
        else
        	return -1;
	}
	
	public int[] searchRange(int[] nums, int target) {
		if(nums.length==0)
			return new int[]{-1,-1};
		
		int[] leftrightmost = new int[]{-1,-1};
		leftrightmost[0] = leftrightmosttarget(nums,target,0);
		leftrightmost[1] = leftrightmosttarget(nums,target,1);
		
		return leftrightmost;
    }
}
