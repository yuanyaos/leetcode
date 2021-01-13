Common API
Set<> HashSet
put(value)
contains(value)

Map<key, value> HashMap
put(key, value)
containsKey(key)
containsValue(value)
get(key)

String
67. Top K Frequent Words
public class Solution {
 private class MyComparator implements Comparator<Map.Entry<String, Integer>> {
      @Override
      public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
        Integer x = entry1.getValue();
        Integer y = entry2.getValue();
        if (x.equals(y)) {
          return 0;
        }
 
        return x < y ? -1 : 1;
      }
    }
 
    public String[] topKFrequent(String[] combo, int k) {
      // Write your solution here
      // 1. 先过一遍array，把字母和对应的count存到HashMap<String, Integer>里面 O(n)
      // 2. 把所有的HashMap entry过一遍，用minHeap把count最高的HashMap entry给选出来 O(k + (n-k)logk)
      // 3. 把每一个minHeap元素都依次poll出来并把count存入array中 O(k)
 
      Map<String, Integer> map = getFrequencyMap(combo);
 
      PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new MyComparator());
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        if (minHeap.size() < k) {
          minHeap.offer(entry);
        } else if (entry.getValue() > minHeap.peek().getValue()) {  //entry.getValue() > minHeap.peek().getValue()
          minHeap.poll();
          minHeap.offer(entry);
        }
      }
 
      return putFreqInArray(minHeap);
    }
 
    private String[] putFreqInArray(PriorityQueue<Map.Entry<String, Integer>> heap) {
      int size = heap.size();
      String[] result = new String[size];
      for (int i = 0; i < size; i++) {
        result[size - 1 - i] = heap.poll().getKey();
      }
      return result;
    }
 
    private Map<String, Integer> getFrequencyMap(String[] combo) {
      Map<String, Integer> map = new HashMap<>();
      for (String s : combo) {
        if (!map.containsKey(s)) {
          map.put(s, 1);
        } else {
          map.put(s, map.get(s) + 1);
        }
      }
      return map;
    }
}
 
public class Solution {
 // Comparator<Template>
 private class MyComparator implements Comparator<Map.Entry<String, Integer>> {
   @Override
   public int compare(Map.Entry<String, Integer> c1, Map.Entry<String, Integer> c2) {
     Integer t1 = c1.getValue();
     Integer t2 = c2.getValue();
     if (t1.equals(t2)) {
       return 0;
     }
     return t1 < t2 ? -1 : 1;
   }
 }
 
