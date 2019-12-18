package leetcode102;

import java.util.List;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] BT = new int[]{3,9,20,-1,-1,15,7};
		
		TreeNode root = new TreeNode(BT[0]);
		
		Solution test = new Solution();
		
		int index = 0;
		test.insert(root, BT, index, BT.length);
		
		List<List<Integer>> result = test.levelOrder(root);
		
		for (List<Integer> l1 : result) {
			   for (Integer n : l1) {
			       System.out.print(n + " ");
			   }

			   System.out.println();
		}
	}

}
