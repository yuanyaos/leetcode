package leetcode11;


public class Leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		[1,8,6,2,5,4,8,3,7]
		Solution test=new Solution();
		int[] height = new int[]{1,8,6,2,5,4,8,3,7};
		int maxarea=test.maxArea(height);
		System.out.println(maxarea);
	}

}
