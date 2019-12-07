package leetcode33;

public class Solution {
	public int search(int[] nums, int target) {
        if(nums.length==0)
        	return -1;
        
		int i=0;
        int j=nums.length-1;
        int m;
        while(i<j){
        	m = (i+j)/2;
        	if(target==nums[m])
        		return m;
        	else if(j-i==1)
        		break;
        	
        	if(nums[i]<=nums[m]){ // no rotation
        		if(target>=nums[i] && target<=nums[m])
        			j = m;
        		else
        			i = m;
        	}else{
        		if(target<=nums[j] && target>=nums[m])
        			i = m;
        		else
        			j = m;
        	}
        }
        
        if(nums[i]==target)
        	return i;
        else if(nums[j]==target)
        	return j;
        else
        	return -1;
		
		
//		int left = 0;
//	    int right= nums.length-1;
//	 
//	    while(left<=right){
//	        int mid = left + (right-left)/2;
//	        if(target==nums[mid])
//	            return mid;
//	 
//	        if(nums[left]<=nums[mid]){
//	            if(nums[left]<=target&& target<nums[mid]){
//	                right=mid-1;
//	            }else{
//	                left=mid+1;
//	            }
//	        }else{
//	            if(nums[mid]<target&& target<=nums[right]){
//	                left=mid+1;
//	            }else{
//	                right=mid-1;
//	            }
//	        }    
//	    }
//	 
//	    return -1;
    }
}
