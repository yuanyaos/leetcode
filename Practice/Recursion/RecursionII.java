121. Spiral Order Traverse I
public class Solution {
 private void helper(int[][] matrix, int offset, List<Integer> list, int size) {
   // Base case
   if (size == 0) {
     return;
   }
   if (size == 1) {
     list.add(matrix[offset][offset]);
     return;
   }
 
   // Subproblem
   for (int i = 0; i < size - 1; i++) {
     list.add(matrix[offset][offset + i]);
   }
   for (int i = 0; i < size - 1; i++) {
     list.add(matrix[offset + i][offset + size - 1]);
   }
   for (int i = size - 1; i >= 1; i--) {
     list.add(matrix[offset + size - 1][offset + i]);
   }
   for (int i = size - 1; i >= 1; i--) {
     list.add(matrix[offset + i][offset]);
   }
   helper(matrix, offset + 1, list, size - 2);
 }
 
 public List<Integer> spiral(int[][] matrix) {
   // Write your solution here
   // Divide into subproblem
   // Time: O(n)
   // Time: O(n/2)
 
   // Assumption: square
   List<Integer> list = new ArrayList<>();
   helper(matrix, 0, list, matrix.length);
 
   return list;
 }
}
233. N Queens
          0        1        2         3        4

0        0        1        2         X        4

1        1        2        3         4        5

2        2        X        4         5        6

3        3        4        5         6        7

4        4        5        6         7        8


          0        1        2 

2        2        3        4

1        X        2        3

0        0        1        2

column: int[N]
column index: y

diagnol: int[2*N]
diagonal index: x + y
e.g. at diagonal[3], there is already a chess

Reversed diagonal: int[2*N]
reversed diagonal index: (n - 1 - x) + y

Each layer is one row. At each layer, we need to determine if current column is valid O(1)

layer 1: N, layer 2: N-1 ….. 1 => Time: O(N!)
Space: O(n)

public class Solution {
 private List<Integer> arrayToList(int[] array) {
   List<Integer> temp = new ArrayList<>();
   for (int i = 0; i < array.length; i++) {
     temp.add(array[i]);
   }
   return temp;
 }
 
 private void mark(int n, int x, int y, boolean[] usedCol, boolean[] usedDiag, boolean[] usedRevDiag) {
   usedCol[y] = true;
   usedDiag[x + y] = true;
   usedRevDiag[x - y + n - 1] = true;
   return;
 }
 
 private void unmark(int n, int x, int y, boolean[] usedCol, boolean[] usedDiag, boolean[] usedRevDiag) {
   usedCol[y] = false;
   usedDiag[x + y] = false;
   usedRevDiag[x - y + n - 1] = false;
   return;
 }
 
 private boolean isValid(int n, int x, int y, boolean[] usedCol, boolean[] usedDiag, boolean[] usedRevDiag) {
   return !usedCol[y] && !usedDiag[x + y] && !usedRevDiag[x - y + n - 1];
 }
 
 private void helper(int row, int n, List<List<Integer>> results, int[] storedCol, boolean[] usedCol,
 boolean[] usedDiag, boolean[] usedRevDiag) {
   // 1. Base case
   if (row == n) {
     results.add(arrayToList(storedCol));
   }
   // 2. Subproblem
   // 对于current row，检查每一列
   for (int i = 0; i < n; i++) {
     if (!isValid(n, row, i, usedCol, usedDiag, usedRevDiag)) {
       continue;
     }
     storedCol[row] = i;
     mark(n, row, i, usedCol, usedDiag, usedRevDiag);
     helper(row + 1, n, results, storedCol, usedCol, usedDiag, usedRevDiag);
     unmark(n, row, i, usedCol, usedDiag, usedRevDiag);
   }
 
   // 3. What to report to upper level
   return;
 }
 
 public List<List<Integer>> nqueens(int n) {
   // 使用一个对角线的坐标，在一行行的往下走的时候，所需要判断当前column和两个对角线方向上有没有已经
   // 放了一个queen，为了避免重复的查看column和对角线上的每一个格子，可以用三个arrays来记录column，
   // diagonal和reversed diagonal上的坑有没有被占
   // ********** 对于位置(x, y)，column是y，diagonal是x+y, reversed diagonal是x+(-y+n-1) **********
   //   | 0 1 2 3
   // -----------
   // 0 | 0 1 2 3
   // 1 | 1 2 3 4
   // 2 | 3 4 5 6
 
   List<List<Integer>> results = new ArrayList<List<Integer>>();
   int[] storedCol = new int[n];  // Current column being checked given a row
   boolean[] usedCol = new boolean[2 * n];  // Used column
   boolean[] usedDiag = new boolean[2 * n];  // Used diagonal
   boolean[] usedRevDiag = new boolean[2 * n];  // Used revered diagonal
   helper(0, n, results, storedCol, usedCol, usedDiag, usedRevDiag);
 
   return results;
 }
}
 
