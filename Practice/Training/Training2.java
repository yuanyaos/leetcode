131. Deep Copy Linked List With Random Pointer
/**
* class RandomListNode {
*   public int value;
*   public RandomListNode next;
*   public RandomListNode random;
*   public RandomListNode(int value) {
*     this.value = value;
*   }
* }
*/
public class Solution {
 public RandomListNode copy(RandomListNode head) {
   // 用HashMap的方法来避免产生多余的node
   // HashMap<原list上的node，新的list上的node>
   // Time: O(n), space: O(n)
 
   if (head == null) {
     return head;
   }
 
   //      new              old
   Map<RandomListNode, RandomListNode> map = new HashMap<>();
 
   // Dummy node
   RandomListNode dummy = new RandomListNode(0);
   RandomListNode curr = dummy;
   while (head != null) {
     // 1. head 的部分
     if (!map.containsKey(head)) {
       map.put(head, new RandomListNode(head.value));
     }
     // 需要注意这里，不要直接用curr
     curr.next = map.get(head);
 
     // 2. head.random 的部分
     if (head.random != null) {
       if (!map.containsKey(head.random)) {
         map.put(head.random, new RandomListNode(head.random.value));
       }
       curr.next.random = map.get(head.random);
     }
 
     // 3. Move forward
     head = head.next;
     curr = curr.next;
   }
 
   return dummy.next;
 }
}
132. Deep Copy Undirected Graph
/*
* class GraphNode {
*   public int key;
*   public List<GraphNode> neighbors;
*   public GraphNode(int key) {
*     this.key = key;
*     this.neighbors = new ArrayList<GraphNode>();
*   }
* }
*/
public class Solution {
 public List<GraphNode> copy(List<GraphNode> graph) {
   // 也可以用DSF做
   // 依旧使用HashMap的方法避免重复创造node
   // 因为是graph，用BSF traverse，所以需要一个queue
   // Time: O(n), space: O(n)
 
   Map<GraphNode, GraphNode> map = new HashMap<>();
   Queue<GraphNode> queue = new LinkedList<>();
   List<GraphNode> result = new ArrayList<>();
 
   for (GraphNode node : graph) {
     map.put(node, new GraphNode(node.key));
     queue.offer(node);
     result.add(map.get(node));
   }
 
   while (!queue.isEmpty()) {
     int size = queue.size();
     for (int i = 0; i < size; i++) {
       GraphNode cur = queue.poll();
       GraphNode newCur = map.get(cur);
       for (GraphNode child : cur.neighbors) {
         if (!map.containsKey(child)) {
           map.put(child, new GraphNode(child.key));
           queue.offer(child);
         }
         // connect in the copied graph
         newCur.neighbors.add(map.get(child));
       }
     }
   }
   return result;
 }
}
 
 
public class Solution {
 private GraphNode helper(GraphNode head, Queue<GraphNode> queue, Map<GraphNode, GraphNode> map) {
    queue.offer(head);
    GraphNode newHead = new GraphNode(head.key);
    map.put(head, newHead);
    while (!queue.isEmpty()) {
      GraphNode oldCur = queue.poll();
      GraphNode newCur = map.get(oldCur);
      for (GraphNode next : oldCur.neighbors) {
        if (!map.containsKey(next)) {
          map.put(next, new GraphNode(next.key));
          queue.offer(next);
        }
       // Making connection for the new copied node
        newCur.neighbors.add(map.get(next));
      }
    }
    return newHead;
  }
 
 public List<GraphNode> copy(List<GraphNode> graph) {
   // 依旧使用HashMap的方法避免重复创造node
   // 因为是graph，用BSF traverse，所以需要一个queue
   // Time: O(n), space: O(n)
 
   List<GraphNode> copyGraph = new ArrayList<>();
    // Use map to avoid repeated copy
    HashMap<GraphNode, GraphNode> map = new HashMap<>();
    Queue<GraphNode> queue = new ArrayDeque<>();
 
    for (GraphNode node : graph) {
      copyGraph.add(helper(node, queue, map));
    }
    return copyGraph;
 }
}

