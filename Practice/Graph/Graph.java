27. Kth Smallest Sum In Two Sorted Arrays
public class Solution {
 private static class MyComparator implements Comparator<Cell> {
   public int compare(Cell c1, Cell c2) {
     if (c1.value == c2.value) {
       return 0;
     }
     return c1.value < c2.value ? -1 : 1;
   }
 }
 
 private class Cell {
   public int x;
   public int y;
   public int value;
   public Cell(int x, int y, int value) {
     this.x = x;
     this.y = y;
     this.value = value;
   }
 }
 
 public int kthSum(int[] A, int[] B, int k) {
   /*
   A and B are sorted
   A = [1, 2, 7]
   B = [4, 8, 9]
   k = 3
 
       1       3      7
 
 4     5(1)   7(2)    11
 
 8     9(3)    11     15
 
 9     10      12     16
 
  Use a min heap to store the contour of when pushing outward
  Expand heap.poll()
 
  Time: O(k) + O(logk)
  Space (length of contour): O(n)
 */
 
   // Build min Heap
   PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new MyComparator());
 
   // create visited matrix to record visited position
   boolean[][] visited = new boolean[A.length][B.length];
 
   int x = 0;
   int y = 0;
   minHeap.offer(new Cell(x, y, A[x] + B[y]));
   visited[x][y] = true;
 
   // Should be k - 1, kth smallest should be among the k - 1th smallest children
   for (int i = 0; i < k - 1; i++) {
     Cell cur = minHeap.poll();
    
     x = cur.x + 1;
     y = cur.y;
     if (x < A.length && y < B.length && !visited[x][y]) {
       minHeap.offer(new Cell(x, y, A[x] + B[y]));
       visited[x][y] = true;
     }
 
     x = cur.x;
     y = cur.y + 1;
     if (x < A.length && y < B.length && !visited[x][y]) {
       minHeap.offer(new Cell(x, y, A[x] + B[y]));
       visited[x][y] = true;
     }
   }
 
   return minHeap.peek().value;
 }
}
681. Seven Puzzle
public class Solution {
   final static private int[][] DIR = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
 
    static class Board {
      public int[][] v;
      public int rowOfZero;
      public int colOfZero;
 
      public Board (int[] values) {
        v = new int[2][4];
        int count = 0;
        for (int i = 0; i < 2; i++) {
          for (int j = 0; j < 4; j++) {
            v[i][j] = values[count++];
          }
        }
        // Initialize zero position
        findZeroPosition();
      }
 
      public Board (Board input) {
        v = new int[2][4];
        for (int i = 0; i < 2; i++) {
          for (int j = 0; j < 4; j++) {
            this.v[i][j] = input.v[i][j];
          }
        }
        // Initialize zero position
        findZeroPosition();
      }
 
      public void setZeroPosition(int rowOfZero, int colOfZero) {
        this.rowOfZero = rowOfZero;
        this.colOfZero = colOfZero;
      }
 
      public void findZeroPosition() {
        for (int i = 0; i < v.length; i++) {
          for (int j = 0; j < v[0].length; j++) {
            if (v[i][j] == 0) {
              this.rowOfZero = i;
              this.colOfZero = j;
            }
          }
        }
      }
 
      public boolean equals(Board input) {
        for (int i = 0; i < 2; i++) {
          for (int j = 0; j < 4; j++) {
            if (this.v[i][j] != input.v[i][j]) {
              return false;
            }
          }
        }
        return true;
      }
 
      public int hashCode() {
        int code = 0;
        for (int i = 0; i < 2; i++) {
          for (int j = 0; j < 4; j++) {
            code = code * 10 + v[i][j];
          }
        }
        return code;
      }
    }
 
 
    private void swap(Board board, int oldX, int oldY, int newX, int newY) {
      int temp = board.v[oldX][oldY];
      board.v[oldX][oldY] = board.v[newX][newY];
      board.v[newX][newY] = temp;
      board.rowOfZero = newX;
      board.colOfZero = newY;
    }
 
