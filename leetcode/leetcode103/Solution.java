package leetcode103;

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

enum Direction 
{ 
    left,right;
} 

public class Solution {
	public void insert(TreeNode node, int[] BT, int index, int length){
		if(index>=length)
			return;
		
		if(node==null)
			return;
//		if(BT[index]==-1){
//			node = null;
//			return;
//		}
		
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
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {		
		List<List<Integer>> result = new ArrayList<>();
		if(root==null)
			return result;
		
		result.add(new ArrayList<Integer>());		
		result.get(0).add(root.val);
		
        List<Stack<TreeNode>> stacks = new ArrayList<Stack<TreeNode>>();
        stacks.add(new Stack<TreeNode>());
        stacks.add(new Stack<TreeNode>());
        
        Direction direction = Direction.right;
        
        stacks.get(0).push(root);
        int index1 = 0, index2 = 1;
        while(!stacks.get(0).isEmpty() || !stacks.get(1).isEmpty()){
        	TreeNode temp;
        	result.add(new ArrayList<Integer>());
        	if(direction==Direction.left){	// from left
        		while(!stacks.get(index1).isEmpty()){
        			temp = stacks.get(index1).pop();
        			// left
        			if(temp.left!=null){
        				result.get(result.size()-1).add(temp.left.val);
        				stacks.get(index2).push(temp.left);
        			}
        			// right
        			if(temp.right!=null){
        				result.get(result.size()-1).add(temp.right.val);
        				stacks.get(index2).push(temp.right);
        			}
        		}
        	}
        	else{
        		while(!stacks.get(index1).isEmpty()){	// from right
        			temp = stacks.get(index1).pop();
        			// right
        			if(temp.right!=null){
        				result.get(result.size()-1).add(temp.right.val);
        				stacks.get(index2).push(temp.right);
        			}
        			// left
        			if(temp.left!=null){
        				result.get(result.size()-1).add(temp.left.val);
        				stacks.get(index2).push(temp.left);
        			}
        		}
        	}
        	
        	// swap index1 and index2
        	int t = index1;
        	index1 = index2;
        	index2 = t;
        	
        	// change scanning direction
        	direction = direction==Direction.left ? Direction.right : Direction.left;
        }
        
        if(result.get(result.size()-1).size()==0)
        	result.remove(result.size()-1);
        
        return result;
    }
}