 private Map<String, Integer> getMap(String[] combo) {
   Map<String, Integer> map = new HashMap<>();
   for (String s : combo) {
     Integer count = map.get(s);
     if (count == null) {
       map.put(s, 1);
     } else {
       map.put(s, count + 1);
     }
   }
   return map;
 }
 
Second pass
 public String[] topKFrequent(String[] combo, int k) {
   // input: a string array and k for top k frequent word
   // output: a string array saving the top k strings
   // range of k? >=1
   // sorted? No
 
   // Idea: 1. use hashmap to record mapping from string to count. Time: O(n), Space: O(n)
   // Time: O(k + (n - k) * logk)
   // 2. traverse through every entry in the hashmap
   // 3. use priority queue to dynamically record the top k strings
   // Go through the combo array and maintain a priority queue with a size of k
 
   // Corner case
   if (combo.length == 0) {
     return new String[0];
   }
 
   // 1. Get hashmap
   Map<String, Integer> map = getMap(combo);
  
   // The entry of the hashmap should be the element in priority queue
   PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue(k, new MyComparator());
 
   // Traverse the hashmap and build priority queue
   for (Map.Entry<String, Integer> e : map.entrySet()) {
     if (minHeap.size() < k) {
       minHeap.offer(e);
     } else if (e.getValue() > minHeap.peek().getValue()) {
       minHeap.poll();
       minHeap.offer(e);
     }
   }
 
   // Transform min heap to array in a reverse way
   // Cannot make the size of result k because k can be larger than the distinct words in combo
   // Must use minHeap.size()
   String[] results = new String[minHeap.size()];
   for (int i = minHeap.size() - 1; i >= 0; i--) {
     results[i] = minHeap.poll().getKey();
   }
 
   return results;
 }
}
652. Common Numbers Of Two Sorted Arrays(Array version)
public class Solution {
 public List<Integer> common(int[] A, int[] B) {
   // Write your solution here
   // Use 2 pointers for each arrays
   // Space & Time: O(n)
   List<Integer> result = new ArrayList<>();
   int i = 0;
   int j = 0;
   while (i < A.length && j < B.length) {
     if (A[i] < B[j]) {
       i++;
     } else if (A[i] > B[j]) {
       j++;
     } else {
       result.add(A[i]);
       i++;
       j++;
     }
   }
   return result;
 }
}
 
 
395. Remove Certain Characters
public class Solution {
 public String remove(String input, String t) {
   // Write your solution here
   // Time:
 
   // Space: O(n)
   char[] charRemoved = input.toCharArray();
 
   // 1. Put all characters in t into a set
   // Time: O(k)
   // Space: O(k)
   Set<Character> set = new HashSet<>();
   for (int i = 0; i < t.length(); i++) {
     set.add(t.charAt(i));
   }
 
   // 2. Scan throught the input, use slow and fast pointer
   // Time: O(n)
   // Space: O(1)
   int slow = 0;
   for (int fast = 0; fast < charRemoved.length; fast++) {
     if (!set.contains(charRemoved[fast])) {
       charRemoved[slow] = charRemoved[fast];
       slow++;
     }
   }
 
   return new String(charRemoved, 0, slow);
 }
}
 
68. Missing Number I
public class Solution {
 public int missing(int[] array) {
   // Write your solution here
   // 1. Use hash set
   // 2. go through 1 to N to check which number is missing
   // Time: put in hash set O(n), scan from 1 to N: O(n). Total: O(n)
   // Space: O(n) hash set space
 
   HashSet<Integer> map = new HashSet<Integer>();
   // No duplicate number
   for (int i = 0; i < array.length; i++) {
     map.add(array[i]);
   }
 
   for (int i = 1; i <= map.size() + 1; i++) {
     if (!map.contains(i)) {
       return i;
     }
   }
   return -1;
 }
}
 
281. Remove Spaces
public class Solution {
 public String removeSpaces(String input) {
   // Assumption: input is not null
 
   // Write your solution here
   // Slow and fast pointer
   // 1. input[fast] is ' '
   //    1.1 slow == 0, do not do anything
   //    1.2 slow != 0
   //        1.2.1 input[slow - 1] == ' ', do not copy
   //        1.2.2 input[slow - 1] != ' ', copy
   // 2. input[fast] is not ' ', copy
   // Time: O(n)
   // Space: O(n)
 
   char[] array = input.toCharArray();
 
   int slow = 0;
   for (int fast = 0; fast < array.length; fast++) {
     if (array[fast] == ' ') {
       if (slow != 0 && array[slow - 1] != ' ') {
           array[slow++] = array[fast];
       }
     } else {  // array[fast] != ' '
       array[slow++] = array[fast];
     }
   }
 
   // Last one may be ' '
   if (slow > 0 && array[slow - 1] == ' ') {
     slow--;
   }
 
   return new String(array, 0, slow);
 }
}
 
79. Remove Adjacent Repeated Characters I
public class Solution {
 public String deDup(String input) {
   // Write your solution here
   // Space in input?
   if (input == null) {
     return null;
   } else if (input.length() == 0) {
     return "";
   }
 
   // Slow and fast pointer;
   char[] array = input.toCharArray();
   int slow = 1;
   for (int fast = 1; fast < array.length; fast++) {
     if (array[slow - 1] != array[fast]) {
       array[slow++] = array[fast];
     }
   }
 
   return new String(array, 0, slow);
 }
}
 