Naive:
/*
CA: N > 0, the signature is: List<List<Integer>> nqueens(int n)
R: Use DSF. Every level is one row. At each level, check if it is valid compared to previous chess position
 
Time: O(n^n) Every level has n node (colume) and there are n level
Space: O(n)
*/
 
public class Solution {
 private boolean isValid(int col, List<Integer> cur) {
   // cur[end] save the chess colume position for previous rows
   int row = cur.size();
   // For each previous row, find if the chess conflicts with current chess
   for (int i = 0; i < row; i++) {
     // if current chess position is at the colume or diagonal of previous chess position
     // then it is not valid and return false
     if (col == cur.get(i) || (row - i) == Math.abs(col - cur.get(i))) {
       return false;
     }
   }
   return true;
 }
 
 private void helper(int n, List<Integer> cur, List<List<Integer>> result) {
   if (cur.size() == n) {
     result.add(new ArrayList<Integer>(cur));
     return;
   }
 
   // Go through all column at current row
   // Go to next row only if the current (row, col) is valid (not conflict with existing chesses)
   for (int i = 0; i < n; i++) {
     if (isValid(i, cur)) {
       // if current column is valid, push into the end of the list
       cur.add(i);
       helper(n, cur, result);
       // Must remove the last one to vacate the space for the next valid column at current row
       cur.remove(cur.size() - 1);
     }
   }
 }
 
 public List<List<Integer>> nqueens(int n) {
   // Write your solution here
   List<Integer> current = new ArrayList<>();
   List<List<Integer>> result = new ArrayList<>();
 
   helper(n, current, result);
 
   return result;
 }
}
 
