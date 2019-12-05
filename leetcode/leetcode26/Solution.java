package leetcode26;

public class Solution {
	public int removeDuplicates(int[] nums) {
		if(nums.length==0)
			return 0;
		
		int count = 0;
		int newnum = nums[count];
        for(int i=1;i<nums.length;i++){
        	if(nums[i]!=newnum){
        		newnum = nums[i];
        		count = count+1;
        		nums[count] = newnum;
        	}
        }
        return count+1;
    }
}
