package leetcode88;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = new int[]{1,2,3,0,0,0};
		int[] nums2 = new int[]{2,5,6};
		
		int m = 3;
		int n = 3;
		
		Solution test = new Solution();
		test.merge(nums1, m, nums2, n);
		
		for(int i=0;i<nums1.length;i++)
			System.out.println(nums1[i]);
	}

}
