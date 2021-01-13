public class Solution {
 private void helper(StringBuilder sb, int index, List<String> result, char[] setArray) {
   // Base case
   if (index == setArray.length) {
     result.add(sb.toString());
     return;
   }
 
   // 当前index上 有 或者 没有 这个字母
   // 1. 如果有，只能有一次，skip all repeated characters
   sb.append(setArray[index]);
   helper(sb, index + 1, result, setArray);
   // 记得要remove掉刚刚加进去的，要不然重复添加同一个字母了
   sb.deleteCharAt(sb.length() - 1);
 
   // 从当前index一直移到不与index字母相同的字母，避免重复
   while (index < setArray.length - 1 && setArray[index] == setArray[index + 1]) {
     index++;
   }
 
   // 2. 不加当前的字母
   helper(sb, index + 1, result, setArray);
 }
 
 public List<String> subSets(String set) {
   // 这道题定义的有问题，实际说的是避免重复的排序，比如 a b1 b2 c，不能出现 a b1 b2 c 和 a b2 b1 c
   // 先让current possition存在，然后再在current position不存在并且skip全部的相同字母
   // 需要先sort一下set
   // 每一层都代表一个index，需要加一个loop skip掉之后与当前字母一样的字母
   // TimeL O(nlogn + 2^n), space: O(height)
 
   // Corner case
   if (set == null) {
     return new ArrayList<String>();
   }
   // ******** 先 sort ********
   char[] setArray = set.toCharArray();
   Arrays.sort(setArray);
 
   List<String> result = new ArrayList<>();
   StringBuilder sb = new StringBuilder();
   helper(sb, 0, result, setArray);
 
   return result;
 }
}

640. All Subsets of Size K
public class Solution {
 private void helper(List<String> result, StringBuilder sb, int index, char[] set, int k) {
   // Base case
   if (sb.length() == k) {
     result.add(sb.toString());
     return;
   }
 
   // Index不能比set还要长
   if (index == set.length) {
     return;
   }
 
   // Index 代表当前位置，两种选择，加 和 不加
   // Case 1: 不加
   helper(result, sb, index + 1, set, k);
 
   // Case 2: 加
   sb.append(set[index]);
   helper(result, sb, index + 1, set, k);
   sb.deleteCharAt(sb.length() - 1);
 }
 
 public List<String> subSetsOfSizeK(String set, int k) {
   // 跟普通的 All subsets完全一样，只需要在 base case 让 index==k 返回就行了
   List<String> result = new ArrayList<>();
   if (set == null) {
     return result;
   }
 
   StringBuilder sb = new StringBuilder();
   char[] setArray = set.toCharArray();
   helper(result, sb, 0, setArray, k);
 
   return result;
 }
}
179. All Valid Permutations Of Parentheses II

