204. Maximum Values Of Size K Sliding Windows
public class Solution {
 public List<Integer> maxWindows(int[] array, int k) {
   // 用Deque来记录index
   /*
  
   k = 3
index   0  1  2  3  4  5  6  7
value   1  2  5  4  3  2  6  0
   i = 0      [0]
   i = 1      [1]
   i = 2      [2]
   i = 3      [2 3]  (i-k = 3-3 = 0)
   i = 4      [2 3 4]  (i-k = 4-3 = 1)
   i = 5      [2 3 4 5] (i-k = 5-3 = 2) -> [3 4 5]
   i = 6      [3 4 5] -> [] -> [6]  从右边移除所有小于新加入的array[i]的数，因为array[i]才是当前window里最大的
   i = 7      [6 7]
 
   Time: O(n), every number will just go in and out of the deque once
   Space: O(k)
   */
 
   List<Integer> result = new ArrayList<>();
   Deque<Integer> deque = new ArrayDeque<>();
   for (int i = 0; i < array.length; i++) {
     while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
       deque.pollLast();
     }
 
     // if the index is to the left of the left boundary of the window, remove it from the deque
     if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
       deque.pollFirst();
     }
 
     // offer the index of new number
     deque.offerLast(i);
 
     if (i >= k - 1) {
       result.add(array[deque.peekFirst()]);
     }
   }
   return result;
 }
}
205. Implement LRU Cache

public class Solution<K, V> {
 // limit is the max capacity of the cache
 // What we need?
 // Need a Node class for the double linked list
 // Need a HashMap to map from key to the node in the linked list
 // NEED TO USE DOUBLE LINKED LIST FOR O(1)
 static class Node<K, V> {
   // What is inside Node class
   Node<K, V> prev;
   Node<K, V> next;
   K key;
   V value;
 
   // Constructor
   Node(K key, V value) {
     this.key = key;
     this.value = value;
   }
 
   // Update
   void update(K key, V value) {
     this.key = key;
     this.value = value;
   }
 }
 
 private final int limit;
 private Map<K, Node<K, V>> map;
 private Node<K, V> head;
 private Node<K, V> tail;
 
 public Solution(int limit) {
   this.limit = limit;
   map = new HashMap<K, Node<K, V>>();
 }
  private Node<K, V> remove(Node<K, V> node) {
   map.remove(node.key);
   if (node.prev != null) {
     node.prev.next = node.next;
   }
   if (node.next != null) {
     node.next.prev = node.prev;
   }
   // Update head and tail
   if (node == tail) {
     tail = node.prev;
   }
   if (node == head) {
     head = node.next;
   }
   node.next = null;
   node.prev = null;
   return node;
 }
 
 private Node<K, V> append(Node<K, V> node) {
   map.put(node.key, node);
   if (head == null) {
     head = node;
     tail = node;
   } else {
     node.next = head;
     head.prev = node;
     head = node;
   }
   return node;
 }
 
