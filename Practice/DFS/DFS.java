62. All Subsets I
public class Solution {
 private void helper(List<String> list, String set, String subset, int index) {
   if (index == set.length()) {
     list.add(subset);
     return;
   }
   // 1.出现在index位置上
   helper(list, set, subset, index + 1);
   // 2.不出现在index位置上
   subset += set.charAt(index);
   helper(list, set, subset, index + 1);
 
   return;
 }
 public List<String> subSets(String set) {
   // Write your solution here.
   // 每一个set里面的位置都包含两个可能：1.出现，2.不出现
   // Time: 2^n
   // Space: set长度
   if (set == null) {
     return new LinkedList<String>();
   }
   if (set == "") {
     List<String> list = new LinkedList<String>();
     list.add("");
     return list;
   }
  
   List<String> list = new LinkedList<String>();
   String subset = "";
   helper(list, set, subset, 0);
 
   return list;
 }
}
 
 
public class Solution {
 private void helper(StringBuilder str, char[] charArray, int index, List<String> list) {
   if (index == charArray.length) {
     list.add(str.toString());
     return;
   }
 
   helper(str, charArray, index + 1, list);
 
   str.append(charArray[index]);
   helper(str, charArray, index + 1, list);
   str.deleteCharAt(str.length() - 1);
 
   return;
 }
 public List<String> subSets(String set) {
   // Write your solution here.
   List<String> list = new ArrayList<>();
   if (set == null) {
     return list;
   }
 
   StringBuilder str = new StringBuilder();
   char[] charArray = set.toCharArray();
 
   helper(str, charArray, 0, list);
 
   return list;
 }
}
 
66. All Valid Permutations Of Parentheses I
public class Solution {
 private void helper(List<String> list, char[] array, int leftCount, int rightCount, int index) {
   // leftCount and rightCount indicate the
   if (leftCount == 0 && rightCount == 0) {
     // If no left and right parenthesis left, return
     list.add(new String(array));
   }
 
   if (leftCount > 0) {
     array[index] = '(';
     // 在backtracking的时候不用恢复array[index]，因为index上的元素会被覆盖
     // 只有array填满了才会add到list中
     helper(list, array, leftCount - 1, rightCount, index + 1);
   }
 
   // 剩下的leftCount比rightCount少，说明还能往里加')''
   if (leftCount < rightCount) {
     array[index] = ')';
     helper(list, array, leftCount, rightCount - 1, index + 1);
   }
 }
 public List<String> validParentheses(int n) {
   // Write your solution here
   // Assumption: n>0
   // Time: 2n层的二叉树，O(2^(2n))
   // Space: O(2n)
   List<String> list = new LinkedList<>();
   char[] array = new char[2 * n];
   helper(list, array, n, n, 0);
 
   return list;
 
 }
}
 
 
 
 
public class Solution {
 private void helper(List<String> list, char[] string, int index, int nLeft, int nRight, int n) {
   // string: the string of one permutation. Should be 2*n
   // index: the current position of the string
   // n: the length of the string or the length of the permutation
   // nLeft, nRight: the number ofr left/right parentheses that have been inserted in to string
   if (index == 2 * n) {
     String str = new String(string);
     list.add(str);
     return;
   }
 
   // Case 1: if right par is smaller than left per, we can add right par
   if (nRight < nLeft) {
     string[index] = ')';
     helper(list, string, index + 1, nLeft, nRight + 1, n);
   }
 
   // Case 2: if left par is smaller than n, we can still put more left par in
   if (nLeft < n) {
     string[index] = '(';
     helper(list, string, index + 1, nLeft + 1, nRight, n);
   }
 
   return;
 }
 public List<String> validParentheses(int n) {
   // Write your solution here
   // Use recursion to go through all the permutation
   // Use countLeft for counting the number of left par
   // Use countRight for counting the number of right par
  
   // Subproblem: the permutation of the parantheses that have not been put into string
   // Recursion rule: return the list
   // Basecase: the string length is 2*n
 
   // Time: permutation of 2*n-> (2n)!
   // Space: stack O(2n) + heap O(n) = O(n)
 
   List<String> list = new LinkedList<String>();
   char[] string = new char[2 * n];
   helper(list, string, 0, 0, 0, n);
 
   return list;
 }
}
 
 
 
73. Combinations Of Coins
public class Solution {
 private void helper(List<List<Integer>> result, int remain, int[] coins, List<Integer> list, int index) {
   // 1. index==coins.length-1代表最后一种coin
   if (index == coins.length - 1) {
     // 2. 判断剩下的钱能不能被最后一种coin整除
     if (remain % coins[index] == 0) {
       list.add(remain / coins[index]);
       result.add(new ArrayList<Integer>(list));
       list.remove(list.size() - 1);
     }
     return;
   }
 
   for (int i = 0; i <= remain / coins[index]; i++) {
     list.add(i);
     helper(result, remain - i * coins[index], coins, list, index + 1);
     list.remove(list.size() - 1);
   }
 
   return;
 }
 public List<List<Integer>> combinations(int target, int[] coins) {
   // Write your solution here
   List<List<Integer>> result = new ArrayList<List<Integer>>();
   List<Integer> list = new ArrayList<Integer>();
   int remain = target;
 
   helper(result, remain, coins, list, 0);
 
   return result;
 }
}
 