/*
Have remaining numbers for every bracket
stack [<({{
 
if stack.peek is left bracket
  we can add left brackets if there are remaining
 
l: (), m: <>, n: {}
remain[6] = {l, m, n, l, m, n};
bracket[6] = {‘(‘, ‘<’, ‘{’, ‘)’, ‘>’, ‘}’};
 
if (remain[3] == 0 && remain[4] == 0 && remain[5])
  list.add(sb.toString());
 
for (int i = 0; i < 3; i++)
  if (i < 3 && remain[i] > 0)
    remain[i]--;
    stack.push(bracket[i]);
    sb.append(bracket[i]);
    helper(sb);
    remain[i]++;
    sb.deleteCharAt(sb.length() - 1);
 
  // remaining right bracket > remaining left bracket
  if (i > 3 && remain[i] > remain[(i - 1) / 2] && stack.peek() == bracket[(i - 1) / 2])
    stack.pop();
    remain[i]--;
    sb.append(bracket[i]);
    helper(sb);
    remain[i]++;
    sb.deleteCharAt(sb.length() - 1);
*/
public class Solution {
 private void helper(StringBuilder sb, List<String> result, int[] remain, Deque<Character> stack, int length, char[] bracket) {
   if (sb.length() == length) {
     result.add(sb.toString());
     return;
   }
 
   for (int i = 0; i < remain.length; i++) {
     if (i % 2 == 0) {
       // 左括号
       if (remain[i] > 0) {
         stack.offerFirst(bracket[i]);
         remain[i]--;
         sb.append(bracket[i]);
         helper(sb, result, remain, stack, length, bracket);
         sb.deleteCharAt(sb.length() - 1);
         remain[i]++;
         stack.pollFirst();
       }
     } else {
       // 右括号
       // 加右括号的时候一定要看inner most的左括号有没有已经match好了
       if (!stack.isEmpty() && bracket[i - 1] == stack.peekFirst()) {
         // poll出来因为match上之前的左括号了，也就是说这一对括号对剩下的左右括号的加减没有影响了
         // 但是一定要注意后面还得加回来，因为比如过对'('来说，现在添加')'可以消除这一对的影响
         // 但是'('后面依旧可以加左括号'(', '<', '<'，所以需要在stack中加回来
         stack.pollFirst();
         remain[i]--;
         sb.append(bracket[i]);
         helper(sb, result, remain, stack, length, bracket);
         sb.deleteCharAt(sb.length() - 1);
         remain[i]++;
         // 把这个左括号加回来
         stack.offerFirst(bracket[i - 1]);
       }
     }
   }
 }
 public List<String> validParentheses(int l, int m, int n) {
   // inner most左括号有最高的优先级来得到右括号，其它的括号必须再等inner most的结束后才能
   // 添加右括号，否则只能添加左括号
   // Time: O(3^n), space: O(n)
 
   List<String> result = new ArrayList<>();
   // Stack 记录当前这一层哪一个括号被用了，特别是左括号
   Deque<Character> stack = new ArrayDeque<>();
   StringBuilder sb = new StringBuilder();
 
   char[] bracket = new char[] {'(', ')', '<', '>', '{', '}'};
   int[] remain = new int[] {l, l, m, m, n, n};
   int length = 2 * (l + m + n);
 
   helper(sb, result, remain, stack, length, bracket);
   return result;
 }
}
404. Factor Combinations
public class Solution {
 private void helper(List<List<Integer>> result, List<Integer> list, int index, int target, List<Integer> factors) {
      // Base case
    if (target == 1) {
 	   result.add(new ArrayList<>(list));
 	   return;
       }
    
    if (index == factors.size()) {
 	   return;
    }
 
      // 对于每个factor, 都在这一层完成
      // 可以除或者不除，除的话除多少次？
 
      // 1. 不除 (0)
      helper(result, list, index + 1, target, factors);
 
      // 2. 除，除多少次 (1, 2, 3, ...)
      int factor = factors.get(index);  // 因为是ArrayList，所以是O(n)
      int size = list.size();
      while (target % factor == 0) {
        list.add(factor);
        target /= factor;
        helper(result, list, index + 1, target, factors);
      }
      // 你往里加一定要删回去，要不然list会一直增加
      // subList(inclusive, exclusive)
      list.subList(size, list.size()).clear();
    }
 
    private List<Integer> getFactor(int target) {
      List<Integer> factors = new ArrayList<>();
      // 不能是1，1能整除任何数字
      for (int i = 2; i <= target / 2; i++) {
        if (target % i == 0) {
          factors.add(i);
        }
      }
      return factors;
    }
 
    public List<List<Integer>> combinations(int target) {
      // 类似于如果用coin组合得到一个target number的问题
      // ****************** 对于coin的问题来说，每一层就是一个币种，并检查每一层用多少个这个币种 ******************
      // 下一层不要再出现这个币种了，Time: O(N_target ^ N_factor), space: O(N_factor)
      // 要不然就是 Time: O(N_factor ^ N_target), space: O(N_target), VERY BAD!!!
      // 这道题则是看看能不能被结果每一层的target整除，可以整除的话就更新target，继续往下
 
      // 首先需要知道有哪些factor(币种)
     List<List<Integer>> result = new ArrayList<List<Integer>>();
     if (target <= 1) {
       return result;
     }
      List<Integer> factors = getFactor(target);
      List<Integer> list = new ArrayList<>();
 
      helper(result, list, 0, target, factors);
 
      return result;
    }
}
643. All Permutations of Subsets
public class Solution {
 private void swap(char[] array, int a, int b) {
   char temp = array[a];
   array[a] = array[b];
   array[b] = temp;
 }
 