    public int numOfSteps(int[] values) {
      /*
      棋盘是一个class, 每一个棋盘摆法就是一个object，需要用一个set来记录是否已经visit
      目标是从给定的摆法能够到达正确的摆法，所以使用BFS，每一步是一层
      0的移动位置有上下左右四个, swap之后就offer到Queue里面
      在BFS过程中一旦当前摆法和正确的摆法相同的话就返回步数
      Time: O(V + E) = O(8!) + O(8! * 4)
      */
 
      // Need a set to recorded the visited board config
      Set<Board> visited = new HashSet<>();
 
      // Correct board config
      Board correct = new Board(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
 
      // Need a queue for BFS
      Queue<Board> queue = new ArrayDeque<>();
      Board start = new Board(values);
      visited.add(start);
      queue.offer(start);
      int step = 0;
      while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
          Board cur = queue.poll();
          if (cur.equals(correct)) {
            return step;
          }
          int curX = cur.rowOfZero;
          int curY = cur.colOfZero;
          for (int[] dir : DIR) {
            int newX = curX + dir[0];
            int newY = curY + dir[1];
            if (newX < 0 || newX >= 2 || newY < 0 || newY >= 4) {
              // Out of bound
              continue;
            }
            // swap current zero to a new place
            swap(cur, curX, curY, newX, newY);
 
            // Check if we have visited the expanded child
            if (!visited.contains(cur)) {
              Board newBoard = new Board(cur);
              queue.offer(newBoard);
              visited.add(newBoard);
            }
            // swap back
            swap(cur, newX, newY, curX, curY);
          }
        }
        step++;
      }
      return -1;
    }
}
661. Word Ladder
public class Solution {
 private Set<String> buildSet(List<String> wordList) {
    Set<String> wordSet = new HashSet<>();
    for (String word : wordList) {
      wordSet.add(word);
    }
   return wordSet;
  }
 
 public int ladderLength(String beginWord, String endWord, List<String> wordList) {
   // Use BFS
 
    // Create a HashSet to store all words
    Set<String> wordSet = buildSet(wordList);
 
    // Create a queue for BFS
    Queue<String> queue = new ArrayDeque<>();
 
    // Current level in the BFS (position in the word)
    int step = 0;
 
    // Start BFS
    queue.offer(beginWord);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String curWord = queue.poll();
        // check if curWord match the endWord
        if (curWord.equals(endWord)) {
          return step + 1;
        }
 
        // Check for all available words one step away from ‘curWord’
	  // 不能使用根据每个position来做为一层！！！可能会在wordList中找不到
        StringBuilder modifiedCurWord = new StringBuilder(curWord);
        for (int j = 0; j < modifiedCurWord.length(); j++) {
          char originalChar = curWord.charAt(j);
          for (char c = 'a'; c <= 'z'; c++) {
            if (c == originalChar) {
              continue;
            }
            modifiedCurWord.setCharAt(j, c);
            String newWord = modifiedCurWord.toString();
            if (wordSet.contains(newWord)) {
              // Expand the new word
              queue.offer(newWord);
              // Remove newWord from set since it is visited
              wordSet.remove(newWord);
            }
          }
          modifiedCurWord.setCharAt(j, originalChar);
        }
      }
      step++;
    }
    return 0;
 }
}
417. Course Schedule
public int[] findOrder(int numCourses, int[][] prerequisites) {
    // Tow status during traversing
    // 1. visiting: not yet finished. Once encounter visiting node, there is a cycle
    // 2. visited: finished. Once encounter visited node, return
    
    // Convert prerequisites to graph
    List<List<Integer>> graph = new ArrayList<List<Integer>>();
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<Integer>());
    }
    
    // Edge representation for graph, suitable for directed graph
    for (int[] pre : prerequisites) {
      graph.get(pre[1]).add(pre[0]);
    }
    
    // Visit status for each node
    List<Integer> order = new ArrayList<>();
    int[] visit = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      // start traverse from each node, determine if it has cycle or not
      if (!dfs(graph, visit, order, i)) {
        return new int[] {};
      }
    }
    
    int[] array = new int [order.size()];
      for (int i = 0; i < array.length; i++) {
        array[i] = order.get(array.length - i - 1);
      }
      return array;
  }
  
  private boolean dfs(List<List<Integer>> graph, int[] visit, List<Integer> order, int curNodeLabel) {
    if (visit[curNodeLabel] == 1) {
      // visting
      return false;
    }
    if (visit[curNodeLabel] == 2) {
      // visited
      return true;
    }
    
    // current node's edge list
    List<Integer> curEdges = graph.get(curNodeLabel);
    
    // VISITING
    visit[curNodeLabel] = 1;
    for (int neighbor : curEdges) {
      if (!dfs(graph, visit, order, neighbor)) {
        return false;
      }
    }
    // VISITED
    visit[curNodeLabel] = 2;
    
    // Put the visited node into the result ordered list
    order.add(curNodeLabel);
    
    return true;
  }


