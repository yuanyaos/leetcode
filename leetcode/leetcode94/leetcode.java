package leetcode94;

import java.util.List;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] BT = new int[]{1,2,3,4,5,6};
		
		TreeNode root = new TreeNode(BT[0]);
		
		Solution test = new Solution();
		
		int index = 0;
		test.insert(root, BT, index, BT.length);
		
		List<Integer> result = test.inorderTraversal(root);
		
		for(Integer i:result){
			System.out.print(i + " ");
		}
	}

}
