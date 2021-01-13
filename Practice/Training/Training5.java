8. Evaluate Reverse Polish Notation
public class Solution {
 private boolean isNumber(String str) {
   // Only consider 4 operators here
   if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
     return false;
   } else {
     return true;
   }
 }
 
 private int compute(int n1, int n2, String op) {
   int result;
   if (op.equals("+")) {
     result = n1 + n2;
   } else if (op.equals("-")) {
     result = n1 - n2;
   } else if (op.equals("*")) {
     result = n1 * n2;
   } else {
     // /
     result = n1 / n2;
   }
   return result;
 }
 
 public int evalRPN(String[] tokens) {
   /*
   ["2", "1", "+", "3", "*"]
   [2 1   +   => pop consecutively two times, compute and push computed result
   [3 3   *   => same
 
 
   [4 13 5   /   =>  13 / 5 = 2
   [4 2      +   =>  4 + 2 = 6
   */
   // What are tokens? number and binary operator
   // null or empty? Maybe
   // Time: O(n)
   // Space: O(n)
 
   if (tokens == null || tokens.length == 0) {
     return 0;
   }
 
   Deque<Integer> stack = new ArrayDeque<>();
   for (int i = 0; i < tokens.length; i++) {
     if (isNumber(tokens[i])) {
       stack.push(Integer.parseInt(tokens[i]));
     } else {
       int num2 = stack.pop();
       int num1 = stack.pop();
       stack.push(compute(num1, num2, tokens[i]));
     }
   }
   return stack.pop();
 }
}
24. Total Occurrence
public class Solution {
 public int totalOccurrence(int[] array, int target) {
   // array is null or empty? return 0
   // Binary search to find target
   // target: 5
   // 1 1 2 4 4 5 5 5 6 7 9 9
   // x x x x x y y y y y y y
   // x: < target
   // y: >= target
   // find the first target and count to the right
   if (array.length == 0 || array == null) {
     return 0;
   }
  
   int left = 0;
   int right = array.length - 1;
   while (left < right - 1) {  // two remaining after search
     int mid = left + (right - left) / 2;  // avoid overflow
     if (array[mid] >= target) {
       right = mid;  // cannot be mid - 1, may miss the target
     } else {
       left = mid;
     }
   }
 
   int start;
   if (array[left] == target) {
     start = left;
   } else {
     start = right;
   }
 
   int i = start;
   while (i < array.length && array[i] == target) {
     i++;
   }
 
   return i - start;
 }
}
142. Binary Tree Diameter
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
 private int helper(TreeNode root, int[] globalLongest) {
   if (root.left == null && root.right == null) {
     return 1;
   } else if (root.left != null && root.right == null) {
     return helper(root.left, globalLongest) + 1;
   } else if (root.left == null && root.right != null) {
     return helper(root.right, globalLongest) + 1;
   }
 
   int left = helper(root.left, globalLongest);
   int right = helper(root.right, globalLongest);
 
   globalLongest[0] = globalLongest[0] > left + right + 1 ? globalLongest[0] : left + right + 1;
 
   return Math.max(left, right) + 1;
 }
 
 public int diameter(TreeNode root) {
   // Longest distance from one leaf to another leaf node
   // Recursion
   /*
   At each layer, get the longest paths from the leaf of left and right subtrees
   // need a global maximum to record the longest distance during traversing
   globalLongest = globalLongest > left + right + 1 ? globalLongest : left + right + 1;
   return max(left, right) + 1
 
   Make sure the path is coming from the leaf
   Base case:
   if (root.left == null && root.right == null) {
     return 1;
   }
 
   if (root.left != null && root.right == null) {
     return helper(root.right)
   } else if (root.left == null && root.right != null) {
     return helper(root.left)
   }
 
   int left = helper(root.left);
   int right = helper(root.right);
  
   update globalLongest;
   return max(left, right) + 1;
 
   Time: O(n)
   Space: O(logn)
   */
   if (root == null) {
     return 0;
   }
 
   int[] globalLongest = new int[] {0};
   helper(root, globalLongest);
 
   return globalLongest[0];
 }
}
144. Recover Binary Search Tree
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
  private TreeNode one;
  private TreeNode two;
  private TreeNode prev;
 
  private void swap(TreeNode one, TreeNode two) {
    int temp = one.key;
    one.key = two.key;
    two.key = temp;
}
 
  
 
  public void helper(TreeNode root) {
    if (root == null) {
      return;
    }
 
    helper(root.left);
    if (prev != null && prev.key > root.key) {
      if (one == null) {
        one = prev;
      }
      two = root;
    }
   prev = root;
    helper(root.right);
  }
 
 
 public TreeNode recover(TreeNode root) {
   // Time: O(n)
   // Space: O(n)
   // one = prev, two = root
   helper(root);
    swap(one, two);
    return root;
 }
}
 
