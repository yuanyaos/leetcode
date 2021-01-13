624. Fibonacci Number Lite
 
public class Solution {
 public int fibonacci(int K) {
   // Write your solution here
   if(K<=0)
     return 0;
   else if(K==1)
     return 1;
 
   int num1 = 0;
   int num2 = 1;
   int sum = 0;
   for(int i=1;i<K;i++){
     sum = num1 + num2;
     num1 = num2;
     num2 = sum;
   }
   return sum;
 }
}
 
13. a to the power of b
public class Solution {
 public long power(int a, int b) {
   // Use recursion, considering odd and even
   // b/2, (b/2)/2, ((b/2)/2)/2 ...
   // b%2 == 0 or b%2== 1
   // Time: O(logb), space: O(logb)
  
   // Base case
   if (b == 0) {
     return 1;
   } else if (b == 1) {
     return a;
   }
 
   // Subproblem and recursion rule
   long temp = power(a, b / 2);
   if (b % 2 == 0) {
     return temp * temp;
   } else {
     return temp * temp * a;
   }
 }
}
 
4. Selection Sort
public class Solution {
 public int[] solve(int[] array) {
   // Write your solution here
   if (array.length == 0) {
     return array;
   }
 
   for (int i = 0; i < array.length; i++) {
     for (int j = i + 1; j < array.length; j++) {
       if (array[j] < array[i]) {
         int temp = array[i];
         array[i] = array[j];
         array[j] = temp;
       }
     }
   }
   return array;
 }
}
 
 
9. Merge Sort
public class Solution {
 private int[] merge(int[] array1, int[] array2) {
   int i1 = 0;
   int i2 = 0;
   int count = 0;
   int[] mergedArray = new int[array1.length + array2.length];
 
   while (i1 < array1.length && i2 < array2.length) {
     if (array1[i1] < array2[i2]) {
       mergedArray[count] = array1[i1];
       i1++;
     } else {
       mergedArray[count] = array2[i2];
       i2++;
     }
     count++;
   }
 
   while (i1 < array1.length) {
     mergedArray[count] = array1[i1];
     i1++;
     count++;
   }
 
   while (i2 < array2.length) {
     mergedArray[count] = array2[i2];
     i2++;
     count++;
   }
 
   return mergedArray;
 }
 public int[] divide(int[] array, int left, int right) {
   // base case:
   if (left == right) {
     return new int[] {array[left]};
   }
  
   // subproblem: sort array[left, mid] and array[mid + 1, right]
   int mid = left + (right - left) / 2;
   int[] leftArray = divide(array, left, mid);
   int[] rightArray = divide(array, mid + 1, right);
 
   // recursion rule: merge two sorted arrays
   int[] mergedArray = merge(leftArray, rightArray);
 
   return mergedArray;
 }
 public int[] mergeSort(int[] array) {
   // Write your solution here
   if (array == null || array.length == 0) {
     return array;
   }
 
   return divide(array, 0, array.length - 1);
 }
}
 

10. Quick Sort
public class Solution {
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 private void quickSort(int[] array, int left, int right) {
   // Base case:
   if (left >= right) {
     return;
   }
 
   // subproblem:
   // Get random index and select the pivot
   Random rand = new Random();
   int pivotIndex = left + rand.nextInt(right - left + 1);
   swap(array, pivotIndex, right);
 
   int i = left, j = right - 1;  // array[right] is the pivot
   // 1. anything between [i, j] are unsorted
   // 2. shrink [i, j] until i > j
   while (i <= j) {
     if (array[i] < array[right]) {
       i++;
     } else {
       swap(array, i, j);
       j--;
     }
   }
 
   // Put the pivot to the right position
   swap(array, i, right);
 
   quickSort(array, left, i - 1);
   quickSort(array, i + 1, right);
 }
 public int[] quickSort(int[] array) {
   // Write your solution here
   if (array == null || array.length <= 1) {
     return array;
   }
 
   quickSort(array, 0, array.length - 1);
   return array;
 }
}
258. Move 0s To The End I
public class Solution {
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 public int[] moveZero(int[] array) {
   if (array.length == 0) {
     return array;
   }
 
   int left = 0;
   int right = array.length - 1;
   while (left <= right) {
     if (array[left] == 0) {
       swap(array, left, right);
       right--;
     } else {
       left++;
     }
   }
 
   return array;
 }
}

