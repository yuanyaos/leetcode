639. Max Path Sum From Leaf To Root
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
public class Solution {
 public int maxPathSumLeafToRoot(TreeNode root) {
   // Time: O(n)
   // Space: O(height)
   if (root.left == null && root.right == null) {
     return root.key;
   }
   if (root.left == null && root.right != null) {
     return root.key + maxPathSumLeafToRoot(root.right);
   }
   if (root.right == null && root.left != null) {
     return root.key + maxPathSumLeafToRoot(root.left);
   }
 
   int left = maxPathSumLeafToRoot(root.left);
   int right = maxPathSumLeafToRoot(root.right);
 
   return root.key + Math.max(left, right);
 }
}
 
141. Binary Tree Path Sum To Target III
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
/*
target = 18
 
5, 16, 22, 25
 
set: 5,
*/
public class Solution {
 private boolean helper(TreeNode root, int target, Set<Integer> allSums, int sumToCurrent) {
  sumToCurrent += root.key;
  if (allSums.contains(sumToCurrent - target)) {
    return true;
  }
 
  // Add sum to current node into the set
  boolean success = allSums.add(sumToCurrent);
 
  // As long as there is a path (true), return true
  if (root.left != null) {
    if (helper(root.left, target, allSums, sumToCurrent)) {
      return true;
    }
  }
 
  if (root.right != null) {
    if (helper(root.right, target, allSums, sumToCurrent)) {
      return true;
    }
  }
 
  // Remember to remove the sumToCurrent
 // Remove only when the the sumToCurrent is distinct in the set
 // sumToCurrent may have occurred in the history and will still be used in other path.
 // If removed, other path will lose the history
 if (success) {
   allSums.remove(sumToCurrent);
 }
 
  return false;
}
 
 public boolean exist(TreeNode root, int target) {
   // Write your solution here
   if (root == null) {
     return false;
   }
 
   // Save all sum of all paths we have been to
   Set<Integer> allSums = new HashSet<>();
   allSums.add(0);
    return helper(root, target, allSums, 0);
 }
}
140. Maximum Path Sum Binary Tree III
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
/*
public class Solution {
 void helper(TreeNode root, int sum, int[] globalMax) {
   if (root == null) {
     return;
   }
 
   if (sum >= 0) {
     sum += root.key;
   } else {
     sum = root.key;
   }
 
   globalMax[0] = Math.max(globalMax[0], sum);
   helper(root.left, sum, globalMax);
   helper(root.right, sum, globalMax);
}
*/
public class Solution {
 private int helper(TreeNode root, int[] globalMax) {
   if (root == null) {
     return 0;
   }
 
   int left = helper(root.left, globalMax);
   int right = helper(root.right, globalMax);
 
   // Avoid negative sum!
   int temp = Math.max(0, Math.max(left, right)) + root.key;
   globalMax[0] = Math.max(globalMax[0], temp);
   return temp;
 }
 public int maxPathSum(TreeNode root) {
   // Write your solution here
   // Subproblem: getting max path sum for the subutree
   // Recursive rule: 1. we get the max path sum from the left and right subtree
   // 2. report max(left, right, 0) + root
   // Base case: root == null, return 0
 
   // Time: O(n), traverse all node
   // Space: O(height)
  
   if (root == null) {
     return 0;
   }
 
   int[] globalMax = new int[1];
   globalMax[0] = Integer.MIN_VALUE;
   helper(root, globalMax);
 
   return globalMax[0];
 }
}
523. Flatten Binary Tree to Linked List
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
public class Solution {
 private void helper(TreeNode root, TreeNode[] prev) {
   if (root == null) {
     return;
   }
 
   helper(root.right, prev);
   helper(root.left, prev);
   root.right = prev[0];
   root.left = null;
   prev[0] = root;
 
   return;
 }
 
 public TreeNode flatten(TreeNode root) {
   // Write your solution here
   // Reverse pre-order traversal
 
   TreeNode[] prev = new TreeNode[1];
   helper(root, prev);
   return root;
 }
}
138. Maximum Path Sum Binary Tree I
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
public class Solution {
   private int globalMax = Integer.MIN_VALUE;
 
    private int helper(TreeNode root) {
      // Base case
      if (root == null) {
        return 0;
      }
 
      // Recursion rule
      // 返回的一定是single path上的sum!!!
      int left = helper(root.left);
      int right = helper(root.right);
 
     // 一定要注意这个条件：root.left != null && root.right != null
      if (left + right + root.key > globalMax && root.left != null && root.right != null) {
   	 globalMax = left + right + root.key;
 	 }
 
     // 还要注意只有单边subtree，不能要是null的subtree，返回值0会影响结果
      if (root.left == null) {
        return right + root.key;
      } else if (root.right == null) {
        return left + root.key;
      }
     
      return left > right ? left + root.key : right + root.key;
    }
 