/*
1.
                 108
           106        null
       null  107
         null  110 <- one
              null 109 <- two
 
2.
                 108 <- two
           106        null
       null  107
         null  110 <- one
              null 109
 
3. swap one and two
*/ 
146. Find Number of BSTs Generated
public class Solution {
 public int numOfTrees(int n) {
   /*
   Every number can be a root
   1 2 3 4 5 6 7 8 9
 
   e.g. 5 as the root
   left:  1 2 3 4
   right: 6 7 8 9
 
   for the right (6 7 8 9), any element can be the root
 
   Using recursion to find the number (a lot of repeated counting)
 
   Use dynammic programming
   dp[i]: number of BST for size k
 
   i = 0: dp[i] = 0;
   i = 1: dp[i] = 1;
   i = 2:
   r
   1 2  -> count = 1
     r
   1 2  -> dp[1]
 
   i = 3:
   r
   1 2 3 
     r
   1 2 3  dp[0] + count: [r + 1 : end]
       r
   1 2 3  dp[1]
  
   Note that everything on the right are essentially the same as the left hand side
   Solution for LHS dp[j - 1]
   Solution for RHS dp[i - j]
   Solution for current position root j is dp[j - 1] * dp[i - j]
   Solution for dp[i]: add up all solution for root position j from 0 to i
   */
 
   int[] dp = new int[n + 1];
   dp[0] = 1;  // Should be 1 to avoid 0 * something = 0
   for (int i = 1; i <= n; i++) {
     dp[i] = 0;
     for (int j = 1; j <= i; j++) {
       dp[i] += dp[j - 1] * dp[i - j];
     }
   }
   return dp[dp.length - 1];
 }
}
160. Climbing Stairs
public class Solution {
 public int stairs(int n) {
   /*
   Dynammic programming
   dp[i]: the number of ways to reach to ith step
   dp[i] = dp[i - 2] + dp[i - 1]  you can either climb from i - 2 or from i - 1
 
   Time: O(n)
   Space: O(n)
 
   1 2 3 4
   1 2 3 5
   */
   if (n == 0) {
     return  0;
   }
   int[] dp = new int[n + 1];
   dp[0] = 1;
   dp[1] = 1;
   for (int i = 2; i <= n; i++) {
     dp[i] = dp[i - 2] + dp[i - 1];
   }
   return dp[n];
 }
}
92. Buy Stock I
public class Solution {
 public int maxProfit(int[] array) {
   int maxProfit = Integer.MIN_VALUE;
   int minPrice = array[0];
   for (int i = 1; i < array.length; i++) {
     maxProfit = Math.max(maxProfit, array[i] - minPrice);
     if (array[i] < minPrice) {
       minPrice = array[i];
     }
   }
   return maxProfit > 0 ? maxProfit : 0;
 }
}
38. Cycle Node In Linked List
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
 public ListNode cycleNode(ListNode head) {
   Set<ListNode> visited = new HashSet<>();
   ListNode cur = head;
   while (cur != null) {
     // Break when the node has been visited
     if (visited.contains(cur)) {
       return cur;
     }
     visited.add(cur);
     cur = cur.next;
   }
   return null;
 }
}
49. Identical Binary Tree
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
 public boolean isIdentical(TreeNode one, TreeNode two) {
   // In-order traversal
   // Assume not null
 
   // Base case:
   if ((one != null && two == null) || (one == null && two != null)) {
     return false;
   } else if (one == null && two == null) {
     return true;
   } else if (one.key != two.key) {
     return false;
   }
 
   // one.key == two.key, keep checking subtree
   boolean identicalLeft = isIdentical(one.left, two.left);
   if (!identicalLeft) {
     return false;
   }
   return isIdentical(one.right, two.right);
 }
}
69. Missing Number II
public class Solution {
 public int missing(int[] array) {
   // Use binary search
   /*
   l r
0 1 2 3 4 5 6
1 2 3 5 6 7 8
 
     l r
0 1 2 3 4 5 6
1 2 3 4 6 7 8
 
         l r
0 1 2 3 4 5 6
1 2 3 4 5 6 7
 
middle index + 1 < array[middle index]
right = mid
 
middle index + 1 == array[middle index]
left = mid
 
Time: O(n)
Space: O(1)
   */
 
   // Assume array is not null
   if (array.length == 0) {
     return 1;
   } else if (array.length == 1) {
     return array[0] == 1 ? 2 : 1;
   }
   if (array[0] != 1) {
     return 1;
   }
 
   int left = 0;
   int right = array.length - 1;
   while (left < right - 1) {
     int mid = left + (right - left) / 2;
     if (array[mid] > mid + 1) {
       right = mid;
     } else {
       left = mid;
     }
   }
 
// 1 2
// 2 3
 
   // two number left
   if (array[left] == left + 1 && array[right] == right + 1) {
     return array[right] + 1;
   } else if (array[left] > left + 1) {
     return array[left - 1] + 1;
   } else {
     // array[right] > right + 1
     return array[right - 1] + 1;
   }
 }
}
76. All Unique Characters I
public class Solution {
 public boolean allUnique(String word) {
   // HashSet
   Set<Character> set = new HashSet<>();
   for (int i = 0; i < word.length(); i++) {
     char c = word.charAt(i);
     if (set.contains(c)) {
       return false;
     }
     set.add(c);
   }
   return true;
 }
}
80. Remove Adjacent Repeated Characters II
public class Solution {
 public String deDup(String input) {
   /*
   null or empty? yes
   What is the input type? String
   In-place? yes
 
   Slow and fast pointers
 
   [0 s]: result
      s f
   a a b b b b b c
 
   if (array[f] == array[s - 1]) {
     while(until array[f] != array[s]) {
       f++
     }
   }
 
   Time: O(n)
   Space: O(1)
   */
 
   if (input == null || input.length() <= 2) {
     return input;
   }
 
   char[] array = input.toCharArray();
 
   int slow = 1;
   int fast = 2;
   while (fast < array.length) {
     if (array[fast] == array[slow] && array[fast] == array[slow - 1]) {
       while (fast < array.length && array[fast] == array[slow]) {
           fast++;
         }
     } else {
       slow++;
       array[slow] = array[fast];
       fast++;
     }
   }
   return new String(array, 0, slow + 1);
 }
}
81. Remove Adjacent Repeated Characters III
public class Solution {
 public String deDup(String input) {
   // Any time or space requirement? In-place
   // Null? yes
/*
Slow and fast pointer
[0 s]: result
 
   s                    f
   b  a  a  a  b  b  b  c
 
   array[s] = array[f];
 
   Time: O(n)
   Space: O(1)
*/
 
  
   if (input == null || input.length() <= 1) {
     return input;
   }
 
   char[] array = input.toCharArray();
   int slow = 0;
   int fast = 1;
   while (fast < array.length) {
     if (array[slow] == array[fast]) {
       while (fast < array.length && array[slow] == array[fast]) {
         fast++;
       }
       if (fast == array.length) {
         return new String(array, 0, slow);
       }
       array[slow] = array[fast];
       fast++;
     } else {
       slow++;
       array[slow] = array[fast];
       fast++;
     }
   }
 
   return new String(array, 0, slow + 1);
 }
}
// a b c
// a a c