 public void set(K key, V value) {
   // Exist key already
   Node<K, V> node = null;
   if (map.containsKey(key)) {
     // 1. update node value and add node to the front
     node = map.get(key);
     node.value = value;  // still need to move to the front
     remove(node);
   } else {
     // Not exist. Exceed the cache limit?
     if (map.size() < limit) {
       // 2. add new node to the front of the linked list
       node = new Node<K, V>(key, value);
     } else {
       // 3. delete the tail and add new node to the front
       node = tail;
       remove(tail);
       node.update(key, value);
     }
   }
   append(node);
 }
  public V get(K key) {
   Node<K, V> node = map.get(key);
   if (node == null) {
     return null;
   }
   // Must put node in the front because it is latest used node
   remove(node);
   append(node);
   return node.value;
 }
}
206. Majority Number I
public class Solution {
 /*
 int candidate = array[0];
    int count = 1;
    for (int i = 1; i < array.length; i++) {
      if (count == 0) {
        count++;
      } else if (candidate == array[i]) {
        count++;
      } else {
        count--;
      }
    }
    return candidate;
   */
 public int majority(int[] array) {
      // Use HashMap to store number and count
      // Only one number has count > 0.5 * L? Yes
      // What is no such number? Has such number
 
      // once count + 1 > 0.5 * L, return current number
      // Time: O(n)
      // Space: O(n)
 
      Map<Integer, Integer> map = new HashMap<>();
      int n = (int) (0.5 * array.length);
      int count;
      for (int i = 0; i < array.length; i++) {
        if (!map.containsKey(array[i])) {
          count = 0;
          if (count + 1 > n) {
            return array[i];
          }
        } else {
          count = map.get(array[i]);
          if (count + 1 > n) {
            return array[i];
          }
        }
        map.put(array[i], count + 1);
      }
      return array[array.length - 1];
    }
}
202. Kth Smallest In Two Sorted Arrays
public class Solution {
 public int helper(int[] a, int aleft, int[] b, int bleft, int k) {
   // Base case
   // Case 1: eliminate all possible numbers in A
   if (aleft >= a.length) {
     return b[bleft + k - 1];
   }
   // Case 2: eliminate all possible numbers in B
   if (bleft >= b.length) {
     return a[aleft + k - 1];
   }
   // Case 3: k == 1
   if (k == 1) {
     return Math.min(a[aleft], b[bleft]);
   }
 
   int amid = aleft + k / 2 - 1;
   int bmid = bleft + k / 2 - 1;
 
   // when amid >= a.length, then kth number is not possible in a[amid + 1 : a.length - 1]
   int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
   int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
 
   if (aval <= bval) {
     return helper(a, amid + 1, b, bleft, k - k / 2);  // remove k / 2 already
   } else {
     return helper(a, aleft, b, bmid + 1, k - k / 2);
   }
 }
 public int kth(int[] a, int[] b, int k) {
   // k = 6
   // l m
   // aaaaaaaaaaaaaa
   // l m
   // bbbbbbbbbbbbbb
 
   // k = 3
   //    l
   // xxxaaaaaaaaaaa
   // l m
   // bbbbbbbbbbbbbb
 
   // if A[k / 2 - 1] < B[k / 2 - 1], discard A[l] to A[m], move l to m + 1 in A
   // impossible tha kth number is in the 'xxx' because xxx + bbb = k, so how can kth number be inside
   // a group of number with size smaller than k
 
   // if B[k / 2 - 1] < A[k / 2 - 1], discard B[l] to B[m], move l to m + 1 in B
   return helper(a, 0, b, 0, k);
 }
}
288. First Non-Repeating Character In Stream
public class Solution {
 // 要求是O(1)读取first non-repeating character，优先考虑linked list
 // 必须要double linked list，因为insert/delete都要O(1)完成
 // 而且必须是闭环double linked list，以防止删除最后一个的时候出现问题
 // head                tail
 // dummy <=> 1 <=> 2 <=> 3
 //   |___________________|
 private Node head;  // tail of the list
 private Node tail;  // head of the list
 private Map<Character, Node> single;
 private Set<Character> repeated;
 
 static class Node {
   Node prev;
   Node next;
   Character ch;
   Node (Character ch) {this.ch = ch;}
 }
 
 public Solution() {
   // Initialize the class.
   // 一个char只能对应一个node，一旦read的时候再次出现了Map里面的Character则需要从list和map中删除
   single = new HashMap<>();  // 存储只出现一次的character
   repeated = new HashSet<>();  // 存储出现次数>=2的character
   head = new Node(null);
   tail = head;
   tail.next = tail;
   tail.prev = tail;
 }
  public void read(char ch) {
   // Implement this method here.
   // 出现次数>=2
   if (repeated.contains(ch)) {
     return;
   }
 
   // 出现次数==1
   if (single.containsKey(ch)) {
     remove(ch);
   } else {
   // 出现次数==0
     append(ch);
   }
 }
  public Character firstNonRepeating() {
   // Implement this method here.
   if (head == tail) {
     // Only the dummy node
     return null;
   }
   return head.next.ch;
 }
 
 private void remove(Character ch) {
   Node node = single.get(ch);
   node.prev.next = node.next;
   node.next.prev = node.prev;
   if (node == tail) {
     tail = node.prev;
   }
   node.prev = null;
   node.next = null;
 
   single.remove(ch);
   repeated.add(ch);  // 出现>=2次的加入repeated中
 }
 
 private void append(Character ch) {
   single.put(ch, new Node(ch));
   Node node = single.get(ch);
   // 添加在linked list的尾部并且更新tail
   tail.next = node;
   node.prev = tail;
   node.next = head;
   tail = tail.next;
 }
}