 private void helper(List<String> result, StringBuilder sb, char[] array, int index) {
   // 可以不用加这个base case，因为for loop里面已经限制了index的最大值不能超过array.length
   if (index == array.length + 1) {
     return;
   }
 
   // 每一次都得加，因为是所有的permutation
   result.add(new String(array, 0, index));
 
   // 从index开始swap，要不然会重复加入结果
   for (int i = index; i < array.length; i++) {
     // swap-swap的方法
     swap(array, index, i);
     helper(result, sb, array, index + 1);
     swap(array, index, i);
   }
 }
 
 public List<String> allPermutationsOfSubsets(String set) {
   // Write your solution here
   // [a, b, c, d, e, f]
   // 不像 All subset, 每一个位置只有一个字母 出现/不出现 的两种可能
   // 任何没有使用过的字母都可以出现在这个位置上，所以需要从index开始
   // 把所有字母都跟当前的swap一下
   // 不需要用StringBuilder
   // Time: O(n!), space: O(N_set)
   List<String> result = new ArrayList<>();
 
   char[] array = set.toCharArray();
   StringBuilder sb = new StringBuilder();
 
   helper(result, sb, array, 0);
 
   return result;
 }
}
263. Two Subsets With Min Difference
/*
Try all possible combinations in the array. So we use DSF
 
Goal:
diff = Math.abs(prevSum - (sum - prevSum)) == Math.abs(2 * prevSum - sum) as small as possible
 
Need a global minimum, update the globalMin if diff is smaller
 
At level
Use array[i]
Do not use array[i]
 
1 or 0
 
3 or 0
 
2 or 0
 
      1                    0
 	       3          0          3         0
                   2      0     2   0      2  0     2  0
 
[1 2 3], [1 3], [1 2], [1], [3 2], [3], [2], [0]
 
Base case:
if index == array.length || size > array.length / 2
  return;
 
cur = Math.abs(2 * sum - totalSum)
if (cur < globalMin)
  globalMin = cur;
 
helper(sum, index + 1, size, globalMin)
 
sum += array[i];
helper(sum, index + 1, size + 1, globalMin)
sum -= array[i]
*/
public class Solution {
 private void helper(int currSum, int sum, int[] result, int index, int[] array, int size) {
   if (index == array.length || size > array.length / 2) {
     return;
   }
 
   // 更新 result
   if (size == array.length / 2 && Math.abs(2 * currSum - sum) < result[0]) {
     result[0] = Math.abs(2 * currSum - sum);
   }
 
   // currSum 可以 加 或者 不加 当前的array[index]
   // 1. 不加
   helper(currSum, sum, result, index + 1, array, size);
 
   // 2. 加
   currSum += array[index];
   size++;
   helper(currSum, sum, result, index + 1, array, size);
   // 记得减回去
   currSum -= array[index];
   size--;
 }
 
 private int getSum(int[] array) {
   int sum = 0;
   for (int i = 0; i < array.length; i++) {
     sum += array[i];
   }
   return sum;
 }
 