11. Rainbow Sort
public class Solution {
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 public int[] rainbowSort(int[] array) {
   // Write your solution here
   if (array == null || array.length <= 1) {
     return array;
   }
 
   int i = 0, j = 0, k = array.length - 1;
   // 1st type (-1): [0, i)
   // 2nd type (0) : [i, j]
   // unsorted     : [j, k], shrink this range until j > k
   // 3rd type (1) : (k, n - 1]
 
   // Move j
   while (j <= k) {
     if (array[j] == -1) {
       swap(array, i, j);
       i++;
       j++;
     } else if (array[j] == 0) {
       j++;
     } else {
       swap(array, j, k);
       k--;
     }
   }
 
   return array;
 }
}
 
C++:
class Solution {
private:
 void swap(vector<int>& array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
public:
 vector<int> rainbowSort(vector<int> array) {
   // write your solution here
   int i = 0;
   int j = 0;
   int k = array.size() - 1;
  
   while (j <= k) {
     if (array[j] == -1) {
       swap(array, i, j);
       i++;
       j++;
     } else if (array[j] == 0) {
       j++;
     } else if (array[j] == 1) {
       swap(array, j, k);
       k--;
     }
   }
 
   return array;
 }
};
 
 
258. Move 0s To The End I

public class Solution {
 public int[] moveZero(int[] array) {
   // Write your solution here
 
   // Create a new array and move all non-zero elements from the input array to the new array
   int[] newArray = new int[array.length];
   int count = 0;
   for (int i = 0; i < array.length; i++) {
     if (array[i] != 0) {
       newArray[count] = array[i];
       count++;
     }
   }
 
   return newArray;
 }
}

C++:
class Solution {
private:
 void swap(vector<int>& array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
public:
 vector<int> moveZero(vector<int> array) {
   if (array.size() <= 1) {
     return array;
   }
 
   // write your solution here
   int i = 0;
   int j = array.size() - 1;
   while (i <= j) {
     if (array[i] != 0) {
       i++;
     } else {
       swap(array, i, j);
       j--;
     }
   }
   return array;
 }
};


115. Array Deduplication I
public class Solution {
 public int[] dedup(int[] array) {
   // Write your solution here
   // 快慢指针
   // Time: O(n)
   // Space: O(n)
   if (array == null || array.length == 0) {
     return array;
   }
 
   int slow = 0;
   int fast = 1;
   while (fast < array.length) {
     if (array[slow] == array[fast]) {
       fast++;
     } else {
       slow++;
       array[slow] = array[fast];
       fast++;
     }
   }
  
   // Copy [0, slow + 1] to a new array
   int[] result = new int[slow + 1];
   for (int i = 0; i < result.length; i++) {
     result[i] = array[i];
   }
 
   return result;
 }
}
 
116. Array Deduplication II
public class Solution {
 public int[] dedup(int[] array) {
   // Write your solution here
   if (array.length <= 2) {
     return array;
   }
 
   int slow = 1;
   int fast = 2;  // do not to care about array[0] and array[1]
   while (fast < array.length) {
     if (array[fast] != array[slow - 1]) {
       slow++;
       array[slow] = array[fast];
     }
     fast++;
   }
 
   return Arrays.copyOf(array, slow + 1);
 }
}
117. Array Deduplication III
public class Solution {
 public int[] dedup(int[] array) {
   // Write your solution here
   if (array.length == 0) {
     return array;
   }
 
   int slow = 0;
   boolean flag = false;
   for (int i = 1; i < array.length; i++) {
     if (array[slow] == array[i]) {
       flag = true;
     } else if (flag == true) {
       array[slow] = array[i];
       flag = false;
     } else {
       slow++;
       array[slow] = array[i];
     }
   }
 
   return Arrays.copyOf(array, flag ? slow : slow + 1);
 }
}
 
 
public class Solution {
 public int[] dedup(int[] array) {
   // Sorted? Yes
   // Slow and fast pointer
   // if array[slow] == array[fast]
   //   keep moving fast until array[slow] != array[fast]
   //   array[slow] = array[fast]
 
   //   s           f
   // 1 5 2 3 3 3 5
 
   //   s         f
   // 1 3 2 3 3 3
 
   // 1 2 3
   if (array.length == 0) {
     return array;
   }
 
   int slow = 0;
   int fast = 1;
 
   while (fast < array.length) {
     if (array[slow] != array[fast]) {
       slow++;
       array[slow] = array[fast];
       fast++;
     } else {
       while (fast < array.length && array[slow] == array[fast]) {
         fast++;
       }
       if (fast == array.length) {
         return Arrays.copyOf(array, slow);
       }
       array[slow] = array[fast];
       fast++;
     }
   }
 
   return Arrays.copyOf(array,slow + 1);
 }
}
118. Array Deduplication IV
public class Solution {
 public int[] dedup(int[] array) {
   // Use a stack to store distinct numbers
   // When traversing array, if array[i] == stack.peek(), keep i++ until array[i] != stack.peek()
   // stack.pop()
   if (array.length == 0) {
     return null;
   }
 
   Deque<Integer> stack = new ArrayDeque<>();
   stack.push(array[0]);
   int i = 1;
   while (i < array.length) {
     Integer top = stack.peek();
     if (top == null || top != array[i]) {
       stack.push(array[i]);
       i++;
     } else {
       while (i < array.length && array[i] == stack.peek()) {
         i++;
       }
       stack.pop();
     }
   }
  
   int[] result = new int[stack.size()];
   for (int j = result.length - 1; j >= 0; j--) {
     result[j] = stack.pop();
   }
   return result;
 }
}
 

259. Move 0s To The End II
public class Solution {
 public int[] moveZero(int[] array) {
   // Write your solution here
   // Slow and fast pointers
   // Time: O(n)
   // Space: O(1)
 
   int slow = 0;
  
   for (int i = 0; i < array.length; i++) {
     if (array[i] != 0) {
       array[slow] = array[i];
       slow++;
     }
   }
 
   while (slow < array.length) {
     array[slow++] = 0;
   }
 
   return array;
 }
}
 
58. Get Keys In Binary Tree Layer By Layer Zig-Zag Order
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
 public List<Integer> zigZag(TreeNode root) {
   // Time: O(n) Space: O(n)
   // Use Deque to implement the tree traversal (also can use 2 stacks)
 
   if (root == null) {
     return new ArrayList<Integer>();
   }
 
   List<Integer> list = new ArrayList<>();
   Deque<TreeNode> deque = new LinkedList<TreeNode>();
   int layer = 0;
  
   // NEED A FLAG TO DETERMINE OFFERFIRST OR OFFERLAST
   deque.offerFirst(root);
   while (!deque.isEmpty()) {
     int size = deque.size();
     for (int i = 0; i < size; i++) {
 
       if (layer % 2 == 0) {
         TreeNode temp = deque.pollLast();
         list.add(temp.key);
         if (temp.right != null) {
           deque.offerFirst(temp.right);
         }
         if (temp.left != null) {
           deque.offerFirst(temp.left);
         }
       } else {
         TreeNode temp = deque.pollFirst();
         list.add(temp.key);
         if (temp.left != null) {
           deque.offerLast(temp.left);
         }
         if (temp.right != null) {
           deque.offerLast(temp.right);
         }
       }
     }
     layer++;
   }
   return list;
 }
}
368. Lowest Common Ancestor Binary Search Tree I
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
 public TreeNode lca(TreeNode root, int p, int q) {
   // Write your solution here
   // Notice this is a binary SEARCH tree
   // Time: O(height)
   // Space: O(height)
   int min = Math.min(p, q);
   int max = Math.max(p, q);
 
   while (root != null) {
     if (root.key < min) {
       root = root.right;
     } else if (root.key > max) {
       root = root.left;
     } else {
       return root;
     }
   }
   return null;
 }
}
 
