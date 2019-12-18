package leetcode104;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] BT = new int[]{3,1,3,4,5,6,7,9};
		
		TreeNode root = new TreeNode(BT[0]);
		
		Solution test = new Solution();
		
		int index = 0;
		test.insert(root, BT, index, BT.length);
		
		int result = test.maxDepth(root);
		
		System.out.println(result);
	}

}
