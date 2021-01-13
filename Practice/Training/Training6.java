723. Candy Crush
class Solution {
   private void markZero(int[][] board, boolean[][] delete) {
       for (int i = 0; i < board.length; i++) {
           for (int j = 0; j < board[0].length; j++) {
               if (delete[i][j]) {
                   board[i][j] = 0;
               }
           }
       }
   }
  
   private void drop(int[][] board, int col, int from, int to) {
       if (from < 0) {
           return;
       }
      
       int fromCur = from;
       int toCur = to;
       while (fromCur >= 0) {
           board[toCur][col] = board[fromCur][col];
           board[fromCur][col] = 0;
           fromCur--;
           toCur--;
       }
   }
  
   private void clearDelete(boolean[][] delete) {
       for (int i = 0; i < delete.length; i++) {
           for (int j = 0; j < delete[0].length; j++) {
               delete[i][j] = false;
           }
       }
   }
  
   public int[][] candyCrush(int[][] board) {
       // 1. layer by layer from top to bottom, from left to right
       // remove all consecutive letter larger than three
       /*
       4 2 4 5 2
       1 2 1 5 2
       2 3 1 1 1
       9 1 1 1 3
 
       0 0 0 0 0
       0 0 1 0 0
       0 0 1 1 1
       0 1 1 1 0
       */
      
       int row = board.length;
       int col = board[0].length;
       boolean[][] delete = new boolean[row][col];
      
       while (true) {
           clearDelete(delete);
           int countDelete = 0;
 
           for (int i = 0; i < row; i++) {
               for (int j = 0; j < col; j++) {
                   if (board[i][j] == 0) {
                       continue;
                   }
                   // current position: [i, j]
                   // (a) check rightward first
                   int slow = j;
                   int fast = j + 1;
                   while (fast < col && board[i][fast] == board[i][slow]) {
                       fast++;
                   }
                   if (fast - slow >= 3) {
                       while (slow < fast) {
                           delete[i][slow] = true;
                           countDelete++;
                           slow++;
                       }
                   }
 
                   // (b) check downward
                   slow = i;
                   fast = i + 1;
                   while (fast < row && board[fast][j] == board[slow][j]) {
                       fast++;
                   }
                   if (fast - slow >= 3) {
                       while (slow < fast) {
                           delete[slow][j] = true;
                           countDelete++;
                           slow++;
                       }
                   }
               }
           }
          
           if (countDelete == 0) {
               return board;
           }
 
           // 2. Determine which needs to be dropped
           // Check from bottom to top to find empty space
 
           // Mark the 'delete' position as 0 in board
           markZero(board, delete);
           // Drop
           for (int j = 0; j < col; j++) {
               for (int i = row - 1; i >= 0; i--) {
                   if (board[i][j] == 0) {
                       int cur = i - 1;
                       while (cur >= 0 && board[cur][j] == 0) {
                           cur--;
                       }
                       // drop all above cur to j at column j
                       drop(board, j, cur, i);
                   }
               }
           }
       }
   }
}
266. Palindrome Permutation
class Solution {
   public boolean canPermutePalindrome(String s) {
       // Use a HashMap to store the occurence of the string s
       // All should be even except only one odd
       // Time: O(n)
       // Space: O(1)
       if (s.length() == 0) {
           return true;
       }
      
       Map<Character, Integer> map = new HashMap<>();
       for (int i = 0; i < s.length(); i++) {
           if (!map.containsKey(s.charAt(i))) {
               map.put(s.charAt(i), 1);
           } else {
               map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
           }
       }
      
       // Scan the map
       int flag = 0;  // count the number of odd
       for (Map.Entry<Character, Integer> e : map.entrySet()) {
           if (e.getValue() % 2 == 1) {
               if (flag != 0) {
                   return false;
               }
               flag++;
           }
       }
       return true;
   }
}
253. Meeting Rooms II
class Solution {
   /*
   0: 0                    30
   1:    5     15  // new room
   2:       10    20  // new room
   3:            18   25  // room 1
            
   1. Sort by starting time
   2. Every time looking into a new room, check all earlier used rooms and see if there is an empty
      room
     
   Time: O(n^2)  // n: # meetings
   Space: O(n)
  
  
   Instead of looking into the end time of ALL earlier meeting, we can use a minHeap to record
   all the earlier meetings' end time
   if (minHeap.peek() < currentInterval start time)
       minHeap.pop();
   minHeap.offer(currentInterval end time);
  
   return size of minHeap
  
   Time: O(n)
   Space: O(n)
   */
   static class MyComparator implements Comparator<Integer> {
       @Override
       public int compare(Integer c1, Integer c2) {
           if (c1.equals(c2)) {
               return 0;
           }
           return c1 < c2 ? -1 : 1;
       }
   }
  
