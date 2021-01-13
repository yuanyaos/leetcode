650. Common Numbers Of Two Arrays I(Array version)
public class Solution {
 public List<Integer> common(int[] a, int[] b) {
   // 看是想省时间还是空间了，省时间的话就用HashMap
   // 注意这里是 No duplicate
   // 注意返回的是sorted的!!!
   // Time: O(n + nlogn), space: O(n)
 
   List<Integer> result = new ArrayList<>();
   Set<Integer> set = new HashSet<>();
   for (int i = 0; i < a.length; i++) {
     set.add(a[i]);
   }
 
   Arrays.sort(b);
   for (int i = 0; i < b.length; i++) {
     if (set.contains(b[i])) {
       result.add(b[i]);
     }
   }
 
   return result;
 }
}
651. Common Numbers Of Two Arrays II(Array version)
public class Solution {
 public List<Integer> common(int[] A, int[] B) {
   // 有可能有duplicate
   // 用一个HashMap，扫一遍A，key是数值，value是key出现的次数
   // 扫一遍B，每在HashMap中找到一个就更新一下HashMap
  
   List<Integer> result = new ArrayList<>();
   Map<Integer, Integer> map = new HashMap<>();
   for (int i = 0; i < A.length; i++) {
     if (!map.containsKey(A[i])) {
       map.put(A[i], 1);
     } else {
       map.put(A[i], map.get(A[i]) + 1);
     }
   }
 
   Arrays.sort(B);
   for (int i = 0; i < B.length; i++) {
     if (map.containsKey(B[i]) && map.get(B[i]) > 0) {
       result.add(B[i]);
       map.put(B[i], map.get(B[i]) - 1);
     }
   }
   return result;
 }
}
133. Merge K Sorted Array
public class Solution {
 static class Elem {
   int indexOfArray;
   int indexInArray;
   int value;
 
   Elem(int indexOfArray, int indexInArray, int value) {
     this.indexOfArray = indexOfArray;
     this.indexInArray = indexInArray;
     this.value = value;
   }
 }
 
 static class MyComparator implements Comparator<Elem> {
   public int compare(Elem a, Elem b) {
     if (a.value == b.value) {
       return 0;
     }
     return a.value < b.value ? -1 : 1;
   }
 }
 
 private int buildHeap(PriorityQueue<Elem> minHeap, int[][] arrayOfArrays) {
   // return length
   int length = 0;
   for (int i = 0; i < arrayOfArrays.length; i++) {
     length += arrayOfArrays[i].length;
     if (arrayOfArrays[i].length > 0) {
          Elem e = new Elem(i, 0, arrayOfArrays[i][0]);
          minHeap.offer(e);
      }
   }
   return length;
 }
 
 private void merge(int[] result, int[][] arrayOfArrays, PriorityQueue<Elem> minHeap) {
   int count = 0;
   while (!minHeap.isEmpty()) {
     Elem cur = minHeap.poll();
     result[count++] = cur.value;
     if (cur.indexInArray < arrayOfArrays[cur.indexOfArray].length - 1) {
       cur.indexInArray++;
       cur.value = arrayOfArrays[cur.indexOfArray][cur.indexInArray];
       minHeap.offer(cur);
     }
   }
 }
 
 public int[] merge(int[][] arrayOfArrays) {
	// 两两merge
   // Time: O((1+2+3+...+k)n) = O(k^2*n)
   // Space: O(kn)
 
   //        l1
   //  1. xxxxxxxxxxxxx        n
   //             l2
   //  2. yyyyyyyyyyy          n
   //          l3
   //  3. zzzzzzzzzzzzzzz      n
   //  k = 3
   // 需要一个heap来存储，小的那个l有higher priority
   // nk个数，每次更新heap需要logk
   // Time: O(nk * logk)
   // Space: O(k)  heap的存储空间
 
   int arrayNumber = arrayOfArrays.length;
   PriorityQueue<Elem> minHeap = new PriorityQueue<>(arrayNumber, new MyComparator());
 
   // Build the heap
   int length = buildHeap(minHeap, arrayOfArrays);
 
   // Merge
   int[] result = new int[length];
   merge(result, arrayOfArrays, minHeap);
 
   return result;
 }
}
134. Merge K Sorted Lists
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
 static class MyComparator implements Comparator<ListNode> {
   public int compare(ListNode a, ListNode b) {
     if (a.value == b.value) {
       return 0;
     }
     return a.value < b.value ? -1 : 1;
   }
 }
 public ListNode merge(List<ListNode> listOfLists) {
   // 跟着之前的一样，用minHeap
 
   ListNode dummy = new ListNode(0);
   PriorityQueue<ListNode> minHeap = new PriorityQueue<>(11, new MyComparator());
 
   // Build min heap
   for (ListNode node : listOfLists) {
     minHeap.offer(node);
   }
 
   // Merge
   ListNode cur = dummy;
   while (!minHeap.isEmpty()) {
     ListNode temp = minHeap.poll();
     cur.next = temp;
     cur = cur.next;
     if (temp.next != null) {
       temp = temp.next;
       minHeap.offer(temp);
     }
   }
 
   return dummy.next;
 }
}
171. Common Elements In Three Sorted Array
public class Solution {
 public List<Integer> common(int[] a, int[] b, int[] c) {
   // Sorted好的吗？是的
   // Heap: O(logk * nk)
   // 3 pointers: O(nk) -> preferred, move the pointer pointing to the smallest number
   // 1, 2, 2, 3
   // 2, 2, 3, 5
   // 2, 2, 4
   int ai = 0;
   int bi = 0;
   int ci = 0;
   List<Integer> result = new ArrayList<>();
   while (ai < a.length && bi < b.length && ci < c.length) {
     if (a[ai] == b[bi] && b[bi] == c[ci]) {
       result.add(a[ai]);
       ai++;
       bi++;
       ci++;
     } else if (a[ai] <= b[bi] && a[ai] <= c[ci]) {
       // 必须是小于等于！！！要不然出现等于的时候pointer不会前进！！！
       ai++;
     } else if (b[bi] <= a[ai] && b[bi] <= c[ci]) {
       bi++;
     } else if (c[ci] <= a[ai] && c[ci] <= b[bi]) {
       ci++;
     }
   }
   return result;
 }
}
644. Common Elements In K Sorted Lists
public class Solution {
 private List<Integer> helper(List<Integer> result, List<Integer> newList) {
   // 要保证List是ArrayList才行，要不然get(i)需要O(n)
   List<Integer> newResult = new ArrayList<>();
   int pr = 0;
   int pn = 0;
   while (pr < result.size() && pn < newList.size()) {
     Integer tr = result.get(pr);
     Integer tn = newList.get(pn);
     if (tr.equals(tn)) {
       newResult.add(tr);
       pr++;
       pn++;
     } else if (tr < tn) {
       pr++;
     } else {
       pn++;
     }
   }
   return newResult;
 }
 
