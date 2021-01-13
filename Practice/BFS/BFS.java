HashMap
import java.util.Arrays;

public class MyHashMap<K, V> {
	public static class Node<K, V> {
		final K key;
		V value;
		Node<K, V> next;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	private static final int CAPACITY = 16;
	private static final float LOAD_FACTOR = 0.75f;
	
	private Node<K, V>[] array;
	private int size;  // actual key-value pairs in the HashMap
	private float loadFactor;
	
	// MyHashMap constructor
	public MyHashMap(int cap, float loadFactor) {
		if (cap <= 0) {
			throw new IllegalArgumentException("HashMap capacity should be >0");
		}
		this.array = (Node<K, V>[]) (new Node[cap]);  // casting to Node<K, V>[]
		this.size = 0;
		this.loadFactor = loadFactor;
	}
	
	// Default constructor
	public MyHashMap() {
		this(CAPACITY, LOAD_FACTOR);
	}
	
	// *************** HashMap private helper functions ***************
	private boolean equalsKey(K k1, K k2) {
		if (k1 == null && k2 == null) {
			return true;
		} else if (k1 == null ||  k2 == null) {
			return false;
		}
		return k1.equals(k2);
    }
	
	private boolean equalsValue(V v1, V v2) {
		if (v1 == null && v2 == null) {
			return true;
		} else if (v1 == null ||  v2 == null) {
			return false;
		}
		return v1.equals(v2);
	}
	
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		// Guarantee hash code is not negative
		return key.hashCode() & 0x7FFFFFFF;
	}
	
	private int getIndex(K key) {
		// get hashcode
		// hashcode % array.length
		return hash(key) % array.length;
	}
	
	private boolean needRehashing() {
		// 0.0f 把size转换为float
		float ratio = (size + 0.0f) / array.length;
		return ratio >= loadFactor;
	}
	
	private void rehashing() {
		Node<K, V>[] oldArray = array;
		array = (Node<K, V>[]) (new Node[array.length * 2]);
		for (Node<K, V> node : oldArray) {
			while (node != null) {
				Node<K, V> next = node.next;
				int index = getIndex(node.key);
				node.next = array[index];
				array[index] = node;
				node = next;
			}
		}
	}
	
	// *************** HashMap public APIs ***************
	// 1. size()
	// 2. isEmpty()
	// 3. clear()
	// 4. boolean containsValue(V value) // loop over array
	// 5. boolean containsKey(K key) // 先getIndex
	// 6. put(K key, V value)
	// 7. V get(K key)
	// 8. V remove(K key)
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
	
	public void clear() {
		Arrays.fill(this.array, null);
		size = 0;
	}
	
	public boolean containsValue(V value) {
		// O(n)
		if (isEmpty()) {
			return false;
		}
		
		// 遍历所有node
		for (Node<K, V> node : array) {
			// 每一个node都是一个linked list
			while (node != null) {
				if (equalsValue(node.value, value)) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}
	
	public boolean containsKey(K key) {
		// 先找到key在array中对应的index, 然后再在array[index]这个linkedlist
		// 中遍历寻找相同的key
		// 不同的hash code可能对应相同的index，比如 1 % 10 = 1, 11 % 10 = 1
		
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.key, key)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	public V get(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.key, key)) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}
	
	public V put(K key, V value) {
		// return old value
		// 如果array中有key，则继续添加到linkedlist后面
		// 如果array中没有key，则直接创建一个新的Node
		int index = getIndex(key);
		Node<K, V> first = array[index];
		Node<K, V> node = first;
		while (node != null) {
			if (equalsKey(node.key, key)) {
				// find the key, update the value
				V old = node.value;
				node.value = value;
				return old;
			}
			node = node.next;
		}
		
		// 本来就没有这个key或者linkedlist中找不到这个key
		// 后来加入的Node有更高的可能性在之后被访问，所以插到linkedlist前面
		Node<K, V> newNode = new Node(key, node);
		newNode.next = first;
		array[index] = newNode;
		
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}
	