135. Closest Number In Binary Search Tree
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
 public int closest(TreeNode root, int target) {
   // Assume: root is not null and no duplicate keys in BST
   // Try 5.1 in the example
   // 在往下找的过程中，当找到一个new node比之前的node更小的时候，那就需要把之前的node更新为当前的node
   // Time: O(height), space: O(1)
 
   int result = root.key;
   while (root != null) {
     if (Math.abs(root.key - target) < Math.abs(result - target)) {
       result = root.key;
     }
 
     if (target < root.key) {
       root = root.left;
     } else {
       root = root.right;
     }
   }
   return result;
 }
}
136. Largest Number Smaller In Binary Search Tree
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
 public int largestSmaller(TreeNode root, int target) {
   // 在BST中不断的找比target小的数，然后边找边update结果
   // Time and space: O(height)
   int min = Integer.MIN_VALUE;
   while (root != null) {
     // 不能用 target - root.key < target - min, 会溢出！！！！！！
     if (target > root.key && root.key > min) {
       min = root.key;
     }
     if (root.key >= target) {
       root = root.left;
     } else {
       root = root.right;
     }
   }
   return min;
 }
}
 
// Test: 1
// Test: 10
180. 2 Sum
public class Solution {
 public boolean existSum(int[] array, int target) {
   // 用HashSet，Naive: 先扫一遍把整个array加到set中，more efficient：边扫边加
 
   Set<Integer> set = new HashSet<>();
   for (int i = 0; i < array.length; i++) {
     if (set.contains(target - array[i])) {
       return true;
     }
     set.add(array[i]);
   }
   return false;
 }
}
181. 2 Sum All Pair I
public class Solution {
 public List<List<Integer>> allPairs(int[] array, int target) {
   // 跟普通的2 sum一样，分为两部分：target - array[i] 和 array[i]
   // 重复不多的话O(n)，worst case有可能O(n^2)
   // Space: O(n)
   // 在Map或Set里面找target - array[i]，然后不断的加入array[i]
   // 1,3,2,4,2,5,3,4,4,4,4  target = 7
 
   // 1,3,2,4,2,5,3,
 
   // 需要建立一个Map存数值和对应的index
   Map<Integer, List<Integer>> map = new HashMap<>();
 
   // 输出结果
   List<List<Integer>> result = new ArrayList<List<Integer>>();
 
   for (int i = 0; i < array.length; i++) {
     // 第一部分：target - array[i]
     List<Integer> index = map.get(target - array[i]);
     if (index != null) {
       for (int j : index) {
         // ******** 虽然j有可能重复，但是i每次都不一样 ********
         List<Integer> list = new ArrayList<>();
         list.add(i);
         list.add(j);
         result.add(list);
       }
     }
 
     // 第二部分：array[i]，实时更新部分
     if (!map.containsKey(array[i])) {
       map.put(array[i], new ArrayList<Integer>());
     }
     map.get(array[i]).add(i);
   }
   return result;
 }
}
182. 2 Sum All Pair II
public class Solution {
 private List<Integer> getList(int a, int b) {
   List<Integer> list = new ArrayList<>();
   list.add(a);
   list.add(b);
   return list;
 }
 