35. Reverse Linked List In Pairs
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode reverseInPairs(ListNode head) {
   // even           h  p
   // 1->2->3->4->   5->6->   returned ListNode
   //                p  h
   // 2->1->4->3->   6->5->   returned ListNode (do not care about what is return, just believe it is right)
   // Recursion
   // Time: O(n), Space: O(n)
 
   if (head == null || head.next == null) {
     return head;
   }
 
   ListNode p = head.next;
   head.next = reverseInPairs(p.next);
   p.next = head;
   return p;
}
}
 
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode reverseInPairs(ListNode head) {
    if (head == null) {
 	 return head;
    }
 
   ListNode dummy = new ListNode(0);
    dummy.next = head;
 
   ListNode p1 = dummy;
   ListNode p2 = p1.next;
   ListNode p3 = null;
   ListNode p4 = null;
   while (p2 != null && p2.next != null) {
     p3 = p2.next;
     p4 = p3.next;
 
     // reverse and connect
     p3.next = p2;
     p2.next = p4;
     p1.next = p3;
 
      // update
      p1 = p2;
      p2 = p1.next;
   }
    return dummy.next;
 
}
}
292. String Abbreviation Matching
public class Solution {
 public boolean match(String input, String pattern) {
   // Write your solution here
   int ii = 0;  // 用在input里
   int pp = 0;  // 用在pattern里
   while (ii < input.length() && pp < pattern.length()) {
     // 如果都是非数字字符的话，如果两个指针对应的char不一样，则说明没对上，返回flase
     if (pattern.charAt(pp) < '0' || pattern.charAt(pp) > '9') {
       if (input.charAt(ii) != pattern.charAt(pp)) {
         return false;
       }
       ii++;
       pp++;
     } else {
       int count = 0;
       while (pp < pattern.length() && pattern.charAt(pp) >= '0' && pattern.charAt(pp) <= '9') {
         count = (pattern.charAt(pp) - '0') + count * 10;
         pp++;
       }
       ii += count;
     }
   }
   return ii == input.length() && pp == pattern.length();
 }
}
122. Spiral Order Traverse II
public class Solution {
 public List<Integer> spiral(int[][] matrix) {
   // Write your solution here
   List<Integer> list = new ArrayList<>();
   int m = matrix.length;
   if (m == 0) {
     return list;
   }
   int n = matrix[0].length;
 
   int left = 0;
   int right = n - 1;
   int up = 0;
   int down = m - 1;
   while (left < right && up < down) {
     for (int i = left; i <= right; i++) {
       list.add(matrix[up][i]);
     }
     for (int i = up + 1; i <= down; i++) {
       list.add(matrix[i][right]);
     }
     for (int i = right - 1; i >= left; i--) {
       list.add(matrix[down][i]);
     }
     for (int i = down - 1; i >= up + 1; i--) {
       list.add(matrix[i][left]);
     }
     left++;
     right--;
     up++;
     down--;
   }
 
   if (left > right || up > down) {
     return list;
   }
   if (left == right) {
     for (int i = up; i <= down; i++) {
       list.add(matrix[i][left]);
     }
   } else {
     for (int i = left; i <= right; i++) {
       list.add(matrix[up][i]);
     }
   }
   return list;
 }
}
126. Lowest Common Ancestor I
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
 public TreeNode lowestCommonAncestor(TreeNode root,
     TreeNode one, TreeNode two) {
   // Write your solution here.
   // Clarification: one and two gauranteed to be in the tree?
 
   // 一个root有两个subtree，如果当前root是left subtree的ancestor，同时也是right subtree的ancestor，则当前root是common ancestor
   // 如果仅仅是left subtree的ancestor，则需要继续往左找
   // 如果仅仅是right subtree的ancestor，则需要继续往又找
 
   // Base case 1: 找到最底部也不是ancestor，返回null
   if (root == null) {
     return null;
   }
   // Base case 2: 只要当前root是one或者two就可以返回root，告诉上一层这里有target node出现
   if (root == one || root == two) {
     return root;
   }
 
   TreeNode leftNode = lowestCommonAncestor(root.left, one, two);
   TreeNode rightNode = lowestCommonAncestor(root.right, one, two);
 
   if (leftNode != null && rightNode != null) {
     // one and two分别来自两边，则当前root是LCA
     return root;
   } else if (leftNode != null) {
     // 只来自于单边，则向上级传递
     return leftNode;
   } else {
     return rightNode;
   }
 }
}
646. Store Number Of Nodes In Left Subtree
/**
* public class TreeNodeLeft {
*   public int key;
*   public TreeNodeLeft left;
*   public TreeNodeLeft right;
*   public int numNodesLeft;
*   public TreeNodeLeft(int key) {
*     this.key = key;
*   }
* }
*/
public class Solution {
 private int postOrder(TreeNodeLeft root) {
   // return total node number
   if (root == null) {
     return 0;
   }
 
   int leftNum = postOrder(root.left);
   int rightNum = postOrder(root.right);
   root.numNodesLeft = leftNum;
 
   return leftNum + rightNum + 1;
 }
 public void numNodesLeft(TreeNodeLeft root) {
   // Write your solution here
   postOrder(root);
 
   return;
 }
}
178. Reverse Binary Tree Upside Down
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
 public TreeNode reverse(TreeNode root) {
   // 用recursion的方法
   // 先解决小问题，再想办法怎么向上report，report什么
   /***********  想清楚向上report什么！是root还是newRoot **************/
   // Time: O(n), space: O(logn)
   /* subproblem
           2
         /  \
        3    4
   */
   if (root == null || root.left == null) {
     return root;
   }
 
   // 从底层得到的修改好的subtree
   TreeNode newRoot = reverse(root.left);
 
   root.left.left = root;
   root.left.right = root.right;
   root.left = null;
   root.right = null;
 
   return newRoot;
 }
}

Find max leave to leave path
Given a binary tree in which each node contains an int number.
Find the maximum possible sum from any leaf node to another leaf node.
The maximum sum path may or may not go through root.
Expected time complexity is O(n).

	// C: What is the output?
	// A: return the length of max path. Return Integer.MIN_VALUE if root is null
	// A: Node value cannot be Integer.MIN_VALUE or Integer.MAX_VALUE
	
	// R:
	// Subproblem: get the max path for left/right-subtree
	// Recursive rule:
	// 1. globalMax = max(globalMax, (left + right + current))
	// 2. return max(left, right) + root
	
	// Time: O(n) traverse all nodes
	// Space: O(logn) = O(height)
	private int helper(TreeNode root, int[] globalMax) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(root.left, globalMax);
		int right = helper(root.right, globalMax);
		
		// NOTE THAT the return value can be negative. So cannot simply return 0 while finding null
		if (root.left != null && root.right != null) {
			globalMax[0] = Math.max(globalMax[0], left + right + root.key);
			return Math.max(left, right) + root.key;
		}
		
		// If left/right child is null
		return root.left == null ? right + root.key : left + root.key;
	}
	
	public int findMaxFromLeaveToLeave(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		
		int[] globalMax = new int[] {Integer.MIN_VALUE};
		
		return globalMax[0];
	}
