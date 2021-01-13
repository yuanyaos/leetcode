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
 private void traverse(List<Integer> list, TreeNode root) {
   if (root == null) {
     return;
   }
 
   list.add(root.key);
 
   traverse(list, root.left);
   traverse(list, root.right);
 
   return;
 }
 public List<Integer> preOrder(TreeNode root) {
   // Write your solution here
   if (root == null) {
     return new LinkedList<Integer>();
   }
 
   List<Integer> list = new LinkedList<Integer>();
   traverse(list, root);
 
   return list;
 }
}
 
654. In-order Traversal Of Binary Tree (recursive)

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
 private void traverse(List<Integer> list, TreeNode root) {
   if (root == null) {
     return;
   }
 
   traverse(list, root.left);
   list.add(root.key);
   traverse(list, root.right);
 
   return;
 }
 public List<Integer> inOrder(TreeNode root) {
   // Write your solution here
   if (root == null) {
     return new LinkedList<Integer>();
   }
 
   List<Integer> list = new LinkedList<Integer>();
   traverse(list, root);
 
   return list;
 }
}
 
60. Height of Binary Tree
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
 public int findHeight(TreeNode root) {
   // Write your solution here
   if (root == null) {
     return 0;
   }
 
   return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
 }
}
 
 
656. Post-order Traversal Of Binary Tree (recursive)

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
 private void traverse(List<Integer> list, TreeNode root) {
   if (root == null) {
     return;
   }
 
   traverse(list, root.left);
   traverse(list, root.right);
   list.add(root.key);
 
   return;
 }
 public List<Integer> postOrder(TreeNode root) {
   // Write your solution here
   if (root == null) {
     return new LinkedList<Integer>();
   }
 
   List<Integer> list = new LinkedList<Integer>();
   traverse(list, root);
 
   return list;
 }
}
 
60. Height of Binary Tree
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
 public int findHeight(TreeNode root) {
   // Write your solution here
   // Find the height of subtree
   // max(leftHeight, rightHeight) + 1
   // Time: O(n)
   // Space: O(height)
   if (root == null) {
     return 0;
   }
 
   return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
 }
}
46. Check If Binary Tree Is Balanced
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
 private int isBalanced2(TreeNode root) {
   if (root == null) {
     return 0;
   }
 
   int leftHeight = isBalanced2(root.left);
   int rightHeight = isBalanced2(root.right);
   if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
       return -1;
   }
 
   return Math.max(leftHeight, rightHeight) + 1;
 }
 public boolean isBalanced(TreeNode root) {
   // Write your solution here
   // Subproblem: if root.left and root.right are balanced
   // Recursion rule: root.left and root.right are balanced and the left height and right height difference is <= 1
   // Base case: return true
   if (root == null) {
     return true;
   }
 
   if (isBalanced2(root) == -1) {
     return false;
   } else {
     return true;
   }
 }
}

 
48. Symmetric Binary Tree
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
 private boolean isSymmetric(TreeNode one, TreeNode two) {
   // Base case
   if ((one == null && two != null) || (one != null && two == null)) {
     return false;
   } else if (one == null && two == null) {
     return true;
   }
 
   // true: one.left and two.right are symmetric tree and one.key == two.key
   if (isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left) && (one.key == two.key)) {
     return true;
   }
 
   return false;
 }
 
 public boolean isSymmetric(TreeNode root) {
   // Write your solution here
   // Time: O(n/2) = O(n)
   // Space: O(height)
   if (root == null) {
     return true;
   }
 
   return isSymmetric(root.left, root.right);
 }
}
 
