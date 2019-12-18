package leetcode102;

import java.util.*;

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
	
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        if(root!=null)
        	q.add(root);
        while(!q.isEmpty()){
        	
        	List<Integer> level = new ArrayList<>();
        	int len = q.size();
        	for(int i=0;i<len;i++){
        		TreeNode temp = q.poll();
        		level.add(temp.val);
        		
        		if(temp.left!=null)
        			q.add(temp.left);
        		if(temp.right!=null)
        			q.add(temp.right);
        		
        	}
        	result.add(new ArrayList<>(level));
        }
        
        return result;
    }
}