	public V remove(K key) {
		// return removed value
		// 跟delete in linkedlist差不多
		int index = getIndex(key);
		Node<K, V> node = array[index];
		Node<K, V> prev = null;
		while (node != null) {
			if (equalsKey(node.key, key)) {
				if (prev == null) {
					array[index] = node.next;
				} else {
					prev.next = node.next;
				}
				size--;
				return node.value;
			}
			prev = node;
			node = node.next;
		}
		return null;
	}
}
25. K Smallest In Unsorted Array
MAX HEAP
public class Solution {
 private class MyComparator implements Comparator<Integer>{
    public int compare(Integer x, Integer y) {
      if (x.equals(y)) {
        return 0;
      }
      return x > y ? -1 : 1;
    }
  }
 
 public int[] kSmallest(int[] array, int k) {
   // Write your solution here
   // Assumption: k >= 0 && k <= n
 
   // Max heap
   if (array == null || k == 0) {
     return new int[0];
   }
 
   // Time:
   // Build heap with size k: O(k)
   // For the rest of n-k elements, insert to the heap, so (n-k)*log(k)
 
   PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new MyComparator());
  
   // Build max heap
   for (int i = 0; i < array.length; i++) {
     if (i < k) {
       maxHeap.offer(array[i]);
     } else if (array[i] < maxHeap.peek()) {
       maxHeap.poll();
       maxHeap.offer(array[i]);
     }
   }
  
   int[] results = new int[k];
   for (int i = k-1; i >= 0; i--) {
     results[i] = maxHeap.poll();
   }
 
   return results;
 }
}
 
MIN HEAP
public class Solution {
 private class MyComparator implements Comparator<Integer> {
   public int compare(Integer s1, Integer s2) {
     if (s1.equals(s2)) {
       return 0;
     }
 
     return s1 > s2 ? 1 : -1;
   }
 }
 
 public int[] kSmallest(int[] array, int k) {
   // Write your solution here
   if (array == null || k == 0) {
     return new int[0];
   }
 
   // MIN HEAP
   // Space: O(n)
   // Time: 1. heapify all n element O(n)
   // 2. poll k elements out of heap O(k*logn)
   // Total: O(n + klogn)
 
   PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k, new MyComparator());
   for (int i = 0; i < array.length; i++) {
     minHeap.offer(array[i]);
   }
 
   int[] results = new int[k];
   for (int i = 0; i < k; i++) {
     results[i] = minHeap.poll();
   }
 
   return results;
 }
}

Quick Select
public class Solution {
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 private int partition(int[] array, int left, int right) {
   int pivot = array[right];
   int i = left, j = right - 1;
   while (i <= j) {
     if (array[i] < pivot) {
       i++;
     } else {  // array[i] >= pivot
       swap(array, i, j);
       j--;
     }
   }
   swap(array, i, right);
 
   return i;
 }
 private void quickSelect(int[] array, int left, int right, int k) {
 
   int mid = partition(array, left, right);
 
   if (mid == k) {
     return;
   } else if (mid > k) {
     quickSelect(array, left, mid - 1, k);
   } else {
     quickSelect(array, mid + 1, right, k);
   }
 
   return;
 }
 
 public int[] kSmallest(int[] array, int k) {
   // Write your solution here
   if (array == null || k == 0) {
     return new int[0];
   }
 
   // Partition the array into 2 parts using pivot
   // if mid > k, do the same thing for the left part
   // else do the same thing for the right part
   // Time: O(n+n/2+n/4+...+1) + O(klogk) = O(n + klogk), Worst case: O(n^2)
   // Space: O(logn)
 
   // 注意是 k - 1 !!!!!! array从0开始算起
   quickSelect(array, 0, array.length - 1, k - 1);
 
   int[] results = new int[k];
   for (int i = 0; i < k; i++) {
     results[i] = array[i];
   }
 
   Arrays.sort(results);
 
   return results;
 
 }
}
 

