package leetcode35;

public class Solution {
	public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int med;
        
		while(right-left>1){
			med = (left+right)/2;
			if(nums[med]==target)
				return med;
			
			if(nums[med]>target)
				right = med;
			else
				left = med;
		}
		
		if(nums[left]==target)
			return left;
		else if(nums[right]==target)
			return right;
		else{
			if(target<nums[left])
				return left;
			else if(target>nums[right])
				return right+1;
			else
				return right;
		}
		
    }
}