 public int minDifference(int[] array) {
   // 先得到set的sum，每一个位置上这个数都可以有出现和不出现两种可能
   // 一旦出现了 abs(current_sum - (sum - current_sum)) < global_min_diff
   // 则更新global_min_diff
   // *************** 注意subset的size是n/2！***************
   // Time: O(2^n), space: O(n)
 
   int[] result = new int[] {Integer.MAX_VALUE};
   int sum = getSum(array);
   helper(0, sum, result, 0, array, 0);
 
   return result[0];
 }
}
641. All Subsets II of Size K
public class Solution {
 private void helper(List<String> result, StringBuilder sb, char[] array, int index, int k) {
   // 这个条件需要先加
   if (sb.length() == k) {
     result.add(sb.toString());
     return;
   }
 
   if (index == array.length) {
     return;
   }
   // 加
   sb.append(array[index]);
   helper(result, sb, array, index + 1, k);
   sb.deleteCharAt(sb.length() - 1);
   while (index < array.length - 1 && array[index] == array[index + 1]) {
     index++;
   }
 
   // 不加
   helper(result, sb, array, index + 1, k);
 }
 public List<String> subSetsIIOfSizeK(String set, int k) {
   // 就是之前题目的组合
   // 先sort，再用while loop把重复的字母忽略掉
   List<String> result = new ArrayList<>();
   StringBuilder sb = new StringBuilder();
   char[] array = set.toCharArray();
   Arrays.sort(array);
 
   helper(result, sb, array, 0, k);
   return result;
 }
}
642. All Valid Permutations Of Parentheses III
public class Solution {
 private void helper(List<String> result, Deque<Integer> stack, int[] remain, char[] bracket, StringBuilder sb, int total) {
   if (sb.length() == total) {
     result.add(sb.toString());
     return;
   }
 
   // 遍历所有remaining括号
   for (int i = 0; i < remain.length; i++) {
     if (i % 2 == 0) {
       // 加左括号，priority必须小于之前stack里面的才行
       if (remain[i] > 0 && (stack.isEmpty() || stack.peekFirst() > i)) {
         sb.append(bracket[i]);
         remain[i]--;
         stack.offerFirst(i);
 
         helper(result, stack, remain, bracket, sb, total);
 
         sb.deleteCharAt(sb.length() - 1);
         remain[i]++;
         stack.pollFirst();
       }
     } else {
       // 加右括号，检查priotiy和左括号 remain[i] > remain[i - 1]
       if (!stack.isEmpty() && stack.peekFirst() == i - 1) {
         sb.append(bracket[i]);
         remain[i]--;
         // 匹配上了，就可以把这一对括号从stack中去掉
         stack.pollFirst();
 
         helper(result, stack, remain, bracket, sb, total);
 
         sb.deleteCharAt(sb.length() - 1);
         remain[i]++;
         stack.offerFirst(i - 1);
       }
     }
   }
 }
 public List<String> validParenthesesIII(int l, int m, int n) {
   // 跟之前的all valid permutations of parentheses一样
   // 1. 对于只有一种括号的，最简单，在加右括号的时候只需要看remaining right是不是大于remaining left，是的话可以加
   // 2. 对于有不同种的括号，需要用stack来记录放的括号，加入右括号的时候必须查看是否和innermost的左括号相匹配，也就是stack.peek()
   // 3. 对于这道题，需要加priority，需要先加高priority的括号
 
   List<String> result = new ArrayList<>();
   Deque<Integer> stack = new ArrayDeque<>();
   int[] remain = new int[] {l, l, m, m, n, n};
   char[] bracket = new char[] {'(', ')', '<', '>', '{', '}'};
   StringBuilder sb = new StringBuilder();
   int total = 2 * l + 2 * m + 2 * n;
   helper(result, stack, remain, bracket, sb, total);
 
   return result;
 }
}
264. Keep Distance For Identical Elements(C++ not ready)
public class Solution {
 private boolean helper(int[] array, int index, int[] used) {
   if (index == array.length) {
     return true;
   }
 
   for (int distance = 1; distance < used.length; distance++) {
     if (used[distance] == 0 || (used[distance] == 1 && index > distance && distance == array[index - distance - 1])) {
       array[index] = distance;
       used[distance]++;
       if (helper(array, index + 1, used)) {
         return true;
       }
       used[distance]--;
     }
   }
   return false;
 }
 