 public List<List<Integer>> allPairs(int[] array, int target) {
   // 2 1 3 2 4 3 4
   //
   // if target - array[i] is in the set?
   //   if array[i] is in the set?
   //     continue;
   //   else
   //     put (array[i], target - array[i]) into result
   // 
   //  put array[i] in the set
   //
   // Time: O(n), space: O(n)
 
   // Correction：有可能出现 2*a=target的情况，所以需要用一个HashMap来计数
   Map<Integer, Integer> map = new HashMap<>();
   List<List<Integer>> result = new ArrayList<List<Integer>>();
   for (int i = 0; i < array.length; i++) {
     int num = target - array[i];
     if (map.containsKey(num)) {
       int count = map.get(num);
       if (num == array[i] && count == 1) {
         result.add(getList(num, num));
         map.put(array[i], 2);
       } else if (num != array[i] && !map.containsKey(array[i])) {
         result.add(getList(num, array[i]));
         map.put(array[i], 1);
       }
     } else {
       map.put(array[i], 1);
     }
   }
   return result;
 }
}
185. 2 Sum 2 Arrays
public class Solution {
 public Set<Integer> buildSet(int[] array) {
   Set<Integer> set = new HashSet<>();
   for (int i = 0; i < array.length; i++) {
     set.add(array[i]);
   }
   return set;
 }
 public boolean existSum(int[] a, int[] b, int target) {
   // null or empty? no
   // push shorter array into a hashset and traverse another array
   int[] shortArray = a.length < b.length ? a : b;
   int[] longArray = a.length >= b.length ? a : b;
   Set<Integer> set = buildSet(shortArray);
   for (int i = 0; i < longArray.length; i++) {
     int another = target - longArray[i];
     if (set.contains(another)) {
       return true;
     }
   }
   return false;
 }
}
186. 3 Sum
public class Solution {
 private void twoSum(List<List<Integer>> result, int[] array, int start, int end, int target) {
   int first = start - 1;
   while (start < end) {
     if (array[start] + array[end] == target) {
       List<Integer> list = new ArrayList<>();
       list.add(array[first]);  // 第一个
       list.add(array[start]);
       list.add(array[end]);
       result.add(list);
 
       // 特别要注意这里，避免重复运算
       start++;
       while (start < end && array[start] == array[start - 1]) {
         start++;
       }
     } else if (array[start] + array[end] < target) {
       start++;
     } else {
       end--;
     }
   }
 }
 
 public List<List<Integer>> allTriples(int[] array, int target) {
   // 第一种方法是可以采用2 sum的方法
   // for (int i = 0; i < array.length; i++) {
   //   给定array[i], 对右边剩下的array使用2 sum，新的target是target - array[i]
   // }
   // Time: O(n^2), space: O(n)
 
   // 另一种方法是先sort，再从两端往里搜，Time: O(n^2), space: O(1)
   // 两种方法时间复杂度是一样的，但是后者空间复杂度小
   List<List<Integer>> result = new ArrayList<List<Integer>>();
 
   Arrays.sort(array);
  
   for (int i = 0; i < array.length - 2; i++) {
     // 出现 1, 2, 2, 2, 3 的情况需要避免重复寻找以2开头的 triples
     if (i > 0 && array[i] == array[i - 1]) {
       continue;
     }
 
     twoSum(result, array, i + 1, array.length - 1, target - array[i]);
   }
   return result;
 }
}
188. 4 Sum
public class Solution {
 private class Pair {
   int left;
   int right;
 
   Pair (int left, int right) {
     this.left = left;
     this.right = right;
   }
 }
 public boolean exist(int[] array, int target) {
   // 有4个数字，index是：a < b < c < d
   // 跟 2 sum 非常像，也是把array分为 x 和 target - x
   // 跟 2 sum 不同的是x不是当前的array[i]，而是 sum of a pair, 并且 b < c 来保证
   // 左边的 pair 的index小于右边的 pair index
   // Time: O(n^2), space: O(n^2)
   // 使用HashMap存储 sum of pair 和对应的 indeices of pairs
   Map<Integer, Pair> map = new HashMap<>();
 
   for (int i = 1; i < array.length; i++) {
     for (int j = 0; j < i; j++) {
       // i 是 d，j 是 c
       // 第一部分（跟 2 sum 非常像）, target - pairSum
       int pairSum = array[i] + array[j];
       if (map.containsKey(target - pairSum) && map.get(target - pairSum).right < j) {
         return true;
       }
 
       // 第二部分
       if (!map.containsKey(pairSum)) {
         map.put(pairSum, new Pair(j, i));
       }
     }
   }
   return false;
 }
}