127. Lowest Common Ancestor II
/**
* public class TreeNodeP {
*   public int key;
*   public TreeNodeP left;
*   public TreeNodeP right;
*   public TreeNodeP parent;
*   public TreeNodeP(int key, TreeNodeP parent) {
*     this.key = key;
*     this.parent = parent;
*   }
* }
*/
public class Solution {
 private int findDepth(TreeNodeP aNode) {
   int depth = 0;
   while (aNode.parent != null) {
     depth++;
     aNode = aNode.parent;
   }
   return depth;
 }
 
 private TreeNodeP findLCA(TreeNodeP longNode, TreeNodeP shortNode, int diff) {
   while (diff > 0) {
     longNode = longNode.parent;
     diff--;
   }
 
   while (longNode != shortNode) {
     longNode = longNode.parent;
     shortNode = shortNode.parent;
   }
   return longNode;
 }
 
 public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
   // Write your solution here.
   // 这题没有给root，而是直接给了两个target nodes
 
   // 假设有一个common ancestor在那，但是one和two到CA的距离可能不一样，所以得让距离长的那个node
   // 往上爬，直到与另一个node有相同的到CA的距离，然后再一起往上爬
 
   // 1. 找到one和two的depth
   int depthOfOne = findDepth(one);
   int depthOfTwo = findDepth(two);
 
