package leetcode42;

public class Solution {
	public int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        
        int leftmax = Integer.MIN_VALUE;
        int rightmax = Integer.MIN_VALUE;
        
        int area = 0;
        while(left<right){
        	if(height[left]<height[right]){
	        	if(height[left]>leftmax)
	        		leftmax = height[left];
	        	
	        	area += leftmax-height[left];
	        	left++;
        	}else{
        		if(height[right]>rightmax)
	        		rightmax = height[right];
	        	
	        	area += rightmax-height[right];
	        	right--;
        	}
        }
        
        return area;
    }
}