public class Solution {
 public String deDup(String input) {
   // Write your solution here
   // Use slow and fast pointers
   // Time: O(n), Space: O(n)
   if (input == null || input.isEmpty()) {
     return input;
   }
 
   char[] array = input.toCharArray();
   int slow = 0;
   int fast = 0;
   while (fast < array.length) {
     if (fast == slow) {
       fast++;
       continue;
     }
     if (array[fast] != array[slow]) {
       slow++;
       array[slow] = array[fast];
     }
     fast++;
   }
 
   return new String(array, 0, slow + 1);
 }
}
 
 
82. Remove Adjacent Repeated Characters IV
public class Solution {
 public String deDup(String input) {
   // Write your solution here
   if (input == null || input.length() <= 1) {
     return input;
   }
 
   // 用stack来存储deduplicated之后的结果
   // 在原有的string上模拟stack
   char[] array = input.toCharArray();
  
   int top = -1;  // top记录stack的top
   for (int i = 0; i < input.length(); i++) {
     if (top == -1 || array[top] != array[i]) {
       top++;
       array[top] = array[i];
     } else {
       top--;
       // 注意terminate条件，要停在最后一个重复字符的前面
       while (i < array.length - 1 && array[i] == array[i + 1]) {
         i++;
       }
     }
   }
 
   return new String(array, 0, top + 1);
 }
}
 
 
 
public class Solution {
 public String deDup(String input) {
   // Write your solution here
   // 用一个stack，依次扫过array，push到stack里
   // 类似消消乐，如果stack里遇到相同的就消除
   // Time: O(n), Space: O(n)
   if (input == null || input.isEmpty()) {
     return input;
   }
 
   char[] array = input.toCharArray();
   Deque<Character> stack = new ArrayDeque<>();
   for (int i = 0; i < array.length; i++) {
     if (!stack.isEmpty() && stack.peek().equals(array[i])) {
       if (i == array.length - 1 || array[i + 1] != array[i]) {
         stack.pop();
       }
     } else {
       stack.push(array[i]);
     }
   }
 
   int size = stack.size();
   for (int end = size - 1; end >= 0; end--) {
     array[end] = stack.pop();
   }
   return new String(array, 0, size);
 }
}
85. Determine If One String Is Another's Substring
public class Solution {
 public int strstr(String large, String small) {
   // Write your solution here
   // Time: O(k*n)
   // Space: O(1)
   if (small.length() > large.length()) {
     return -1;
   }
   if (small.length() == 0) {
     return 0;
   }
 
   for (int i = 0; i <= large.length() - small.length(); i++) {
     boolean flag = true;
     for (int j = 0; j < small.length(); j++) {
       if (small.charAt(j) != large.charAt(i + j)) {
         flag = false;
         break;
       }
     }
     if (flag == true) {
       return i;
     }
   }
   return -1;
 }
}
 

String II
396. Reverse String
public class Solution {
 public String reverse(String input) {
   // Write your solution here
   if (input.length() == 0) {
     return "";
   }
 
   char[] array = input.toCharArray();
   int i = 0;
   int j = array.length - 1;
   while (i <= j) {
     char temp = array[i];
     array[i] = array[j];
     array[j] = temp;
     i++;
     j--;
   }
 
   return new String(array);
 }
}
 
84. Reverse Words In A Sentence I
public class Solution {
 private void reverse(char[] arrayf, int i, int j) {
   while (i <= j) {
     char temp = array[i];
     array[i] = array[j];
     array[j] = temp;
     i++;
     j--;
   }
 }
 public String reverseWords(String input) {
   // Write your solution here
   // Flip-flip.
   // Space: O(n), Time: O(n)
   if (input == null || input.length() == 0) {
     return input;
   }
 
   char[] array = input.toCharArray();
   reverse(array, 0, array.length - 1);
 
   int slow = 0;
   for (int fast = 0; fast < array.length; fast++) {
     if (fast == 0 || array[fast - 1] == ' ') {
       slow = fast;
     }
     if (fast == array.length - 1 || array[fast + 1] == ' ') {
       reverse(array, slow, fast);
     }
   }
 
   return new String(array);
 }
}
397. Right Shift By N Characters
public class Solution {
 private void reverse(char[] array, int start, int end) {
   int i = start;
   int j = end;
   while (i <= j) {
     char temp = array[i];
     array[i] = array[j];
     array[j] = temp;
     i++;
     j--;
   }
   return;
 }
 public String rightShift(String input, int n) {
   // Write your solution here
   // "I love Google" method
   // abcdef -> abcd ef -> fe dcba -> ef abcd
   // Time: O(n)
   // Space: O(i)
   if (input.length() == 0 || n == 0) {
     return input;
   }
 
   char[] array = input.toCharArray();
   n = n % array.length;
   reverse(array, 0, array.length - 1);
   reverse(array, 0, n - 1);
   reverse(array, n, array.length - 1);
 
   return new String(array);
 }
}
 
