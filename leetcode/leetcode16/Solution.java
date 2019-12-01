package leetcode16;

import java.util.Arrays;

public class Solution {
	public int threeSumClosest(int[] nums, int target) {
//		if(nums==null || nums.length==0)
//			return 0;
		int result = 0;;
        Arrays.sort(nums);
        
        int diffmin=Integer.MAX_VALUE;
        for(int i=0;i<nums.length-2;i++){
        	if(i==0 || (i>0 && nums[i]!=nums[i-1])){
	        	int pivot=nums[i];
	        	int left=i+1;
	        	int right=nums.length-1;
	        	while(left<right){
	        		int diff=Math.abs(target-pivot-nums[left]-nums[right]);
	        		if(diff<diffmin){
	        			diffmin = diff;
	        			result = pivot+nums[left]+nums[right];
	        		}
	        		if(pivot+nums[left]+nums[right]<target){
	        			left++;
	        		}else{
	        			right--;
	        		}
	        	}
        	}
        }
        return result;
    }
}