57. Get Keys In Binary Tree Layer By Layer
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
 public List<List<Integer>> layerByLayer(TreeNode root) {
   // Write your solution here
 
   // Level by level --> use the BSF and queue to traverse the queue
   // Nodes in each level are saved in the List.
   // Because we traverse each node, the tims compexity is O(n)
   // 1. We need a queue, the maximum size of queue is the width of the tree, O(n), and it is in heap
   // 2. When in the deepest level, the list length is n. It is also in heap.
   // 3. Combined, the space complexity is O(n)
 
   if (root == null) {
     List<List<Integer>> result = new ArrayList();
     return result;
   }
 
   // 1. Create a queue and a list of list
   List<List<Integer>> result = new ArrayList();
   Queue<TreeNode> queue = new LinkedList<>();
   queue.offer(root);
 
   while (!queue.isEmpty()) {
     int queueSize = queue.size();
    
     // Create list for each level
     List<Integer> list = new ArrayList<>();
     for (int i = 0; i < queueSize; i++) {
       TreeNode popedNode = queue.poll();
       list.add(popedNode.key);
       // push the next level nodes to queue
       if (popedNode.left != null) {
         queue.offer(popedNode.left);
       }
       if (popedNode.right != null) {
         queue.offer(popedNode.right);
       }
     }
     result.add(list);
   }
 
   return result;
 }
}
 
47. Check If Binary Tree Is Completed
 
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
 public boolean isCompleted(TreeNode root) {
   // Write your solution here
   // [1,2,4,3,  #  ,  3,     5]
    //          flag   non-null
    // BSF遍历，出现 flag + non-null的  情况就返回false
    // 在往queue里面填的时候就要判断flag
 
   // Space: stack O(1), heap O(width of the tree) = O(n)
   // Time: O(n)
    
      if (root == null) {
        return true;
      }
 
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      boolean endLoop = false;
      q.offer(root);
      while (!q.isEmpty()) {
          TreeNode temp = q.poll();
  
          if (temp.left != null) {
            if (endLoop == true) {
              return false;
            }
            q.offer(temp.left);
          } else {
            endLoop = true;
          }
         
          if (temp.right != null) {
            if (endLoop == true) {
 	         return false;
 	     }
            q.offer(temp.right);
          } else {
            endLoop = true;
          }
      }
     
      return true;
 }
}
 
56. Bipartite
/**
* public class GraphNode {
*   public int key;
*   public List<GraphNode> neighbors;
*   public GraphNode(int key) {
*     this.key = key;
*     this.neighbors = new ArrayList<GraphNode>();
*   }
* }
*/
//        A1
//    B1  B2  B3  B4
//  A2  A3  A4  A5  A6  A7
// 每一层必须label不相同，需要一个HashMap存node和label
public class Solution {
 public boolean isBipartite(List<GraphNode> graph) {
   // write your solution here
   // Input: adjacent list
 
   //                 1             group 0
   //                / \
   //               2   3           group 1 (not visited, label 1)
   //              / \   /\
   //             4   5 6  7        group 0 (if visited and it is group 1, return false)
 
   // Time: O(n+n) = O(n)
   // Space: O(width)
   HashMap<GraphNode, Integer> visited = new HashMap<>();
 
   for (GraphNode node : graph) {  // O(n)
   // If starting node has been visited, this means the route has been traveled, so return true
     if (visited.containsKey(node)) {
       continue;
     }
     if (!search(node, visited)) {
       return false;               // each BSF: O(n)
     }
   }
   return true;
 }
 
