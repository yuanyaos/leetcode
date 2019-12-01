package leetcode18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

public class Solution {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		if(nums==null || nums.length==0){
        	List<List<Integer>> list=new ArrayList<List<Integer>>();
        	return list;
        }
        List<List<Integer>> list=new ArrayList<List<Integer>>();
        HashSet<ArrayList<Integer>> setzero=new HashSet<ArrayList<Integer>>();
        Arrays.sort(nums);
        
        for(int a=0;a<nums.length-3;a++)
	        for(int i=a+1;i<nums.length-2;i++){

		        	int pivot=nums[a];
		        	int pivot2=nums[i];
		        	int left=i+1;
		        	int right=nums.length-1;
		        	while(left<right){
		        		if(pivot+pivot2+nums[left]+nums[right]==target){
		        			ArrayList<Integer> listzero=new ArrayList<Integer>();
		        			listzero.add(pivot);
		        			listzero.add(pivot2);
		        			listzero.add(nums[left]);
		        			listzero.add(nums[right]);
		        			if(!setzero.contains(listzero)){
		        				list.add(listzero);
			        			setzero.add(listzero);
		        			}		        			
		        			while(left<right && nums[left]==nums[left+1])
		        				left++;
		        			
		        			while(left<right && nums[right]==nums[right-1])
		        				right--;
		        			left++;
		        			right--;
		        		}
		        		else if(pivot+pivot2+nums[left]+nums[right]<target){
		        			left++;
		        		}else{
		        			right--;
		        		}
		        	}

	        }
        return list;
    }
}
