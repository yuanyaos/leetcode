package leetcode101;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

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
	
	public boolean isSymmetric(TreeNode root) {
		if(root==null)
            return true; 
		if(root.left==null && root.right==null)
			return true;
		else if((root.left==null && root.right!=null) || root.left!=null && root.right==null)
			return false;
		
        Queue<TreeNode> q_le = new LinkedList<TreeNode>();
        Queue<TreeNode> q_ri = new LinkedList<TreeNode>();
        
        q_le.add(root.left);
        q_ri.add(root.right);
        while(!q_le.isEmpty() || !q_ri.isEmpty()){
        	TreeNode temp_le = q_le.poll();
        	TreeNode temp_ri = q_ri.poll();
        	
        	if (temp_le == null && temp_ri == null)
        		continue;
            if (temp_le == null || temp_ri == null)
            	return false;
        	if(temp_le.val!=temp_ri.val)
        		return false;
        	
        		q_le.add(temp_le.left);
        		q_le.add(temp_le.right);
        	
        		q_ri.add(temp_ri.right);
        		q_ri.add(temp_ri.left);
        	
        	if(q_le.size()!=q_ri.size())
        		return false;
        }
        
        return true;
    }
}