   static class IntervalComparator implements Comparator<int[]> {
       @Override
       public int compare(int[] c1, int[] c2) {
           if (c1[0] == c2[0]) {
               return 0;
           }
           return c1[0] < c2[0] ? -1 : 1;
       }
   }
  
   public int minMeetingRooms(int[][] intervals) {
       // Assume not null
      
       PriorityQueue<Integer> minHeap = new PriorityQueue<>(new MyComparator());
      
       // Sort intervals by starting time
       Arrays.sort(intervals, new IntervalComparator());
      
      
       for (int[] interval : intervals) {
           if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
               minHeap.poll();
           }
           minHeap.offer(interval[1]);
       }
       return minHeap.size();
   }
}
451. Sort Characters By Frequency
class Solution {
   static class Cell {
       char ch;
       int count;
       Cell(char ch, int count) {
           this.ch = ch;
           this.count = count;
       }
   }
  
   public class MyComparator implements Comparator<Cell> {
       public int compare(Cell c1, Cell c2) {
           return c1.count >= c2.count ? -1 : 1;
       }
   }
  
   public String frequencySort(String s) {
       /*
       Input string
       aabssst
      
       1. HashMap Time: O(n), space: O(n)
       abst
       2131
      
       2. maxHeap Time: O(n), space: O(n)
       tbas
       1123
      
       3. poll and put into StringBuilder Time: O(n), space: O(n)
       */
      
       // 1. HashMap<char, count>
       Map<Character, Integer> map = new HashMap<>();
       for (int i = 0; i < s.length(); i++) {
           char cur = s.charAt(i);
           if (!map.containsKey(cur)) {
               map.put(cur, 1);
           } else {
               map.put(cur, map.get(cur) + 1);
           }
       }
      
       // 2. Build maxHeap
       PriorityQueue<Cell> maxHeap = new PriorityQueue<>(new MyComparator());
       for (Map.Entry<Character, Integer> e : map.entrySet()) {
           maxHeap.offer(new Cell(e.getKey(), e.getValue()));
       }
      
       // 3. Build result
       StringBuilder sb = new StringBuilder();
       while (!maxHeap.isEmpty()) {
           Cell cur = maxHeap.poll();
           for (int i = 0; i < cur.count; i++) {
               sb.append(cur.ch);
           }
       }
      
       return sb.toString();
   }
}
49. Group Anagrams
class Solution {
   public List<List<String>> groupAnagrams(String[] strs) {
       // 1. Sort each String and generate hashcode mapping to its position in the result list
       List<List<String>> result = new ArrayList<List<String>>();
      
       // map string to position in the result list
       Map<String, Integer> map = new HashMap<>();
      
       for (String str : strs) {
           int index = 0;
           char[] array = str.toCharArray();
           Arrays.sort(array);
           String sortedString = new String(array);
           if (!map.containsKey(sortedString)) {
               index = result.size();
               map.put(sortedString, index);
               result.add(new ArrayList<String>());
           } else {
               index = map.get(sortedString);
           }
           result.get(index).add(str);
       }
       return result;
   }
}
79. Word Search
class Solution {
   static private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
   public boolean exist(char[][] board, String word) {
       /* 使用DSF对每一个grid search
       DSF限制条件：
       1. 不能超过grid的边界
       2. 如果当前的字母和word当前的字母不同，则false
       3. 深度不能超过word的长度
      
       DSF:
       四个方向进行寻找，任何一个返回true，则返回true
      
       Time: search O(4^word.len * m * n)
       Space: word length
       */
      
       // start search for all positions in the board
       for (int i = 0; i < board.length; i++) {
           for (int j = 0; j < board[0].length; j++) {
               if (search(board, word, i, j, 0)) {
                   return true;
               }
           }
       }
       return false;
   }
  
