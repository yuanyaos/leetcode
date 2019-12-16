package leetcode42;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
		
		Solution test = new Solution();
		
		int area = test.trap(height);
		
		System.out.println(area);
	}

}
