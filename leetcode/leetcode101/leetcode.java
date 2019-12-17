package leetcode101;

import java.util.*;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] BT = new int[]{1,2,2,2,-1,2};
		
		TreeNode root = new TreeNode(BT[0]);
		
		Solution test = new Solution();
		
		int index = 0;
		test.insert(root, BT, index, BT.length);
		
		boolean result = test.isSymmetric(root);
		
		System.out.println(result);
	}

}