public class Solution {
 public boolean canFinish(int numCourses, int[][] prerequisites) {
   // Convert prerequisites to graph
   List<List<Integer>> graph = new ArrayList<List<Integer>>();
   int[] visitStatus = new int[numCourses];
   // 有两个状态！
   /*
   Main idea:
   if (curNode == visiting) return cycle;
   if (curNode == visited) return OK
 
   mark as visting
   DSF(...)
   mark as visited
 
   visiting: 访问neighbor之前把neightbor标记为visiting (1)
             如果访问到visiting的node则有cycle
   visited:  访问结束后标记为visited
   */
 
   for (int i = 0; i < numCourses; i++) {
     graph.add(new ArrayList<Integer>());
   }
 
   // for (int i = 0; i < numCourses; ++i)
   //         graph.add(new ArrayList<Integer>());
 
   for (int[] c : prerequisites) {
     graph.get(c[1]).add(c[0]);
   }
 
   for (int i = 0; i < numCourses; i++) {
     if (!DSF(graph, i, visitStatus)) {
       return false;
     }
   }
   return true;
 }
 
 private boolean DSF(List<List<Integer>> graph, int index, int[] visitStatus) {
   if (visitStatus[index] == 1) {
     return false;
   } else if (visitStatus[index] == 2) {
     return true;
   }
 
   List<Integer> nextCourses = graph.get(index);
   visitStatus[index] = 1;
   for (int c : nextCourses) {
     if (!DSF(graph, c, visitStatus)) {
       return false;
     }
   }
   visitStatus[index] = 2;
 
   return true;
 }
}
430. Course Schedule II (topological sort)
public class Solution {
 private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
   // initialization
   List<List<Integer>> graph = new ArrayList<List<Integer>>();
   for (int i = 0; i < numCourses; i++) {
     graph.add(new ArrayList<Integer>());
   }
   // build edge list
   for (int[] pair : prerequisites) {
     graph.get(pair[1]).add(pair[0]);
   }
   return graph;
 }
 
 private boolean isConflict(List<List<Integer>> graph, int[] visited, int index, List<Integer> result) {
   if (visited[index] == 1) {
     return true;  // is conflict
   } else if (visited[index] == 2) {
     return false;  // visited, not conflict
   }
 
   // change unvisited to visiting
   visited[index] = 1;
 
   List<Integer> curList = graph.get(index);
   for (Integer nextClass : curList) {
     if (isConflict(graph, visited, nextClass, result)) {
       return true;
     }
   }
 
   // no cycle, label currect node to visited
   visited[index] = 2;
   result.add(index);
 
   return false;
 }
 
 public int[] findOrder(int numCourses, int[][] prerequisites) {
   /*
   Create a graph edge list
   */
   List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
 
   // start from every node and do DSF
   // need a visited array 0: not visited, 1: visiting, 2: visited
   int[] visited = new int[numCourses];
 
   List<Integer> result = new ArrayList<>();
 
   for (int i = 0; i < graph.size(); i++) {
     if (isConflict(graph, visited, i, result)) {
       return new int[0];
     }
   }
  
   int[] array = new int[result.size()];
   int i = array.length - 1;
   for (int num : result) {
     array[i--] = num;
   }
   return array;
 }
}
 