 private boolean search(GraphNode node, HashMap<GraphNode, Integer> visited) {
 
   // 1. Create a queue to do the BFS
   Queue<GraphNode> queue = new ArrayDeque<>();
 
   // 2. Push starting node into visited, label as group 0
   visited.put(node, 0);
 
   // 3. Generate starting node in queue
   queue.offer(node);
 
   // 3. Do BSF
   while (!queue.isEmpty()) {
     // Expand
     GraphNode newNode = queue.poll();
     int oldGroup = visited.get(newNode);
     int newGroup = oldGroup == 0 ? 1 : 0;  // inverse here
     // Generate its neighbors
     for (GraphNode neighborNode : newNode.neighbors) {
       if (!visited.containsKey(neighborNode)) {
         // 1) If not visited, push to the visited hash map
         // 2) Push into queue
         visited.put(neighborNode, newGroup);
         queue.offer(neighborNode);
       } else {  // This neighbor has been visited before (do not do the expansion)
         // check the group number to see if its group number == parent group number
         int visitedGroup = visited.get(neighborNode);
         if (visitedGroup == oldGroup) {
           return false;
         }
       }
     }
   }
   return true;
 }
}
 
26. Kth Smallest Number In Sorted Matrix
public class Solution {
 private class Cell {
 	 int row;
 	 int col;
 	 int value;
 	 Cell (int row, int col, int value) {
   	 this.row = row;
   	 this.col = col;
   	 this.value = value;
 	 }
    }
 public int kthSmallest(int[][] matrix, int k) {
   // Write your solution here
   // The starting point is matrix[0][0]
   // Assumption: matrix is not null and k > 0 && k <= N*M
 
   // Use a min heap to keep the smallest element
   // loop k times, then the smallest element in the heap is the kth smallest element
   // Time: O(k*logk)
   // Space: O(row)
   // 在minHeap中塞入所有的children，minHeap的作用是找到children中外层contour的点中的最小值
 
 
   int rows = matrix.length;
   int cols = matrix[0].length;
   boolean[][] visited = new boolean[rows][cols];
   PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(new Comparator<Cell>() {
     public int compare(Cell o1, Cell o2) {
       if (o1.value == o2.value) {
         return 0;
       }
       return o1.value < o2.value ? -1 : 1;
     }
   });
 
   // push matrix[0][0] into minHeap
   minHeap.offer(new Cell(0, 0, matrix[0][0]));
   visited[0][0] = true;
   // 注意是k-1个loop！！！！！！！！！！！！！！！
   // 要不然会把k的孩子push到minheap里面
   for (int i = 0; i < k - 1; i++) {
     Cell cur = minHeap.poll();
     // Expand cur, only two neighbors
    
     if (cur.row + 1 < rows && !visited[cur.row + 1][cur.col]) {
       minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
       visited[cur.row + 1][cur.col] = true;
     }
     if (cur.col + 1 < cols && !visited[cur.row][cur.col + 1]) {
       minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
       visited[cur.row][cur.col + 1] = true;
     }
   }
   return minHeap.peek().value;
 }
}
 
38. Search a 2D Matrix II
public class Solution {
   static class Cell {
       int x;
       int y;
       int value;
       Cell(int x, int y, int value) {
           this.x = x;
           this.y = y;
           this.value = value;
       }
   }
  
   /**
    * @param matrix: A list of lists of integers
    * @param target: An integer you want to search in matrix
    * @return: An integer indicate the total occurrence of target in the given matrix
    */
   public int searchMatrix(int[][] matrix, int target) {
       if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
           return 0;
       }
      
       // BFS, stop when next is > target
       Queue<Cell> queue = new ArrayDeque<>();
       // downward, right
       int[][] directions = {{1, 0}, {0, 1}};
      
       // counts of target
       int result = 0;
      
       queue.offer(new Cell(0, 0, matrix[0][0]));
       while(!queue.isEmpty()) {
           Cell cur = queue.poll();
           if (cur.value == target) {
               result++;
           }
           for (int[] dir : directions) {
               int nextX = cur.x + dir[0];
               int nextY = cur.y + dir[1];
               if (nextX < matrix.length && nextY < matrix[0].length && matrix[nextX][nextY] <= target && matrix[nextX][nextY] != Integer.MIN_VALUE) {
                   queue.offer(new Cell(nextX, nextY, matrix[nextX][nextY]));
                   matrix[nextX][nextY] = Integer.MIN_VALUE;
               }
           }
       }
      
       return  result;
   }
}
