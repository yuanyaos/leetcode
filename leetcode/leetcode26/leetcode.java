package leetcode26;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution test = new Solution();
		int[] nums = new int[]{0,1,1,2,2,3,3,4,6,7,8,9,9};
		int len = test.removeDuplicates(nums);
		
		for(int i=0;i<len;i++){
			System.out.println(nums[i]);
		}
		System.out.println("Length is: "+len);
	}

}
