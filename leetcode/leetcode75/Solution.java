package leetcode75;

public class Solution {
	private void swap(int[] nums, int a, int b){
		int t = nums[a];
		nums[a] = nums[b];
		nums[b] = t;
	}
	
	public void sortColors(int[] nums) {
        int n = nums.length;
		int p0 = 0;
        int p2 = n-1;
        int cur = 0;
        
        while(cur<=p2){
        	if(nums[cur]==0){
        		swap(nums,p0,cur);
        		p0++;
        		cur++;
        	}
        	else if(nums[cur]==2){
        		swap(nums,cur,p2);
        		p2--;
        	}
        	else{
        		cur++;
        	}
        }
    }
}