public class Solution {
 public int[] findOrder(int numCourses, int[][] prerequisites) {
   /*
   num = 4
   [[1,0],[2,0],[3,1],[3,2]]
   0: [1, 2]
   1: [3]
   2: [3]
   3: []    // the final course
   4: []    // the final course
  
   Finish all prerequisite courses means
   ALL INCOMING EDGES HAVE BEEN VISITED!!!
 
   Time: O(V + E)
   */
  
   // Convert int[][] to graph representation
   List<List<Integer>> graph = new ArrayList<List<Integer>>();
   for (int i = 0; i < numCourses; i++) {
     graph.add(new ArrayList<Integer>());
   }
 
   for (int[] pair : prerequisites) {
     graph.get(pair[1]).add(pair[0]);
   }
 
   // Find starting node and start traverse
   // Goal is the incoming edges for all nodes are visited
   int[] incomingEdges = new int[numCourses];
   for (List<Integer> list : graph) {
     for (Integer node : list) {
       incomingEdges[node]++;
     }
   }
 
   // Start traversing from node without incomming edges
   int[] order = new int[numCourses];
   Queue<Integer> queue = new ArrayDeque<>();
   for (int i = 0; i < numCourses; i++) {
     if (incomingEdges[i] == 0) {
       queue.offer(i);
     }
   }
 
   int endPosition = helper(queue, graph, incomingEdges, order);
   return endPosition == numCourses ? order : new int[] {};
 }
 
 private int helper(Queue<Integer> queue, List<List<Integer>> graph, int[] incomingEdges, int[] order) {
   // BFS
   // We can start traversing from a node ONLY when ALL incoming edges of this node have been visited: incomingEdges[i] == 0
   // All prerequisite courses should be finished to move on to next course
 
   int i = 0;
   while (!queue.isEmpty()) {
     // Add the course into order
     order[i] = queue.poll();
     List<Integer> nextCourse = graph.get(order[i]);
     i++;
     for (Integer c : nextCourse) {
       incomingEdges[c]--;
       if (incomingEdges[c] == 0) {
         queue.offer(c);
       }
     }
   }
 
   return i;
 }
}
501. Alien Dictionary
public class Solution {
 public String alienOrder(String[] words) {
   int[] indegree = new int[26];
   // HashMap
   Map<Character, Set<Character>> graph = new HashMap<>();
   buildGraph(graph, words, indegree);
   return bfs(graph, indegree);
 }
 
 private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
   StringBuilder sb = new StringBuilder();
   Queue<Character> queue = new ArrayDeque<>();
   int totalChars = graph.size();
   // 初始化队列，offer indegree为零的字母
   // 把indegree为0的node先添加到StringBuilder中
   for (char c : graph.keySet()) {
     if (indegree[c - 'a'] == 0) {
       sb.append(c);
       queue.offer(c);
     }
   }
 
   while (!queue.isEmpty()) {
     char cur = queue.poll();
     if (graph.get(cur) == null || graph.get(cur).size() == 0) {
       // 当前node没有出度
       continue;
     }
     // 遍历当前node的所有出度
     for (char next : graph.get(cur)) {
       indegree[next - 'a']--;
       if (indegree[next - 'a'] == 0) {
         queue.offer(next);
         sb.append(next);  // indegree为零了所以添加到StringBuilder中
       }
     }
   }
 