83. Encode Space
public class Solution {
 public String encode(String input) {
   // In-place? Do we have enough space in the array for the output?
      // From right to left, use slow and fast pointer to do this in-place
 
  /*            
                 f
        abd sds bs
                 s
    abd20%sds20%bs
 
    abbbcdd
    ab3cd2
   
  */
 
      // Time: O(n)
      // Space: O(1)
      // Assume enough space
      char[] array = new char[input.length() * 10];
      for (int i = 0; i < input.length(); i++) {
        array[i] = input.charAt(i);
      }
 
      int fast = input.length() - 1;
      int slow = array.length - 1;
      while (fast >= 0) {
        if (array[fast] == ' ') {
          array[slow] = '%';
          slow--;
          array[slow] = '0';
          slow--;
          array[slow] = '2';
          slow--;
          fast--;
        } else {
          array[slow] = array[fast];
          fast--;
          slow--;
        }
      }
      return new String(array, slow + 1, array.length - slow - 1);
    }
}
90. Array Hopper III
public class Solution {
 public int minJump(int[] input) {
   /*
   i: problem size
   j (j < i): position that already has the solution
   solution[j]: the minimum number of jumps needed to jump to i
  
   for (int j = 0; j < i; j++)
     if position j is reachable and from position j we can reach OVER i
       if (solution[i] has not been updated)
         update it
       else
         update solution[i] = min(solution[i], solution[j] + 1)
 
   Time: O(n^2)
   Space: O(n)
   */
   // Assume array.length >= 1
   int[] array = new int[input.length + 1];
    for (int i = 0; i < input.length; i++) {
      array[i] = input[i];
    }
    
      int[] solution = new int[array.length];
      solution[0] = 0;
      for (int i = 1; i < array.length; i++) {
        solution[i]= -1;
        for (int j = 0; j < i; j++) {
          if (solution[j] != -1 && j + array[j] >= i) {
            if (solution[i] == -1) {
              solution[i] = solution[j] + 1;
            } else {
              solution[i] = Math.min(solution[i], solution[j] + 1);
            }
          }
        }
      }
      return solution[solution.length - 1];
 }
}
183. 2 Sum Closest
public class Solution {
 private void update(List<Integer> result, int[] array, int left, int right, int target) {
      int value1 = result.get(0);
      int value2 = result.get(1);
      if (Math.abs(target - (array[left] + array[right])) < Math.abs(target - (value1 + value2))) {
        result.clear();
        result.add(array[left]);
        result.add(array[right]);
      }
  }
 public List<Integer> closest(int[] array, int target) {
   // Sorted? No
   // Assume not null
   // Duplicate? yes
   // Assume array length >= 2
   // Sort first
   // two pointers come from both sides toward middle
   /*
       if (array[left] + array[right] > target) {
         right--;
         update result if needed
       } else {
         left++;
         update result if needed
       }
 
       Time: O(nlogn + n)
       Space: O(1)
   */
 
   Arrays.sort(array);
   int left = 0;
   int right = array.length - 1;
   List<Integer> result = new ArrayList<>();
   result.add(array[left]);
   result.add(array[right]);
   while (left < right) {
     int sum = array[left] + array[right];
     if (sum > target) {
       update(result, array, left, right, target);
       right--;
     } else {
       update(result, array, left, right, target);
       left++;
     }
   }
   return result;
 }
}
184. 2 Sum Smaller
public class Solution {
 public int smallerPairs(int[] array, int target) {
   // Sorted? No
   // Assume array length >= 2
/*    for (int i = 0; i < array.length; i++) {
       if (array[i] > target)  break;
       for (int j = i + 1; j < array.length; j++) {
         if (array[i] + array[j] >= target) {
           break;
         }
         count++;
       }
     }
     */
 
     Arrays.sort(array);
     int count = 0;
     int left = 0;
     int right = array.length - 1;
     while (left < right) {
       if (array[left] + array[right] < target) {
         //   l        r
         // [[1, 3, 3, 4, 7],6]
         // 如果 array[left] + array[right] < target, 则 array[left] + array[left to right - 1] < target
         // 1 + 4 < 6, 则 1 + 3, 1 + 3 肯定小于6
         count += right - left;
         left++;
       } else {
         right--;
       }
     }
     return count;
 }
}
56. Merge Intervals (leetcode)
/**
* Definition of Interval:
* public class Interval {
*     int start, end;
*     Interval(int start, int end) {
*         this.start = start;
*         this.end = end;
*     }
* }
*/
 