54. Is Binary Search Tree Or Not
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
 public boolean isBST(TreeNode root, int min, int max) {
   if (root == null) {
     return true;
   }
 
   if (root.key < min || root.key > max) {
     return false;
   }
 
   return isBST(root.left, min, root.key - 1) && isBST(root.right, root.key + 1, max);
 }
 public boolean isBST(TreeNode root) {
    // Write your solution here
    // Assumption: 1. all inputs are integers between the MIN_VALUE and MAX_VALUE; 2. no duplicate
    // Corner case: root == null? return true
    // Time: O(n) traverse all nodes
    // Space: O(height)
 
   // Subproblem: node.left and node.right is BST?
   // 错误的！！！Recursion rule: node.left == BST && node.right == BST && node.left.key < node.key && node.right.key > node.key
   // Subproblem: 错的！！！！！！！！！ check left.key < root.key < right.key
   // 比如：
   //         6
   //   4           8
   // 1    7
 
   // Recursion rule: node.left == BST && node.right == BST && node.left.key和node.right.key属于range里面
   // Base case: node == null, return true
   if (root == null) {
     return true;
   }
 
   // WRONG!!!
   // if (isBST(root.left) && isBST(root.right) && root.left.key < root.key && root.right.key > root.key) {
   //   return true;
   // }
   return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
 }
}
 
50. Tweaked Identical Binary Trees
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
 public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
   // Write your solution here
   // Time: O(4^(logn)) = O(n^2)
   // Space: O(height)
   if (one == null && two == null) {
     return true;
   } else if ( (one == null && two != null) || (one != null && two == null) ) {
     return false;
   }
 
   if ( ( (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)) ||
          (isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left)) ) &&
          one.key == two.key ) {
            return true;
          }
  
   return false;
 }
}
 
55. Get Keys In Binary Search Tree In Given Range
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
 public void getRange(TreeNode root, List list, int min, int max) {
   if (root == null) {
     return;
   }
 
   getRange(root.left, list, min, max);
   if (root.key >= min && root.key <= max) {
     list.add(root.key);
   }
   getRange(root.right, list, min, max);
 
   return;
 }
 public List<Integer> getRange(TreeNode root, int min, int max) {
   // Write your solution here
   // Time: O(height)
   // Space: O(height)
   // Idea: in-order traversing->get the ascending order
 
   List<Integer> list = new LinkedList<Integer>();
   if (root == null) {
     return list;
   }
 
   getRange(root, list, min,max);
 
   return list;
 }
}
 
 
52. Search In Binary Search Tree
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
 public TreeNode search(TreeNode root, int key) {
   // Write your solution here
   if (root == null) {
     return root;
   }
 
   // NOTICE: this is BINARY SEARCH TREE (BSF)!!!
   if (root.key == key) {
     return root;
   }
 
   if (key < root.key) {
     return search(root.left, key);
   } else {
     return search(root.right, key);
   }
 }
}
 