 public int[] keepDistance(int k) {
   // https://notes.peterchen.xyz/Interview/keep-distance-for-identical-elements/
   // 用permutation swap-swap的方法，关键在于在所有permutation加限制条件，使得找到的permutation满足题意
   // Time: O(n!), space: O(n)
   int[] array = new int[2 * k];
   int[] used = new int[k + 1];
 
   return helper(array, 0, used) ? array : null;
 }
}


272. Combinations For Telephone Pad I
public class Solution {
 private void helper(List<String> result, StringBuilder sb, int index, String[] map, char[] input) {
   if (index == input.length) {
     result.add(sb.toString());
     return;
   }
 
   String strAtCurLvl = map[input[index] - '0'];
   if (strAtCurLvl.length() == 0) {
     helper(result, sb, index + 1, map, input);
   } else {
     for (int i = 0; i < strAtCurLvl.length(); i++) {
       char cur = strAtCurLvl.charAt(i);
       sb.append(cur);
       helper(result, sb, index + 1, map, input);
       sb.deleteCharAt(sb.length() - 1);
     }
   }
 }
 
 public String[] combinations(int number) {
   // 看你输入几个数字，DSF的每一层是一个position
   // 比如 2 3 1, 有三层
 
   // Need to get 1 character from the string, put it into a StringBuilder
   // Go to the next level (position)
   // Stop when the level is larger than the length of the number
   // Time: O(3^number_length), space: O(number_length)
 
   // Get the length of the number
   char[] input = Integer.toString(number).toCharArray();
   // Get the string for the input digit
   String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
 
   List<String> result = new ArrayList<>();
   StringBuilder sb = new StringBuilder();
   helper(result, sb, 0, map, input);
 
   String[] str = new String[result.size()];
   str = result.toArray(str);
 
   return str;
 }
}
147. Restore IP Addresses
public class Solution {
 private void helper(List<String> result, StringBuilder sb, char[] ip, int level, int digit) {
   // Base case
   if (level == 4) {
     // 这里需要特别注意，因为还包括了多加入的点'.'
     if (sb.length() == ip.length + 4) {
       // 要把最后多加的那个点去掉
       result.add(sb.substring(0, sb.length() - 1));
     }
     return;
   }
 
   // 1 digit
   if (digit < ip.length) {
     sb.append(ip[digit]).append('.');
     helper(result, sb, ip, level + 1, digit + 1);
     sb.delete(sb.length() - 2, sb.length());
   }
 
   // 2 digits
   if (digit + 1 < ip.length) {
     char a = ip[digit];
     char b = ip[digit + 1];
     // 一个section里面不能出现 .08. 这样的组合，只能是 .8.
     if (a != '0') {
       sb.append(a).append(b).append('.');
       helper(result, sb, ip, level + 1, digit + 2);
       sb.delete(sb.length() - 3, sb.length());
     }
   }
 
   // 3 digits
   if (digit + 2 < ip.length) {
     char a = ip[digit];
     char b = ip[digit + 1];
     char c = ip[digit + 2];
     if (a == '1' || (a == '2' && b >= '0' && b <= '4') || (a == '2' && b == '5' && c <= '5')) {
       sb.append(ip[digit]).append(ip[digit + 1]).append(ip[digit + 2]).append('.');
       helper(result, sb, ip, level + 1, digit + 3);
       sb.delete(sb.length() - 4, sb.length());
     }
   }
 }
 
 public List<String> Restore(String ip) {
   // 0~255?
   // 4 section -> 4 layer in the DSF -> each layer can be 1-digit, 2-digit, 3-digit numbers, but the number should be less than 255
   // 255255
   // 1st layer: 2, 25, 255
   // 1st layer: 2 -> 2nd layer: 5, 55
   // Need an index to record the currect digit. If in the end the current index is still smaller than the number length, throw it
   // Time: O(3^4), space: O(4)
 
   // Convert ip string to char array
   char[] ipArray = ip.toCharArray();
   List<String> result = new ArrayList<>();
   StringBuilder sb = new StringBuilder();
 
   helper(result, sb, ipArray, 0, 0);
   return result;
 }
}

Cross Training III
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