 public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
   // Sorted
   // 不要用minHeap的方法，Time：O(logk * nk)
 
   // A1 and A2 => O(n)
   // A12 and A3 => O(n)
   // ...
   // A1_k-1 and A_k => O(n)
   // Time: O(nk), space: O(n)
 
   List<Integer> result = input.get(0);
   for (int i = 0; i < input.size(); i++) {
     result = helper(result, input.get(i));
   }
   return result;
 }
}
198. Largest Rectangle In Histogram
public class Solution {
 public int largest(int[] array) {
   // 利用单调栈，stack存储左边界，只要左边界大于等于
   // 虽然有两个循环，但是时间是O(n)，因为每个数字进栈和出栈只有一次，顶多是是2n
   // Spce: O(n)
   Deque<Integer> stack = new ArrayDeque<>();
   int result = 0;
 
   //         |
   //      |  |  |
   //   |  |  |  |
   //   0  1  2  3  4
   // 必须把4也考虑进去
   for (int i = 0; i <= array.length;i++) {
     // 最后一个设置为0是force检查stack里剩下的的所有左边界
     int cur = i == array.length ? 0 : array[i];
     // 这个循环的目的是把所有存储的比cur大(或等于)的左边界都排除掉，然后剩下比cur小的左边界都可以被cur(包括cur)之后的数字继续用为左边界
     // 计算面积的时候是计算小于cur index的面积，*********不包括cur index！！！！！！！！*********** 所以需要 i <= array.length
     while (!stack.isEmpty() && array[stack.peek()] >= cur) {
       // 不能先找左边界，因为stack为空的时候说明left == 0，而先找左边界会忽略stack为空的情况
       int height = array[stack.pop()];
       // array[stack.peek() + 1] 必然大于等于cur，array[stack.peek()]可能小于cur，但是下一次也进不来while循环
       int left = stack.isEmpty() ? 0 : stack.peek() + 1;
       result = Math.max(result, height * (i - left));
     }
     stack.push(i);
   }
   return result;
 }
}
199. Max Water Trapped I
public class Solution {
 public int maxTrapped(int[] array) {
   // 双边向内，不可以用单边的，比如{3, 2, 1}，没有右边的boundary水就没办法存储起来
   // traverse的过程中不断更新边界，一旦新的值大于当前边界，就需要更新，否则计算面积
   int left = 0;
   int right = array.length - 1;
   int lmax = 0;
   int rmax = 0;
   int result = 0;
   while (left < right) {
     if (array[left] < array[right]) {
       if (array[left] < lmax) {
         result += lmax - array[left];
       } else {
         lmax = array[left];
       }
       left++;
     } else {
       if (array[right] < rmax) {
         result += rmax - array[right];
       } else {
         rmax = array[right];
       }
       right--;
     }
   }
   return result;
 }
}
216. Most Points On A Line
/*
* class Point {
*   public int x;
*   public int y;
*   public Point(int x, int y) {
*     this.x = x;
*     this.y = y;
*   }
* }
*/
public class Solution {
 public int most(Point[] points) {
   // Time: O(n^2), space: O(n)
    int result = 0;
    for (int i = 0; i < points.length; i++) {
      Point seed = points[i];
      int same = 1;
      int sameX = 0;
      int most = 0;
      Map<Double, Integer> cnt = new HashMap<Double, Integer>();
     // 不用往前看了，之前的已经找过了
      for (int j = i; j < points.length; j++) {
        if (i == j) {
          continue;
        }
        Point tmp = points[j];
        if (tmp.x == seed.x && tmp.y == seed.y) {
          same++;
        } else if (tmp.x == seed.x) {
          sameX++;
        } else {
          double slope = ((tmp.y - seed.y) + 0.0) / (tmp.x - seed.x);
          if (!cnt.containsKey(slope)) {
            cnt.put(slope,  1);
          } else {
            cnt.put(slope, cnt.get(slope) + 1);
          }
          most = Math.max(most, cnt.get(slope));
        }
      }
      most = Math.max(most, sameX) + same;
      result = Math.max(result, most);
    }
    return result;
  }
}
