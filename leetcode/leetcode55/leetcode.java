package leetcode55;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		3,2,1,0,4
//		2,3,1,1,4
		int[] nums = new int[]{3,2,1,0,4};
		
		Solution test = new Solution();
		boolean result = test.canJump(nums);
		
		System.out.println(result);
	}

}
