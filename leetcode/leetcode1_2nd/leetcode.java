package leetcode1_2nd;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{3,3};
		
		Solution test = new Solution();
		int[] result = test.twoSum(nums,6);
		
		for(int i=0;i<result.length;i++){
			System.out.print(result[i]);
			System.out.println();
		}
	}

}
