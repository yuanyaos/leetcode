package leetcode15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
        if(nums==null || nums.length==0){
        	List<List<Integer>> list=new ArrayList<List<Integer>>();
        	return list;
        }
        List<List<Integer>> list=new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
        for(int i=0;i<nums.length-2;i++){
        	if(i==0 || (i>0 && nums[i]!=nums[i-1])){
	        	int pivot=nums[i];
	        	int left=i+1;
	        	int right=nums.length-1;
	        	while(left<right){
	        		if(pivot+nums[left]+nums[right]==0){
	        			List<Integer> listzero=new ArrayList<Integer>();
	        			listzero.add(pivot);
	        			listzero.add(nums[left]);
	        			listzero.add(nums[right]);
	        			list.add(listzero);
	        			
	        			while(left<right && nums[left]==nums[left+1])
	        				left++;
	        			
	        			while(left<right && nums[right]==nums[right-1])
	        				right--;
	        			left++;
	        			right--;
	        		}
	        		else if(pivot+nums[left]+nums[right]<0){
	        			left++;
	        		}else{
	        			right--;
	        		}
	        	}
        	}
        }
        return list;
    }
}