public class Solution {
   /**
    * @param intervals: interval list.
    * @return: A new interval list.
    */
   
   public static class MyComparator implements Comparator<Interval> {
       public int compare(Interval a, Interval b) {
           return a.start - b.start;
       }
   }
   public List<Interval> merge(List<Interval> intervals) {
       // write your code here
       /*
       Sort first!
      
       Go through the interval lists
       1    3
          2   6
       merge to 1  6
      
       if there is a gap, put interval into result list
       Time: O(n)
       Space: O(1)
       */
       // Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
       Collections.sort(intervals, new MyComparator());
      
       List<Interval> result = new ArrayList<>();
       if (intervals == null || intervals.size() == 0) {
           return result;
       }
      
       // Assume input is an arraylist
       int curStart = Integer.MIN_VALUE;
       int curEnd = Integer.MIN_VALUE;
       for (Interval inter : intervals) {
           if (inter.start > curEnd) {
               if (curStart != Integer.MIN_VALUE && curEnd != Integer.MIN_VALUE) {
                   result.add(new Interval(curStart, curEnd));
               }
               curStart = inter.start;
               curEnd = inter.end;
           } else {
               if (inter.end > curEnd) {
                   // (1 10) (2 3) (4 5) (6 7) (8 9)
                   curEnd = inter.end;
               }
           }
       }
       result.add(new Interval(curStart, curEnd));
       return result;
   }
}
223. Rectangle Area
class Solution {
   public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
       // x axis [A, C], [E, G]
       // y axis [B, D], [F, H]
      
