package leetcode88;

public class Solution {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] numscopy = new int[m+n];
        
        int p1 = 0;
        int p2 = 0;
        int p = 0;
        
        while(p1<m && p2<n) {
        	if(nums1[p1]<=nums2[p2]) {
        		numscopy[p] = nums1[p1];
        		p++;
        		p1++;
        	}else {
        		numscopy[p] = nums2[p2];
        		p++;
        		p2++;
        	}
        }
        if(p1>=m && p2<n) {
        	while(p2<n) {
        		numscopy[p] = nums2[p2];
        		p++;
        		p2++;
        	}
        }else if(p2>=n && p1<m) {
        	while(p1<m) {
        		numscopy[p] = nums1[p1];
        		p++;
        		p1++;
        	}
        }
        
        for(int i=0;i<m+n;i++)
        	nums1[i] = numscopy[i];
    }
}