65. All Permutations II
/*
The idea is to use DSF to find all permutations
One level -> one position
Current position can be filled with all remaining characters
e.g. 
   0 1 2 3 4 5 6
   a b c d e  f g (bolded: remaining characters)
 
position 3 can be d e f g
Thus:
for (int i = currentIndex; i < array.length; i++) {
  swap(array, currentIndex, i);
  DSF(array, index + 1, …...)
  swap(array, currentIndex, i)  // swap back
}
 
a1 a2 b -> a1 a2 b is the same as a2 a1 b
Need to avoid this case. Need to add HashSet at each level
If set.contains(array[i]), then skip
 
pos0:                     a b c d e f g     n
 
pos1:            abcdefg         abcdefg    n - 1
.
.
.           1
Time: O(n!)
Space: tree height: O(n), each O(n^2)
*/

public class Solution {
 private void swap(char[] array, int i, int j) {
 
 }
 
 private void helper(char[] array, int index, List<String> list) {
   // 1. Base case
   if (index == array.length) {
     list.add(new String(array));
     return;
   }
 
   Set<Character> used = new HashSet<>();
   for (int i = index; i < array.length; i++) {
     if (used.contains(array[i])) {
       continue;
     }
     used.add(array[i]);
     swap(array, index, i);
     helper(array, index + 1, list);
     swap(array, index, i);
   }
 }
 
 public List<String> permutations(String input) {
   // ****************** 重要 ******************
   // 整体思路采用swap-swap的方法
   // 每一层都必须有一个HashSet
   // 每一层代表char array中的第几个位置
   // a b b c d -> 五层;  a b c d d e f -> 七层
   // 每一层用一个HashSet可以避免同一个位置重复使用相同的字母，避免最后list里面存入重复的permutations
   // 空间复杂度：每一层都有HashSet (n 层), n, n-1, n-2, ..., 1 => n^2
   // 时间复杂度：n!
   char[] array = input.toCharArray();
   List<String> list = new ArrayList<>();
   helper(array, 0, list);
 
   return list;
 }
}
 
649. String Replace (basic)
public class Solution {
 public String replace(String input, String source, String target) {
   // Write your solution here
   // Use StringBuilder to append the substring
   // Time is O(n), space is O(n)
 
   // 012345678901234
   // hhappledogapple
   // hhcat
 
   StringBuilder sb = new StringBuilder();
   int fromIndex = 0;
   int toIndex = input.indexOf(source, fromIndex);
   // -1 means no match found
   while (toIndex != -1) {
     sb.append(input, fromIndex, toIndex);
     sb.append(target);
     fromIndex = toIndex + source.length();
     toIndex = input.indexOf(source, fromIndex);
   }
   sb.append(input, fromIndex, input.length());
   return sb.toString();
 }
}
 
 
public class Solution {
 private boolean equals(char[] s1, char[] s2, int start) {
   for (int i = 0; i < s2.length; i++) {
     if (s1[start + i] != s2[i]) {
       return false;
     }
   }
   return true;
 }
 
 private String replaceForShorter(char[] input, char[] source, char[] target) {
   // Time: O(k*n), k = source.length
   // Space: O(1)
   int slow = 0;
   int fast = 0;
   while (fast < input.length) {
     if (fast <= input.length - source.length && equals(input, source, fast)) {
       // copy
       for (int i = 0; i < target.length; i++) {
         input[slow] = target[i];
         slow++;
       }
       fast += source.length;
     } else {
       input[slow] = input[fast];
       slow++;
       fast++;
     }
   }
   return new String(input, 0, slow);
 }
 
