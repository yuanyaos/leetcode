package leetcode27;

public class Solution {
	public int removeElement(int[] nums, int val) {
		if(nums.length==0)
			return 0;
		
		int count = 0;
        for(int i=0;i<nums.length;i++){
        	if(nums[i]!=val){        		
        		nums[count] = nums[i];
        		count = count+1;
        	}
        }
        return count;
    }
}