51. Insert In Binary Search Tree
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
 public TreeNode insert(TreeNode root, int key) {
   // Write your solution here
   if (root == null) {
     root = new TreeNode(key);
     return root;
   }
 
   if (key < root.key) {
     root.left = insert(root.left, key);
   } else if (key > root.key) {
     root.right = insert(root.right, key);
   }
 
   return root;
 }
}
 


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
 private void insert2(TreeNode root, int key) {
   // Base case 1:
   if (key < root.key && root.left == null) {
     root.left = new TreeNode(key);
     return;
   } else if (key > root.key && root.right == null) {
     root.right = new TreeNode(key);
   }
 
   // Base case 2:
   if (key == root.key) {
     return;
   }
 
   // subproblem
   if (key < root.key) {
     // recursion rule
     insert2(root.left, key);
     return;
   } else {
     // recursion rule
     insert2(root.right, key);
     return;
   }
 }
 public TreeNode insert(TreeNode root, int key) {
   // Write your solution here
   // Space: O(1)
   // Time: depth of the BSF -> O(logn)
   if (root == null) {
     return new TreeNode(key);
   }
 
   insert2(root, key);
 
   return root;
 }
}
 
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
 public TreeNode insert(TreeNode root, int key) {
   // Write your solution here
   if (root == null) {
     root = new TreeNode(key);
     return root;
   }
 
   TreeNode prev = null;
   TreeNode curr = root;
   while (curr != null && curr.key != key) {
     if (key < curr.key) {
       prev = curr;
       curr = curr.left;
     } else if (key > curr.key) {
       prev = curr;
       curr = curr.right;
     }
   }
 
   // curr != null && curr.key == key
   if (curr != null) {
     return root;
   }
 
   // curr == null
   curr = new TreeNode(key);
   if (key < prev.key) {
     prev.left = curr;
   } else {
     prev.right = curr;
   }
 
   return root;
 }
}
 
 
53. Delete In Binary Search Tree
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
 private TreeNode findLeftmost(TreeNode root) {
   TreeNode prev = null;
   while (root.left != null) {
     prev = root;
     root = root.left;
   }
   prev.left = root.right;
   return root;
 }
 private TreeNode deleteHelper(TreeNode root, int key) {
   if (root == null) {
     return root;
   }
 
   // Case 1
   if (root.key == key) {
     if (root.left != null && root.right == null) {
       // Case 3: single child
       return root.left;
     } else if (root.right != null && root.left == null) {
       // Case 3
       return root.right;
     } else if (root.right != null && root.right.left == null) {
       // Case 2
       root.right.left = root.left;
       return root.right;
     } else if (root.right != null && root.right.left != null) {
       // Case 3
       TreeNode newRoot = findLeftmost(root.right);
       newRoot.left = root.left;
       newRoot.right = root.right;
       return newRoot;
     } else if (root.left == null && root.right == null) {
       return null;
     }
   }
 
   //  ****************** Must add!!! Reduce complexity ******************
   if (key < root.key) {
     root.left = deleteHelper(root.left, key);
   } else if (key > root.key) {
     root.right = deleteHelper(root.right, key);
   }
 
   return root;
 }
 
 public TreeNode deleteTree(TreeNode root, int key) {
   // No duplicate
   // Recursion
   /*  Current root is the one to be deleted
   Case 1:
             a
         b       c
               d    e
             f  g  h  i
              j
             k l
     Delete a, we need to replace with f (find the left-most node of right subtree)
     d.left = f.right;
     f.left = root.left;
     f.right = root.right;
     return f;
 
   Case 2:
             a
         b       c
                    e
                   h  i
     Remove a, replace with c
     c.left = root.left;
     return c;
 
   Case 3:
             a    or     a
         b                  c
     Remove a, replace with child
     return b or c
   */
 
   if (root == null) {
     return root;
   }
 
   TreeNode result = deleteHelper(root, key);
 
   return result;
 }
}
210. Reconstruct Binary Search Tree With Preorder Traversal
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
 public TreeNode reconstruct(int[] pre) {
   /*
   We need to check if the next number is inside the left interval or right interval
   left interval: [min, root.key - 1]
   right interval: [root.key + 1, max]
 
   // cur is a GLOBAL INDEX
   TreeNode root = new TreeNode(pre[cur]);
  
   if (cur == pre.length - 1)
     return root;
 
   if (pre[cur + 1] is inside left interval) {
     cur++;
     root.left = helper(pre, min, root.key - 1);
   }
   if (pre[cur + 1] is inside right interval) {
     cur++;
     root.right = helper(pre, root.key + 1, max);
   }
 
   return root;
 
               5
           3
         1
 
   Try: [5 3 1 4 8 11]
 
        left       right
   5  [min, 4]  [6, max]
   3  [min, 2]  [4, 4]
   1  [min, 0]  [2, 2]
   4  []
 
   Time: O(n)
   Space: O(logn)
   */
   if (pre == null || pre.length == 0) {
     return null;
   }
 
   int[] cur= new int[] {0};
   return helper(pre, cur, Integer.MIN_VALUE, Integer.MAX_VALUE);
 }
 
 private TreeNode helper(int[] pre, int[] cur, int min, int max) {
 
   // Build node for current value
   TreeNode root = new TreeNode(pre[cur[0]]);
 
   if (cur[0] == pre.length - 1) {
     return root;
   }
 
   // left interval
   if (cur[0] < pre.length - 1 && pre[cur[0] + 1] >= min && pre[cur[0] + 1] <= root.key - 1) {
     cur[0]++;
     root.left = helper(pre, cur, min, root.key - 1);
   }
 
   // right interval
   if (cur[0] < pre.length - 1 && pre[cur[0] + 1] >= root.key + 1 && pre[cur[0] + 1] <= max) {
     cur[0]++;
     root.right = helper(pre, cur, root.key + 1, max);
   }
 
   return root;
 }
}
44. Pre-order Traversal Of Binary Tree (iterative)
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
 public List<Integer> preOrder(TreeNode root) {
   // Write your solution here
   List<Integer> list = new LinkedList<Integer>();
   if (root == null) {
     return list;
   }
 
   Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
   stack.push(root);
   while (!stack.isEmpty()) {
     TreeNode curr = stack.pop();
     list.add(curr.key);
     // 后打印的要先压栈，存着折后用
     if (curr.right != null) {  // 顺序不要搞错了
       stack.push(curr.right);
     }
     if (curr.left != null) {
       stack.push(curr.left);
     }
   }
 
   return list;
 }
}
 