 private String replaceForLonger(char[] input, char[] source, char[] target) {
   // From left to right, count the occurance
   // Extend the string using StringBuilder
   // Update from right to left
  
   List<Integer> index = getIndex(input, source);
 
   char[] result = new char[input.length + index.size() * (target.length - source.length)];
 
   int lastIndex = index.size() - 1;
   int slow = result.length - 1;
   int fast = input.length - 1;
   while (fast >= 0) {
     if (lastIndex >= 0 && fast == index.get(lastIndex)) {
       for (int i = 0; i < target.length; i++) {
         result[slow - target.length + 1 + i] = target[i];
       }
       slow -= target.length;
       fast -= source.length;
       lastIndex--;
     } else {
         result[slow] = input[fast];
         slow--;
         fast--;
     }
   }
   return new String(result);
 }
 
 private List<Integer> getIndex(char[] input, char[] source) {
   ArrayList<Integer> index = new ArrayList<Integer>();
   int i = 0;
   while (i <= input.length - source.length) {
     if (equals(input, source, i)) {
       index.add(i + source.length - 1);
       i += source.length;
     } else {
       i++;
     }
   }
   return index;
 }
 
 public String replace(String input, String source, String target) {
   // Write your solution here
   // We need two function to deal with 2 case:
   // 1. output is shorter and 2. output is longer
 
   char[] inputArray = input.toCharArray();
   char[] sourceArray = source.toCharArray();
   char[] targetArray = target.toCharArray();
   if (source.length() >= target.length()) {
     return replaceForShorter(inputArray, sourceArray, targetArray);
   } else {
     return replaceForLonger(inputArray, sourceArray, targetArray);
   }
 }
}
197. ReOrder Array
public class Solution {
 public int[] reorder(int[] array) {
   // 想办法把大的array问题转换为小的问题
   // 分两种情况讨论，array长度的一半是even or odd
   // 0 1 2 3 4 5 6 7 8 9
   // 1 2 3 4 5 A B C D E
   // mid = left + length / 2 = 5
   // lm = left + length / 4 = 2
   // rm = left + 3 * length / 4 = 7
 
   // reverse(lm, mid - 1)  // O(n/4)
   // reverse(mid, rm - 1)  // O(n/4)
   // reverse(lm, rm - 1)   // O(n/2)
   // 1 2 A B | 3 4 5 C D E  // 每一层都是O(n), 总共有O(logn)层, time: O(nlogn), space: O(logn)
   // reorder(left, left + 2 * (lm - left) - 1)
   // reorder(left + 2 * (lm - left), right)
 
   if (array.length % 2 == 0) {
     reorder(array, 0, array.length - 1);
   } else {
     reorder(array, 0, array.length - 2);
   }
 
   return array;
 }
 
 private void reorder(int[] array, int left, int right) {
   int length = right - left + 1;
   if (length <= 2) {
     // base case: 不需要再分割
     return;
   }
 
   int mid = left + length / 2;
   int lm = left + length / 4;
   int rm = left + 3 * length / 4;
 
   reverse(array, lm, mid - 1);
   reverse(array, mid, rm - 1);
   reverse(array, lm, rm - 1);
 
   reorder(array, left, left + 2 * (lm - left) - 1);
   reorder(array, left + 2 * (lm - left), right);
 
   return;
 }
 
 private void reverse(int[] array, int left, int right) {
   while (left <= right) {
     int temp = array[left];
     array[left] = array[right];
     array[right] = temp;
     left++;
     right--;
   }
 }
}
 
