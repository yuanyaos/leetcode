package leetcode94;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public void insert(TreeNode node, int[] BT, int index, int length){
		if(index>=length)
			return;
		
		if(node==null)
			return;

		node.val = BT[index];
		
		if(2*index+1<length && BT[2*index+1]!=-1){
			node.left = new TreeNode(0);	// left child
			insert(node.left, BT, 2*index+1, length);
		}
		else{
			node.left = null;
		}
		
		if(2*index+2<length && BT[2*index+2]!=-1){
			node.right = new TreeNode(0);	// right child
			insert(node.right, BT, 2*index+2, length);
		}
		else{
			node.right = null;
		}
	}
	
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        recurse(root,result);
        return result;
    }
	
	private void recurse(TreeNode root, List<Integer> result){
		if(root==null)
			return;
		if(root.left!=null){
			recurse(root.left,result);
		}
		result.add(root.val);
		if(root.right!=null){
			recurse(root.right,result);
		}
	}
}