   private boolean search(char[][] board, String word, int r, int c, int index) {
       if (index >= word.length()) {
           return true;
       }
      
       if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
           return false;
       }
      
       if (board[r][c] != word.charAt(index)) {
           return false;
       }
      
       // mark parent，不能又返回到parent了
       char cur = board[r][c];
       board[r][c] = '0';
       for (int[] dir : dirs) {
           if (search(board, word, r + dir[0], c + dir[1], index + 1)) {
               return true;
           }
       }
       board[r][c] = cur;
       return false;
   }
}
431. Word Search II (unfinished)
public class Solution {
 static class TrieNode {
    public TrieNode[] children;
    public String word;
    public TrieNode() {
      children = new TrieNode[26];
      word = null;
    }
  }
 
 public List<String> findWords(char[][] board, String[] words) {
   TrieNode root = buildTrie(words);
   /*
   总体思路就是在board里面参照着trie的路径走，trie里没有的DSF不走，返回
   */
   List<String> result = new ArrayList<>();
   for (int i = 0; i < board.length; i++) {
     for (int j = 0; j < board[0].length; j++) {
       walkTrie(board, i, j, root, result);
     }
   }
   return result;
 }
 
 private void walkTrie(char[][] board, int r, int c, TrieNode curTrieNode, List<String> result) {
   // 1. return when current index in the board is out of bound or current char was visited
   if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
     return;
   }
   if (board[r][c] == '0') {  // '0' means visited or unvalid
     return;
   }
 
   char curBoardChar = board[r][c];
   TrieNode next = curTrieNode.children[curBoardChar - 'a'];
   // 如果DSF走的路径不在Trie里面，则不继续往下走里，prune
   if (next == null) {
     return;
   }
 
   // 如果Trie里面碰到里word != null, 则这个word在DSF路径里面被找到了
   if (next.word != null) {
     result.add(next.word);
     next.word = null;  // 这个word已经被访问过了
   }
 
   board[r][c] = '0';
   walkTrie(board, r - 1, c, next, result);
   walkTrie(board, r + 1, c, next, result);
   walkTrie(board, r, c - 1, next, result);
   walkTrie(board, r, c + 1, next, result);
   board[r][c] = curBoardChar;
 }
 
 private TrieNode buildTrie(String[] words) {
   // Build Trie
   TrieNode root = new TrieNode();  // 第一个是空的
   for (String word : words) {
     // 从trie的root往下找
     TrieNode cur = root;
     for (char c : word.toCharArray()) {
       TrieNode next = cur.children[c - 'a'];
       if (next == null) {
         next = new TrieNode();
       }
       cur = next;
     }
     // 每一个TrieNode里面需要有一个word来判断这个node是不是一个word的终止
     cur.word = word;
   }
   return root;
 }
}

1011. Capacity To Ship Packages Within D Days
class Solution {
   private int sumWeights(int[] weights, int[] max) {
    int sum = 0;
    for (int i = 0; i < weights.length; i++) {
      if (weights[i] > max[0]) {
        max[0] = weights[i];
      }
      sum += weights[i];
    }
    return sum;
  }
  
  public int shipWithinDays(int[] weights, int D) {
       // 使用二分法
    // weights is sorted
    
    // 船载重量不可能小于最大的包裹重量
    int[] max = new int[] {Integer.MIN_VALUE};
    int right = sumWeights(weights, max);  // maximum capacity
    int left = max[0];
    int finalCapacity = 0;
    
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int shippingDays = getShippingDay(mid, weights);
      if (shippingDays > D) {
        // make the capacity larger
        left = mid + 1;
      } else {
        right = mid - 1;
        finalCapacity = mid;  // we want minimum capacity, we do not return
      }
    }
    return finalCapacity;
   }
  
  private int getShippingDay(int capacity, int[] weights) {
    // Time: O(n)
    // 1 2 3 4 5 6 7 8 9 10
    // capacity = 32
    int days = 1;
    int weightsOnTheShip = 0;
    for (int i = 0; i < weights.length; i++) {
      if (weightsOnTheShip + weights[i] <= capacity) {
        weightsOnTheShip += weights[i];
      } else {
        // weight is already larger than the capacity
        weightsOnTheShip = weights[i];
        days++;
      }
    }
    return days;
  }
}