   return sb.length() == totalChars ? sb.toString() : "";
 }
 
 private void buildGraph(Map<Character, Set<Character>> graph, String[] words, int[] indegree) {
   // 初始化graph，给每一个node添加一个Set
   for (String word : words) {
     for (char c : word.toCharArray()) {
       graph.putIfAbsent(c, new HashSet<>());
     }
   }
  
   for (int i = 1; i < words.length; i++) {
     String first = words[i - 1];
     String second = words[i];
     int minLen = Math.min(first.length(), second.length());
     for (int j = 0; j < minLen; j++) {
       if (first.charAt(j) != second.charAt(j)) {
         char out = first.charAt(j);
         char in = second.charAt(j);
         if (!graph.get(out).contains(in)) {
           graph.get(out).add(in);  // 是一个edge list，从out到in
           indegree[in - 'a']++;
         }
         break;  // 后面的顺序就没有意义了
         /*
         0 1 2 3 4 5
         a b c e j k
         a b c g j k
         index == 3的时候就要break
         */
       }
     }
   }
 }
}
994. Rotting Oranges (leetcode)
class Solution {
   /*
   2 1 1
   0 1 1
   1 0 1
  
   */
   static class Cell {
       int x = 0;
       int y = 0;
       Cell(int x, int y) {
           this.x = x;
           this.y = y;
       }
   }
  
   private boolean outOfBound(int[][] grid, int x, int y) {
       if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
           return true;
       }
       return false;
   }
  
   private boolean areAllRotten(int[][] grid) {
       for (int i = 0; i < grid.length; i++) {
           for (int j = 0; j < grid[0].length; j++) {
               if (grid[i][j] == 1) {
                   return false;
               }
           }
       }
       return true;
   }
  
   public int orangesRotting(int[][] grid) {
       // Use BFS
       // Only one rotten orange in the grid? No
       // Every time you expand from the rotten tomato and mark the fresh to rotten
       // The total layer of the BFS is the time
       // Time: O(number of orange)
       // Space: O(n)
      
       // BFS, so we need a queue
       Queue<Cell> queue = new ArrayDeque<>();
      
       // Four directions
       int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
      
       // initial status of the orange
       for (int i = 0; i < grid.length; i++) {
           for (int j = 0; j < grid[0].length; j++) {
               if (grid[i][j] == 2) {
                   queue.offer(new Cell(i, j));
               }
           }
       }
      
       // BFS starts
       int time = 0;
       boolean expanded;
       while (!queue.isEmpty()) {
           int size = queue.size();
           expanded = false;
           for (int i = 0; i < size; i++) {
               Cell cur = queue.poll();
               // Expand only the adjacent orange is fresh (1)
               // Also check the boundary
               for (int[] dir : directions) {
                   int nextX = cur.x + dir[0];
                   int nextY = cur.y + dir[1];
                   if (!outOfBound(grid, nextX, nextY) && grid[nextX][nextY] == 1) {
                       grid[nextX][nextY] = 2;  // mark fresh to rotten
                       queue.offer(new Cell(nextX, nextY));
                       expanded = true;
                   }
               }
           }
           if (expanded) {
               time++;
           }
       }
      
       return areAllRotten(grid) ? time : -1;
   }
}
1192. Critical Connections in a Network (leetcode)
class Solution {
   int id = 0;
 
   public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
       List<List<Integer>> res = new ArrayList<>();
       if(n < 1){
           return res;
       }
 
       List<Integer> [] graph = new ArrayList[n];
       for(int i = 0; i < n; i++){
           graph[i] = new ArrayList<>();
       }
 
       for(List<Integer> e : connections){
           graph[e.get(0)].add(e.get(1));
           graph[e.get(1)].add(e.get(0));
       }
 
       int [] ids = new int[n];
       Arrays.fill(ids, -1);
       int [] low = new int[n];
 
       for(int i = 0; i < n; i++){
           if(ids[i] == -1){
               dfs(i, low, ids, graph, res, -1);
           }
       }
 
       return res;
   }
 
   private void dfs(int u, int [] low, int [] ids, List<Integer> [] graph, List<List<Integer>> res, int parent){
       ids[u] = low[u] = ++id;
       List<Integer> neibors = graph[u];
 
       for(int v : neibors){
           if(v == parent){
               continue;
           }
 
           if(ids[v] == -1){
               dfs(v, low, ids, graph, res, u);
               low[u] = Math.min(low[u], low[v]);
 
               if(low[v] > ids[u]){
                   res.add(Arrays.asList(u, v));
               }
           }else{
               low[u] = Math.min(low[u], ids[v]);
           }
       }
   }
}
