package leetcode53;

public class Solution {
	public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int result = sum;
        
        for(int i=1;i<nums.length;i++){
        	sum = Math.max(nums[i],sum+nums[i]);
        	result = Math.max(result, sum);
        }
        
        return result;
    }
}
