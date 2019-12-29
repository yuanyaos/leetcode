package leetcode75;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{2,0,1};
		
		Solution test = new Solution();
		test.sortColors(nums);
		
		for(int i=0;i<nums.length;i++)
			System.out.println(nums[i]);
	}

}