   // 2. 让长的那个node往上爬直到与短的那个node有相同的depth
   // 3. 一起往上爬直到遇到LCA
   if (depthOfOne > depthOfTwo) {
     return findLCA(one, two, depthOfOne - depthOfTwo);
   } else {
     return findLCA(two, one, depthOfTwo - depthOfOne);
   }
 }
}
 
119. Largest And Smallest
public class Solution {
 public int[] largestAndSmallest(int[] array) {
   // Write your solution here
   // 最简单的方法是从头过一遍，但是也可以从两边向内走，left part是大的，right part是小的
   // 遇到left<right，则交换，所以比较n/2
   // 然后再从left和right part中分别找到最大和最小值，总共比较n次
   // 所以总共比较(3n/2)次
   // Naive的方法比较2n次
 
   // 1. Divide array into 2 parts
   // n = 3;
   // 3 / 2 = 1;
   int n = array.length;
   for (int i = 0; i < n / 2; i++) {
     if (array[n - 1 - i] > array[i]) {
       swap(array, i, n - 1 - i);
     }
   }
 
   return new int[] {findLargest(array, 0, (n - 1) / 2), findSmallest(array, n / 2, n - 1)};
 }
 
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 
 private int findLargest(int[] array, int left, int right) {
      int max = array[left];
      for (int i = 0; i <= right; i++) {
        if (array[i] > max) {
          max = array[i];
        }
      }
      return max;
    }
 
    private int findSmallest(int[] array, int left, int right) {
      int min = array[left];
      for (int i = 0; i <= right; i++) {
        if (array[i] < min) {
          min = array[i];
        }
      }
      return min;
    }
}
120. Largest And Second Largest
public class Solution {
 /*
     8  2  1  4  5  7  2  9
      8[2]  4[1]  7[5]  9[2]      两两比较中选择大的那个，小的那个存在内存中
        8[2 4]      9[7 5]        4一定比4后面的[...]大，4又比8小，所以舍弃4后面的[...]
            9[7 5 8]              8一定比[2, 4]大，8又比9小，所以把8后面的[...]舍弃掉
   每次都把比小的那个数字更小的数字排除掉
   second largest就在最大值附带的linked list([...])里面找
   Time: O(n) + O(logn) (largest + second largest), 对于2nd largest每次都能舍弃一般的数字，所以是logn
   Space: O(n)
 */
 
 private class Elem {
   int value;
   List<Integer> smaller;
   Elem(int value) {
     this.value = value;
     smaller = new ArrayList<Integer>();
   }
 }
 
 private void compare(Elem[] elem, int length) {
   for (int i = 0; i < length / 2; i++) {
     if (elem[i].value < elem[length - 1 - i].value) {
       Elem temp = elem[i];
       elem[i] = elem[length - 1 - i];
       elem[length - 1 - i] = temp;
     }
     elem[i].smaller.add(elem[length - 1 - i].value);
   }
 }
 
 private Elem[] buildElemArray(int[] array) {
   Elem[] elem = new Elem[array.length];
   for (int i = 0; i < array.length; i++) {
     elem[i] = new Elem(array[i]);
   }
   return elem;
 }
 
 private int getLargest(List<Integer> list) {
   int max = Integer.MIN_VALUE;
   for (Integer l : list) {
     if (l > max) {
       max = l;
     }
   }
   return max;
 }
 