       // Sort intervals by starting times for x and y axis
       int[] xInterval0 = new int[2];
       int[] xInterval1 = new int[2];
       if (A < E) {
           xInterval0[0] = A;
           xInterval0[1] = C;
           xInterval1[0] = E;
           xInterval1[1] = G;
       } else {
           xInterval0[0] = E;
           xInterval0[1] = G;
           xInterval1[0] = A;
           xInterval1[1] = C;
       }
      
       int[] yInterval0 = new int[2];
       int[] yInterval1 = new int[2];
       if (B < F) {
           yInterval0[0] = B;
           yInterval0[1] = D;
           yInterval1[0] = F;
           yInterval1[1] = H;
       } else {
           yInterval0[0] = F;
           yInterval0[1] = H;
           yInterval1[0] = B;
           yInterval1[1] = D;
       }
      
       // Check x axis
       int width = 0;
       if (xInterval0[1] > xInterval1[0]) {
           if (xInterval0[1] < xInterval1[1]) {
               width = xInterval0[1] - xInterval1[0];
           } else {
               width = xInterval1[1] - xInterval1[0];
           }
       }
      
       int height = 0;
       if (yInterval0[1] > yInterval1[0]) {
           if (yInterval0[1] < yInterval1[1]) {
               height = yInterval0[1] - yInterval1[0];
           } else {
               height = yInterval1[1] - yInterval1[0];
           }
       }
      
       return (C - A) * (D - B) + (G - E) * (H - F) - width * height;
   }
}

920. Meeting Rooms
/**
* Definition of Interval:
* public class Interval {
*     int start, end;
*     Interval(int start, int end) {
*         this.start = start;
*         this.end = end;
*     }
* }
*/
 
public class Solution {
   /**
    * @param intervals: an array of meeting time intervals
    * @return: if a person could attend all meetings
    */
   static class MyComparator implements Comparator<Interval> {
       public int compare(Interval a, Interval b) {
           return a.start - b.start;
       }
   }
   
   public boolean canAttendMeetings(List<Interval> intervals) {
       /*
       Determine if there is an overlap between these interval
       Are the interval sorted by starting time? No
       Sorted first
      
       Go through all intervals and compare the prev.end and cur.start
       if prev.end > cur.start return false
       */
       if (intervals.size() <= 1) {
           return true;
       }
      
       // Sort
       Collections.sort(intervals, new MyComparator());
      
       Interval pre = null;
       Interval cur = null;
       for (Interval inter : intervals) {
           if (pre == null && cur == null) {
               pre = inter;
               continue;
           }
           cur = inter;
           if (pre.end > cur.start) {
               return false;
           }
           pre = cur;
       }
       return true;
   }
}
