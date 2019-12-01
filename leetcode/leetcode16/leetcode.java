package leetcode16;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution test=new Solution();
		int[] nums={-1, 2, 1, -4};
		int target=1;
		int diff;
		
		diff = test.threeSumClosest(nums,target);
		
		System.out.print(diff);
	}

}