611. Compress String II
/*
abc
a1b1c1 不会发生
Assume compressed length is shorter than the original length
 
abccccdeeeee
abc4de5
 
0  1  2  3  4  5  6  7  8  9  10  11
a  b  c  c  c  c  d  e  e  e   e   e
a  b  c  4  d  e  5     第一步
copy from the end (don’t need new array):
_  _  a  1  b  1  c  4  d  1   e   5      第二步，从后面copy并加入'1'
*/
public class Solution {
 private String encode(char[] array) {
   // 1. encode重复数目大于等于2的字母
   int slow = 0;
   int fast = 0;
   int newLength = 0;  // 用来创建新数组
   while (fast < array.length) {
     // 1.1 遍历当前重复字母
     int begin = fast;
     while (fast < array.length && array[fast] == array[begin]) {
       fast++;
     }
     array[slow] = array[begin];
     slow++;  // 当前slow在字母下一个，也就是数字开始的地方
 
     // 1.2 拷贝重复数目大于等于2的字母
     if (fast - begin == 1) {
       newLength += 2;  // 对于没有重复的字母，要多加一个空间，如 a->a1
     } else {
       // len: digit length, NOT including the letter
       int len = copy(array, fast - begin, slow);
       slow += len;
       newLength += len + 1;  // 1 means the letter length
     }
   }
 
   // 2. encode只有一个字母的地方
   char[] newArray = new char[newLength];
   fast = slow - 1;
   slow = newLength - 1;
   while (fast >= 0) {
     // 拷贝数字
     if (Character.isDigit(array[fast])) {
       while (fast >= 0 && Character.isDigit(array[fast])) {
         newArray[slow] = array[fast];
         slow--;
         fast--;
       }
     } else {
       newArray[slow] = '1';
       slow--;
     }
     // 拷贝字母
     newArray[slow] = array[fast];
     slow--;
     fast--;
   }
   return new String(newArray);
 }
 
 private int copy(char[] array, int count, int index) {
   // 返回 digit length
   int len = 0;
   int temp = count;
   // Get the digit length
   while (temp > 0) {
     temp /= 10;
     len++;
     index++;
   }
 
   // 现在index在数字的下一个地方
   temp = count;
   while (temp > 0) {
     int digit = temp % 10;
     temp /= 10;
     index--;
     array[index] = (char)('0' + digit);
   }
   return len;
 }
 
 public String compress(String input) {
   // Write your solution here
   // 用inplace的方法实现，hashset也可以，但是需要额外空间
   if (input == null || input.length() == 0) {
     return input;
   }
 
   char[] array = input.toCharArray();
  
   return encode(array);
 }
}


253. Longest Substring Without Repeating Characters
public class Solution {
 public int longest(String input) {
   /*********** 需要用快慢指针，sliding window的方法 ***********/
   // slow记录substring的左边界，fast记录substring的右边界
   // 用一个hashset记录当前这个substring里面是不是有重复的字母
   // Clarify: not null
   // Only need to go through the array once, so time O(n)
   // Wrost case we save all characters in the hashset, so space O(n)
   if (input == "") {
     return 0;
   }
 
   int slow = 0;
   int fast = 0;
   int maxLength = 0;
   HashSet<Character> set = new HashSet<>();
   while (fast < input.length()) {
     if (set.contains(input.charAt(fast))) {
       set.remove(input.charAt(slow));
       slow++;
     } else {
       set.add(input.charAt(fast));
       fast++;
       maxLength = Math.max(maxLength, fast - slow);
     }
   }
   return maxLength;
 }
}
398. All Anagrams
// Sliding window with fixed size of sh.length()
// Need a ‘match’ variable to count the number of matched characters in the window. count can be negative and positive
public class Solution {
 private Map<Character, Integer> buildMap(String sh) {
   Map<Character, Integer> map = new HashMap<>();
   for (int i = 0; i < sh.length(); i++) {
     Integer count = map.get(sh.charAt(i));
     if (count == null) {
       map.put(sh.charAt(i), 1);
     } else {
       map.put(sh.charAt(i), count+1);
     }
   }
   return map;
 }
 
