package leetcode41;

public class Solution {
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for(int i=0;i<n;++i){
			while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]){
				int t = nums[i];
				nums[i] = nums[t];
				nums[t] = t;
			}
		}
		
		int i;
		for(i=0;i<n;i++)
			if(nums[i]!=i)
				return i+1;
		
		return i+1;
    }
}