64. All Permutations I
/*
DFS: every layer is a position
At each layer, we can use any numbers that have not been used
 
index:  0 1 2 3 4 5 6 7 8  9
array:  [1 2 3 4 5 6 7 8 9 10]
        _______.............
           Used    Unused
 
swap the later number to current position
 
 
Bolded are used in earlier layers. So we can use any number after index 4
Therefore we can swap from index 5 to index 9 in this layer so that we can use all of them in this position
 
pos 0   1              2              3              4              5  ……
pos 1              2   3  4…        1 3 4 5
.
.
.
 
Time: O(n!)
Space: O(n)
*/
public class Solution {
 private void swap(char[] array, int i, int j) {
   char temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 
 private void helper(char[] array, int index, List<String> list) {
   if (index == array.length) {
     list.add(new String(array));
   }
 
   for (int i = index; i < array.length; i++) {
     swap(array, index, i);
     helper(array, index + 1, list);
     swap(array, index, i);
   }
 
   return;
 }
 
 public List<String> permutations(String input) {
   // Write your solution here
   // Time: O(n!)
   // Space: O(n)
   List<String> list = new ArrayList<>();
   if (input == null) {
     return list;
   }
 
   char[] array = input.toCharArray();
   helper(array, 0, list);
 
   return list;
 }
}
 
 
 
 
public class Solution {
 private void helper(List<String> list, String input, String substring, boolean[] marker, int depth) {
   if (depth == input.length()) {
     list.add(substring);
     return;
   }
   for (int i = 0; i < input.length(); i++) {
     if (!marker[i]) {
       marker[i] = true;
       helper(list, input, substring + input.charAt(i), marker, depth + 1);
       marker[i] = false;
     }
   }
   return;
 }
 public List<String> permutations(String input) {
   // Write your solution here
   // 没有duplicate，所以DSF每一层都要对input中的所有char扫描一遍
   // 但是要mark previous lavels已经经过的char，防止重复扫描
   // Tims: O(n!)
   // Space: O(n), height of the recursion tree
 
   List<String> list = new LinkedList<String>();
   if (input == "") {
     list.add("");
     return list;
   }
 
   boolean[] marker = new boolean[input.length()];
   String substring = "";
   helper(list, input, substring, marker, 0);
 
   return list;
 }
}
 
525. Number of Islands
public class Solution {
 private void helper(boolean[][] visited, char[][] grid, int row, int col, int[][] direction) {
   if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] == '0') {
     return;
   }
 
   visited[row][col] = true;
   for (int[] dir : direction) {
     helper(visited, grid, row + dir[0], col + dir[1], direction);
   }
 }
 
 public int numIslands(char[][] grid) {
   // Loop over the grid, every element is a start
   // Only start from an element that has not been visited
   // We have four directions to explore: [-1, 0], [1, 0], [0, -1], [0, 1]
 
   // At each level we try 4 direction, if out of bound or visited, then skip
   // Otherwise, we explore this direction and mark visited
 
   // return when all directions have been visited or they are zeroa
 
   // Time: O(n)
   // Space: O(n)
 
   if (grid == null || grid.length == 0) {
     return 0;
   }
 
   boolean[][] visited = new boolean[grid.length][grid[0].length];
   int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
 
   int numOfIsland = 0;
   for (int i = 0; i < grid.length; i++) {
     for (int j = 0; j < grid[0].length; j++) {
       if (grid[i][j] != '0' && !visited[i][j]) {
         numOfIsland++;
         helper(visited, grid, i, j, direction);
       }
     }
   }
   return numOfIsland;
 }
}
683. Word Break III
public class Solution {
   /*
    * @param : A string
    * @param : A set of word
    * @return: the number of possible sentences.
    */
   private void helper(String s, Set<String> dict, int[] count, int index) {
       // index: starting index
       // Base case
       if (index == s.length()) {
           count[0]++;
           return;
       }
      
       for (int i = index + 1; i <= s.length(); i++) {
           if (!dict.contains(s.substring(index, i).toLowerCase())) {
               continue;
           }
           helper(s, dict, count, i);
       }
   }
   
   
   public int wordBreak3(String s, Set<String> dict) {
       // Write your code here
       // DSF
       // Each level is one white space
       // Time: O(n)
       // Space: O(n)
       if (s == null || s.length() == 0) {
           return 0;
       }
      
       Set<String> newDict = new HashSet<>();
       for (String t : dict) {
           String t0 = t.toLowerCase();
           newDict.add(t0);
       }
      
       int[] count = new int[] {0};
       helper(s, newDict, count, 0);
       return count[0];
   }
}
 
Print all if {} block
	// C: what will happen if pair number n <= 0
	// A: print nothing
	// R: Use DSF to get all possible combinations. Each level represents a position.
	//    Each node has 2 branches: left/right bracket.
	// Height: 2*n. So the node number is 2^(2n). Note that in the end, we need to print, which takes n^2
	// So the time compexity is O(2^(2n)*n^2)
	// Space: O(2n)
	
	public void printIfBlocks(int n) {
		if (n <= 0) {
			return;
		}
		
		char[] array = new char[n * 2];
		helper(n, array, 0, 0, 0);
	}
	
	private void helper(int n, char[] array, int left, int right, int index) {
		// left/right: left/right bracket numbers that have been put into array
		// index: current position index
		if (index == 2 * n) {
			printBlock(array);
		}
		
		if (left < n) {
			array[index] = '{';
			helper(n, array, left + 1, right, index + 1);
		}
		if (right < left) {
			array[index] = '}';
			helper(n, array, left, right + 1, index + 1);
		}
	}
	
	private void printBlock(char[] array) {
		int space = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '{') {
				for (int j = 0; j < space; j++) {
					System.out.print("  ");
				}
				System.out.println("if {");
				space++;
			} else {
				for (int j = 0; j < space; j++) {
					System.out.print("  ");
				}
				System.out.println("}");
				space--;
			}
		}
	}
