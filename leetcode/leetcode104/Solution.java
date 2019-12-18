package leetcode104;

public class Solution {
	static int maxdeph = -1;
	
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
	
	private void DSFsearch(TreeNode node, int currdepth){
		
		if(node.left==null && node.right==null){
			if(currdepth>Solution.maxdeph){
				Solution.maxdeph = currdepth;
				return;
			}
		}
		if(node.left!=null)
			DSFsearch(node.left,currdepth+1);
		if(node.right!=null)
			DSFsearch(node.right,currdepth+1);
		
		return;
	}
	
	public int maxDepth(TreeNode root) {
		if(root==null)
			return 0;
		
		int currdepth = 0;
        DSFsearch(root,currdepth+1);
        
        return Solution.maxdeph;
    }
}
