package leetcode34;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{1,2,2};
		Solution test = new Solution();
		
		int[] result = test.searchRange(nums,2);
		
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}

}