 public int[] largestAndSecond(int[] array) {
   Elem[] elem = buildElemArray(array);
   int length = array.length;
   while (length > 1) {
     compare(elem, length);
     length = (length + 1) / 2;
   }
 
   // Traverse the linked list in elem[0]
   int secondLargest = getLargest(elem[0].smaller);
 
   return new int[] {elem[0].value, secondLargest};
 }
}
504. Closest Number In Binary Search Tree II
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
 private void inOrderHelper(Queue<Integer> queue, TreeNode root, double target, int k) {
   if (root == null) {
     return;
   }
 
   inOrderHelper(queue, root.left, target, k);
 
   if (queue.size() < k) {
     queue.offer(root.key);
   }
   else if (Math.abs(queue.peek() - target) < Math.abs(target - root.key)) {  // queue size == k
     return;
   } else {
     queue.poll();
     queue.offer(root.key);
   }
 
   inOrderHelper(queue, root.right, target, k);
 }
 
 public int[] closestKValues(TreeNode root, double target, int k) {
   // Repeated number?
   // Solution 1: 先in-order排好序(Time: O(n), space: O(n)); 2: binary search+两边开花 (O(logn+k))
   // Solution 2: 在in-order traverse的过程中用一个size为k的queue记录k个最靠近target的数字
   // 1 3 6 7 8 10 15 18     target=9, k=3
   // - - -
   // Judge if rightmost is closer to the target than the left most, if yes, offer to queue, else return
   // Time: O(n), space: O(height + k)
   Queue<Integer> queue = new LinkedList<>();
   inOrderHelper(queue, root, target, k);
 
   int[] result = new int[queue.size()];
    for (int i = 0; i < result.length; i++) {
     result[i] = queue.poll();
   }
 
   return result;
 }
}
129. Lowest Common Ancestor IV
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
 private TreeNode helper(TreeNode root, Set<TreeNode> set) {
   if (root == null) {
     return root;
   }
 
   // 1. 当前root是ancester
   if (set.contains(root)) {
     return root;
   }
 
   TreeNode left = helper(root.left, set);
   TreeNode right = helper(root.right, set);
 
   // 2.
   if (left != null && right != null) {
     return root;
   }
 
   // 3.
   return left != null ? left : right;
 }
 public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
   // 每一个nodes都保证在tree里面
   // Base case: null
   // 1. 当前root在node list里面
   // 2. 左右subtree都包含list里面的值
   // 3. left和right只有一个不是null，说明只有一个subtree里面有node，还没找到ancester，继续往上找
 
   Set<TreeNode> set = new HashSet<>(nodes);
  
   return helper(root, set);
 }
}
647. Lowest Common Ancestor V
/**
* public class KnaryTreeNode {
*     int key;
*     List<KnaryTreeNode> children;
*     public KnaryTreeNode(int key) {
*         this.key = key;
*         this.children = new ArrayList<>();
*     }
* }
*/
public class Solution {
 private KnaryTreeNode helper(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
   // Base case 1
   if (root == null) {
     return root;
   }
 
   // Base case 2
   if (root == a || root == b) {
     return root;
   }
 
   // Recursion rule:
   // 两种情况，1. 单个subtree有，2. targets都在subtrees中
   KnaryTreeNode temp = null;
   for (KnaryTreeNode s : root.children) {
     KnaryTreeNode curr = helper(s, a, b);
     if (curr != null) {
       if (temp == null) {
         temp = curr;
       } else {
         // 第二次发现非null返回值，说明当前root就是ancestor
         return root;
       }
     }
   }
 
   return temp;
 }
 
 public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
   // 查看subtree有没有包含a或者b，如果都left包含一个right包含一个则说明当前的root是ancestor
   // 如果either one包含一个则return包含的那个subtree返回的值
   // 两个base case，一是null，二是root.key是a或者b
   // Time: O(n), space: O(height)
  
   return helper(root, a, b);
 }
}
648. Lowest Common Ancestor VI
/**
* public class KnaryTreeNode {
*     int key;
*     List<KnaryTreeNode> children;
*     public KnaryTreeNode(int key) {
*         this.key = key;
*         this.children = new ArrayList<>();
*     }
* }
*/
public class Solution {
 private KnaryTreeNode helper(KnaryTreeNode root, Set<KnaryTreeNode> set) {
   // Base case
   if (root == null) {
     return root;
   }
   if (set.contains(root)) {
     return root;
   }
 
   // Recursion rule
   KnaryTreeNode onlyOne = null;
   for (KnaryTreeNode child : root.children) {
     KnaryTreeNode temp = helper(child, set);
     if (temp == null) {
       continue;
     } else {
       if (onlyOne == null) {
         onlyOne = temp;
       } else {
         return root;
       }
     }
   }
 
   return onlyOne;
 }
 public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
   // Nodes are gauranteed to be in the tree
   // Base case: root == null or root == nodes
   // Recursion rule:
   // 1. 大于1个subtree返回not null，则返回root
   // 2. 只有一个subtree返回not null，则返回这个subtree的返回值
  
   // Time: O(n), space: O(height)
   Set<KnaryTreeNode> set = new HashSet<KnaryTreeNode>(nodes);
   return helper(root, set);
 }
}
