package leetcode31;

public class Solution {
	public void swap(int[] nums, int i, int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	public void reverseArray(int start,int[] nums){
		int i=start;
		int j=nums.length-1;
		while(i<j){
			swap(nums,i,j);
			i++;
			j--;
		}
	}
	
	public void nextPermutation(int[] nums) {
		if(nums.length==1)
			return;
		
		int i=nums.length-2;
		while(i>=0 && nums[i]>=nums[i+1])
			i--;
		
		if(i>=0){
			int j=nums.length-1;
			while(j>=0){
				if(nums[i]<nums[j]){
					swap(nums,i,j);
					break;
				}
				j--;
			}
		}
		
		reverseArray(i+1,nums);
    }
}