43. In-order Traversal Of Binary Tree (iterative)
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
 public List<Integer> inOrder(TreeNode root) {
   // Write your solution here
   // Time: O(n);
   // Space: O(height)
   List<Integer> list = new LinkedList<Integer>();
   if (root == null) {
     return list;
   }
 
   Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
   TreeNode curr = root;
   while (curr != null || !stack.isEmpty()) {
     if (curr != null) {
       stack.push(curr);
       curr = curr.left;
     } else {
       // 左孩子是null的话就pop，然后走到右孩子
       curr = stack.pop();
       list.add(curr.key);
       curr = curr.right;
     }
   }
 
   return list;
 }
}
 
45. Post-order Traversal Of Binary Tree (iterative)
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
 public List<Integer> postOrder(TreeNode root) {
   // Write your solution here
   List<Integer> list = new LinkedList<>();
   if (root == null) {
     return list;
   }
 
   // Keep track of prev and cur
   Deque<TreeNode> stack = new LinkedList<>();
   stack.push(root);
   TreeNode prev = null;
   while (!stack.isEmpty()) {
     TreeNode cur = stack.peek();
     if (prev == null || cur == prev.left || cur == prev.right) {
       if (cur.left != null) {
         stack.push(cur.left);
       } else if (cur.right != null) {
         stack.push(cur.right);
       } else { // cur == null
         stack.pop();
         list.add(cur.key);
       }
     } else if (prev == cur.right || prev == cur.left && cur.right == null) {
       stack.pop();
       list.add(cur.key);
     } else {
       stack.push(cur.right);
     }
     prev = cur;
   }
   return list;
 }
}
297. Serialize and Deserialize Binary Tree (leetcode)
/**
* Definition for a binary tree node.
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode(int x) { val = x; }
* }
*/
public class Codec {
 
   // Encodes a tree to a single string.
public String serialize(TreeNode root) {
   return dfs(root).toString(); // dfs序列化二叉树
}
// dfs序列化二叉树
StringBuilder dfs(TreeNode root){
   StringBuilder sb = new StringBuilder();  // 序列化后字符串
   if(root==null){ // 如果当前节点为空，向字符串后拼接一个null字符串
       sb.append("null,");
   }else{ // 当前节点不为空
       sb.append(root.val).append(","); // 首先拼接当前字符串数值
       sb.append(dfs(root.left)); // 将左子节点的序列化字符串拼接到当前字符串后
       sb.append(dfs(root.right)); // 将右子节点的序列化字符串拼接到当前字符串后
   }
   return sb; // 返回当前拼接后的字符串
}
// Decodes your encoded data to tree.
public TreeNode deserialize(String data) {
   // 将所有节点值以逗号分隔，并存至Queue中
   Queue<String> q=new LinkedList<>(Arrays.asList(data.split(",")));
   // dfs反序列化
   return dfs(q);
}
// dfs反序列化
TreeNode dfs(Queue<String> q){
   String val=q.poll(); // 取出一个节点数值
   // 如果当前数值为null，返回空对象
   if("null".equals(val)) return null;
   else{ // 当前节点不为空
       // 利用当前节点数值新建一个节点对象
       TreeNode node = new TreeNode(Integer.valueOf(val));
       node.left=dfs(q); // 左子节点对象为dfs后的返回值
       node.right=dfs(q); // 右子节点对象为第二次dfs后的返回值
       return node;
   }
}
}
 
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