    public int maxPathSum(TreeNode root) {
      // Post-order traversal
      // From the subtree we get the sum of a SINGLE PATH, sum_a and sum_b,
      // if (sum_a + sum_b + root.key > global_max) update global_max
      // if sum_a > sum_b, return sum_a + root.key
      // else, return sum_b + root.key
     
      int singlePathSum = helper(root);
 
      return globalMax;
    }
}
139. Maximum Path Sum Binary Tree II
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
public class Solution {
 private int globalMax = Integer.MIN_VALUE;
 
 private int helper(TreeNode root) {
   // Base case
   if (root == null) {
     return 0;
   }
 
   // Recursion rule
   // Return the maxsimum single path sum of this subtree
   int left = helper(root.left);
   int right = helper(root.right);
 
   // 避免negative contribution to the single path
   left = left < 0 ? 0 : left;
   right = right < 0 ? 0 : right;
 
   // 更新globalMax
   if (left + right + root.key > globalMax) {
     globalMax = left + right + root.key;
   }
 
   return left > right ? left + root.key : right + root.key;
 }
 
 public int maxPathSum(TreeNode root) {
   /*
   4 cases:
   root.key (left <= 0 && right <= 0)
   root.key + left (left > 0 && right <= 0)
   root.key + right (right > 0 && left <= 0)
   root.key + left + right (left > 0 && right > 0)
   */
   // 不单单是leaf，而是any node to any node
   // 从下层得到sum of single path for both subtrees
   // 如果sum是负数的话就不要了，因为negatively contribute to the sum
   // 如果 left + right + root.key > global_max, 则更新global_max
   // 返回最大的single path sum, 注意可能是当前的root
   // Time: O(n), space: O(height)
 
   int sum = helper(root);
   return globalMax;
 }
}
213. Reconstruct Binary Tree With Preorder And Inorder
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
public class Solution {
 private TreeNode helper(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> inOrderMap) {
   if (inStart > inEnd || preStart > preEnd) {
     return null;
   }
 
   // root is always the first element in preOrder
   int inRoot = inOrderMap.get(preOrder[preStart]);
 
   TreeNode root = new TreeNode(preOrder[preStart]);
 
   /*
   left_tree_pre = [pre_start + 1, pre_start + (in_root - in_left)]
   left_tree_in = [in_start, in_root - 1]
  
   right_tree_pre = [pre_start + (in_root - in_left) + 1, pre_end];
   right_tree_in = [in_root + 1, in_end]
   */
 
   root.left = helper(preOrder, inOrder, preStart + 1, preStart + (inRoot - inStart), inStart, inRoot - 1, inOrderMap);
   root.right = helper(preOrder, inOrder, preStart + (inRoot - inStart) + 1, preEnd, inRoot + 1, inEnd, inOrderMap);
 
   return root;
 }
 
 private Map<Integer, Integer> getMap(int[] inOrder) {
   Map<Integer, Integer> map = new HashMap<>();
   for (int i = 0; i < inOrder.length; i++) {
     map.put(inOrder[i], i);
   }
   return map;
 }
 
 public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
   /*
   We can use recursion to find the boundary in pre and in for left and right subtrees
        0 1 2 3 4 5
   pre: 5 3 1 4 8 11
   in:  1 3 4 5 8 11
 
   in_start = 0, in_end = 5
   5 is root, find 5 in 'in'
   in_root = 3
 
   left_tree_in = [in_start, in_root - 1]
   right_tree_in = [in_root + 1, in_end]
 
   left_tree_pre = [pre_start + 1, pre_start + (in_root - in_left)]
   right_tree_pre = [pre_start + (in_root - in_left) + 1, pre_end];
 
   In each level, we need to get the root index in 'in', so we need a map<value, index>
   Assume NO duplicate
 
   Base case: in_start > in_end, return null
 
   Time: O(n)
   Space: O(n)
   */
   if (inOrder == null || preOrder == null) {
     return null;
   }
 
   Map<Integer, Integer> inOrderMap = getMap(inOrder);
   return helper(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1, inOrderMap);
 }
}
 

https://www.cnblogs.com/grandyang/p/4296500.html
/**
* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* }
*/
// Time: O(n), space: O(n)
public class Solution {
 private TreeNode helper(int[] pre, int[] in, int[] preIndex, int[] inIndex, int target) {
 	 if (inIndex[0] >= in.length || in[inIndex[0]] == target) {
   	 // in array的作用是提供root的信息
   	 return null;
 	 }
 	 TreeNode root = new TreeNode(pre[preIndex[0]]);
 	
 	 preIndex[0]++;
 	 root.left = helper(pre, in, preIndex, inIndex, root.key);
 	 inIndex[0]++;
 	 root.right = helper(pre, in, preIndex, inIndex, target);
 	 return root;
    }
   
    public TreeNode reconstruct(int[] in, int[] pre) {
 	 int[] preIndex = new int[] {0};
 	 int[] inIndex = new int[] {0};
 	 return helper(pre, in, preIndex, inIndex, Integer.MAX_VALUE);
    }
}