 public List<Integer> allAnagrams(String sh, String lo) {
   // 通过sliding window的方法只需要过一边string
   // 所以time: O(n), space: O(n)
   List<Integer> results = new ArrayList<>();
   if (lo == "" || sh.length() > lo.length()) {
     return results;
   }
 
   // 把sh的character放入hashmap中
   Map<Character, Integer> map = buildMap(sh);
   // 记录distinct character
   int match = 0;
   for (int i = 0; i < lo.length(); i++) {
     // 使用sliding window的方法，window长度为sh的长度
     // 右边+1，左边-1，更新hashmap里面对应字母的count
 
     // **************** 第一部分（右边界）：i代表的是window的右边界 ****************
     char right = lo.charAt(i);
     Integer count = map.get(right);
     if (count != null) {
       count--;
       map.put(right, count);
       // 当这个字母在map里面的count减少到0之后，说明这个字母的match已经结束
       if (count == 0) {
         match++;
       }
     }
 
     // **************** 第二部分（左边界）：如果i已经超过了window size，就需要开始考虑window左侧 ****************
     if (i >= sh.length()) {
       char left = lo.charAt(i - sh.length());
       count = map.get(left);
       // 如果左边界的字母存在于window中，由于需要把window向右移动一位，所以需要在hashmap中恢复这个被移除的字母的count，也就是count++
       if (count != null) {
         count++;
         map.put(left, count);
         // 如果这个字母从0变成1，则match需要-1，表示这个字母在hashmap中没有被消除
         if (count == 1) {
           match--;
         }
       }
     }
 
     // **************** 一旦match的数值和hashmap size相等，则说明有一个anagram match ****************
     if (match == map.size()) {
       results.add(i - sh.length() + 1);
     }
   }
   return results;
 }
}
625. Longest subarray contains only 1s
public class Solution {
 public int longestConsecutiveOnes(int[] nums, int k) {
      // 依旧使用sliding window, window里面允许有k个0
      // 要保证window里面的0的数量要小于等于k
     // time: O(n), space: O(1)
      int slow = 0;
      int fast = 0;
      int count = 0;
      int maxLength = 0;
     
      while (fast < nums.length) {
        if (nums[fast] == 0) {
          count++;
        }
        if (count > k) {
         // 一旦window里面0的数量大于k了就需要往右移动left boundary一步
          maxLength = Math.max(maxLength, fast - slow);
         // 注意：只有当slow的位置是0的时候才需要减少记录的0的数量，也就是count--
          if (nums[slow] == 0) {
            count--;
          }
          slow++;
        }
        fast++;
      }
     // post-processing, 注意处理最后的情况
      if (count <= k || nums[fast - 1] == 1) {
        maxLength = Math.max(maxLength, fast - slow);
      }
 
      return maxLength;
    }
}
 

public class Solution {
 public int longestConsecutiveOnes(int[] nums, int k) {
   // Write your solution here
   // 用sliding window来track
   int slow = 0;
   int fast = 0;
   int count = 0;
   int longest = 0;
   while (fast < nums.length) {
     if (nums[fast] == 1) {
       fast++;
       longest = Math.max(longest, fast - slow);
     } else if (count < k) {
       // nums[fast] == 0
       count++;
       fast++;
       longest = Math.max(longest, fast - slow);
     } else if (nums[slow] == 0) {
       slow++;
       count--;
     } else {
       slow++;
     }
   }
   return longest;
 }
}
 
public class Solution {
 public int longestConsecutiveOnes(int[] nums, int k) {
      // 依旧使用sliding window, window里面允许有k个0
      // 要保证window里面的0的数量要小于等于k
     // time: O(n), space: O(1)
     // Not null, 0 <=k <= array.length
   if (nums.length == 0) {
     return 0;
   }
 
    int slow = 0;
    int fast = 0;
    int remain = k;
    int longest = Integer.MIN_VALUE;
   int[] array = nums;
    while (fast < array.length) {
      if (array[fast] == 0) {
        if (remain > 0) {
          fast++;
          remain--;
          longest = Math.max(longest, fast - slow);
        } else {
          // Move the left boundary until remain > 0
          while (remain <= 0) {
            if (array[slow] == 0) {
              remain++;
            }
            slow++;
          }
        }
      } else {
        // array[fast] == 1
        fast++;
        longest = Math.max(longest, fast - slow);
      }
    }
    return longest;
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

