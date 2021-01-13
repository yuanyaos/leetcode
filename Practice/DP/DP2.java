89. Array Hopper II
public class Solution {
 public int minJump(int[] array) {
   // Write your solution here
   // 一维DP，用linear look back的方法去做
   // Time: O(n^2)
   // Space: O(n)
 
   int[] dp = new int[array.length];
   // Base case: 0到0不需要任何步数
   dp[0] = 0;
   for (int i = 1; i < array.length; i++) {
     // 初始化dp[i]，假设无法到达
     dp[i] = -1;
     // 线性回溯，一步步往回看，看看之前的格子能不能到达当前的位置
     // 利用之前的信息
     for (int j = i - 1; j >=0; j--) {
       // 如果j处不是无法到达，并且从j开始能够通过array[j]里存的步数到达i的话
       if (dp[j] != -1 && j + array[j] >= i) {
         // 在已经能从j到达i的情况下！
         // 如果dp[j]里存的从0到j最小步数加上1(还需要一步才能从j到达i)，比当前dp[i]中存的从0到i的最小步数还要小的话，则更新dp[i]
         if (dp[i] == -1 || dp[j] + 1 < dp[i]) {
           dp[i] = dp[j] + 1;
         }
       }
     }
   }
   return dp[array.length - 1];
 }
}
 
97. Largest SubArray Sum
public class Solution {
 public int largestSum(int[] array) {
   // Write your solution here
   // 从左往右一直加，只要和不是负数就还对右边剩下的有贡献
   // 一旦和为负数，则对右边的没有贡献，就从下一个重新开始计算和
   // Time: O(n)
   // Space: O(1)
 
   // int left = 0, right = 0;  // if need to tell the location of the subarray, we need to record the index
   int sum = 0;
   int max = Integer.MIN_VALUE;
   for (int i = 0; i < array.length; i++) {
     sum += array[i];
     if (sum > max) {
       max = sum;
     }
     if (sum < 0) {
       sum = 0;
     }
   }
   return max;
 }
}
 
99. Dictionary Word I
public class Solution {
 private Set<String> getSet(String[] dict) {
   Set<String> set = new HashSet<>();
   for (String s : dict) {
     set.add(s);
   }
   return set;
 }
 public boolean canBreak(String input, String[] dict) {
   /*
    1  2  3  4  5  6  7  8  9         cutting position
   0  1  2  3  4  5  6  7  8          array index
   r  o  b  c  a  t  b  o  b          array
 
   i: boundary of the smaller-size problem
   j: history[j] if [0 j] can be composed from dictionary
   So j = 0 : i - 1
   if history[j] is true, then determine (j, i) is in the dictionary?
     yes: history[i] = true;
 
   Two loops, time: O(n^2)
   Space: O(n)
   */
 
   // Assume input.length() >= 1
   // dict is not empty
   Set<String> set = getSet(dict);
   boolean[] solution = new boolean[input.length() + 1];
   solution[0] = true;
   for (int i = 1; i <= input.length(); i++) {
     for (int j = 0; j < i; j++) {
       // solution[j]: [0, j) can be composed from dict
       if (solution[j] && set.contains(input.substring(j, i))) {
         solution[i] = true;
         break;
       }
     }
   }
   return solution[solution.length - 1];
 }
}
100. Edit Distance
public class Solution {
 public int editDistance(String one, String two) {
   // Write your solution here
   // abc -> hijk
   // a   b   c
   // |   |   |   |
   // h   i   c   k
 
 
   //      a   b   c
   //   0  1   2   3
   // h 1  1   2   3
   // i 2  2   2   3
   // c 3  3   3  '2' (Note here)
   // k 4  4   3   3
 
   // replace | delete
   // --------|---------
   // insert  | (current)
 
   int[][] dp = new int[one.length() + 1][two.length() + 1];
   for (int i = 0; i < dp.length; i++) {
     dp[i][0] = i;
   }
   for (int i = 0; i < dp[0].length; i++) {
     dp[0][i] = i;
   }
 
   for (int i = 1; i < dp.length; i++) {
     for (int j = 1; j < dp[0].length; j++) {
       // 如果当前character相等的话，什么操作都不用，直接继承左上角，相当于排除掉当前的character
       if (one.charAt(i - 1) == two.charAt(j - 1)) {
         dp[i][j] = dp[i - 1][j - 1];
       } else {
         int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
         min = Math.min(min, dp[i - 1][j - 1]);
         dp[i][j] = min + 1;
       }
     }
   }
   return dp[dp.length - 1][dp[0].length - 1];
 }
}
101. Largest Square Of 1s
public class Solution {
 public int largest(int[][] matrix) {
   // Time: O(n*m)
   // Space: O(n*m)
   // Use dynammic programming
   // 0 0 0 0
   // 1 1 1 1
   // 0 1 1 1
   // 1 0 1 1
 
   // 边长被最小的那条边限制，所以dp[i][j] = min(dp[i-1][j-1], dp[i][j-1],dp[i-1][j])
   // 0 0 0 0
   // 1 1 1 1
   // 0 1 2 2
   // 1 0 1 2
   int m = matrix.length;
   int n = matrix[0].length;
   int[][] dp = new int[m][n];
 
   int result = 0;
   for (int i = 0; i < m; i++) {
     for (int j = 0; j < n; j++) {
       if (i == 0 || j == 0) {  // 如果在matrix的边界
         dp[i][j] = matrix[i][j];
       } else if (matrix[i][j] == 0) {  // 如果matrix[i][j] == 0，那就从0开始，之前的memo用不着了
         dp[i][j] = 0;
       } else {  // 如果matrix[i][j] == 1，则需要加1
         dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
       }
       result = Math.max(result, dp[i][j]);
     }
   }
 
   return result;
 }
}


103. Longest Consecutive 1s
public class Solution {
 public int longest(int[] array) {
   // Time: O(n)
   // Space: O(1)
   int result = 0;
   int curLength = 0;
   for (int i = 0; i < array.length; i++) {
     if (array[i] == 1) {
       curLength++;
     } else {
       curLength = 0;
     }
     if (curLength > result) {
       result = curLength;
     }
   }
   return result;
 }
}
 
104. Longest Cross Of 1s
public class Solution {
 private int merge(int[][] matrix1, int[][] matrix2, int m, int n) {
   int result = 0;
   for (int i = 0; i < m; i++) {
     for (int j = 0; j < n; j++) {
       matrix1[i][j] = Math.min(matrix1[i][j], matrix2[i][j]);
       result = Math.max(result, matrix1[i][j]);
     }
   }
   return result;
 }
 
 private int[][] getUpLeftMemo(int[][] matrix, int m, int n) {
   int[][] left = new int[m][n];
   int[][] up = new int[m][n];
   for (int i = 0; i < m; i++) {
     for (int j = 0; j < n; j++) {
       if (matrix[i][j] == 1) {
         if (i == 0 && j == 0) {  // 边界问题 1
           left[i][j] = 1;
           up[i][j] = 1;
         } else if (i == 0) {  // 边界问题 2
           left[i][j] = left[i][j - 1] + 1;
           up[i][j] = 1;
         } else if (j == 0) {  // 边界问题 3
           left[i][j] = 1;
           up[i][j] = up[i - 1][j] + 1;
         } else {
           left[i][j] = left[i][j - 1] + 1;
           up[i][j] = up[i - 1][j] + 1;
         }
       }
     }
   }
   merge(left, up, m, n);
   return left;
 }
 
 private int[][] getDownRightMemo(int[][] matrix, int m, int n) {
   int[][] right = new int[m][n];
   int[][] down = new int[m][n];
   for (int i = m - 1; i >= 0; i--) {
     for (int j = n - 1; j >= 0; j--) {
       if (matrix[i][j] == 1) {
         if (i == m - 1 && j == n - 1) {  // 边界问题 1
           right[i][j] = 1;
           down[i][j] = 1;
         } else if (i == m - 1) {  // 边界问题 2
           right[i][j] = right[i][j + 1] + 1;
           down[i][j] = 1;
         } else if (j == n - 1) {  // 边界问题 3
           right[i][j] = 1;
           down[i][j] = down[i + 1][j] + 1;
         } else {
           right[i][j] = right[i][j + 1] + 1;
           down[i][j] = down[i + 1][j] + 1;
         }
       }
     }
   }
   merge(right, down, m, n);
   return right;
 }
 
 public int largest(int[][] matrix) {
   // 需要有4个矩阵记录left, right, up, down 4个arm
   // 然后对于每个位置取4个矩阵里面最小的那个数
   // 每个矩阵用最长连续1的方法记录arm的长度
   // Timw: O(n)
   // Space: O(n)
 
   int m = matrix.length;
   int n = matrix[0].length;
   int[][] upLeft = getUpLeftMemo(matrix, m, n);
   int[][] downRight = getDownRightMemo(matrix, m, n);
   return merge(upLeft, downRight, m, n);
 }
}

143. Minimum Cuts For Palindromes
public class Solution {
 public int minCuts(String input) {
     // dp[j] is the minimum cut for input[0:j]
      // Q: How to get the dp[i] from dp[j]??
      // A: if dp[j] is the minimum cuts for input[0:j] and [j + 1, i] is also a palindrome
      //      update dp[i] = min(dp[i], dp[j] + 1);
      // 左大段右小段, 左大段是[0, j], 右大段[j + 1, i]
     if (input.length() == 0) {
       return 0;
     }
 
      char[] array = input.toCharArray();
      int n = array.length;
      boolean[][] isPalindrome = new boolean[n][n];
      int[] dp = new int[n];
      for (int i = 0; i < array.length; i++) {
        dp[i] = i;
        for (int j = 0; j <= i; j++) {
          if (array[j] == array[i] && (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
            // input[0 : j] is palindrom
            isPalindrome[j][i] = true;
            // 如果j == 0则不用 +1，[0 : i]已经是palindrome
            dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j - 1] + 1);
          }
        }
      }
      return dp[dp.length - 1];
 }
}
1. Longest Ascending Subsequence
public class Solution {
 public int longest(int[] array) {
   /*    i
     j
   0 1 2 3 4 5 6
   5 2 6 3 4 7 5
  [1 1 2 2 3 4 4]
 
   subproblem: from 0 to i - 1 in the array
   solution[j] = length of longest ascending subsequence for array[0:j], 必须包括array[j]
        
   start from solution[j] compare solution[j] + 1 with array[j],
 
 
   Yuanqiao Wu's solution
   -3 1 -2 -1 2  0  l:3
   4  1 2 5 3       l:4
   */
   if (array.length == 0) {
     return 0;
   }
   int[] solution = new int[array.length];
   solution[0] = 1;
   int result= 1;
   for (int i = 1; i < array.length; i++) {
     solution[i] = 1;
     for (int j = 0; j < i; j++) {
       if (array[j] < array[i]) {
         solution[i] = Math.max(solution[i], solution[j] + 1);
       }
     }
     result = Math.max(result, solution[i]);
   }
   return result;
 }
}
176. Longest Common Substring
public class Solution {
 public String longestCommon(String s, String t) {
   // source:  s t u d e n t
   // target:  s w e d e n
   /*
   i: s
   j: s w
   */
   char[] source = s.toCharArray();
   char[] target = t.toCharArray();
   int ns = source.length;
   int nt = target.length;
   // record the longest number for source[0 : i] and target[0 : j]
   int[][] solution = new int[ns][nt];
   // int longest;
   int start = 0;
   int longest = 0;
   for (int i = 0; i < ns; i++) {
     for (int j = 0; j < nt; j++) {
       if (source[i] == target[j]) {
         // boundary
         if (i == 0 || j == 0) {
           solution[i][j] = 1;
         } else {
           solution[i][j] = solution[i - 1][j - 1] + 1;
         }
         if (solution[i][j] > longest) {
           longest = solution[i][j];
           start = i - longest + 1;
         }
       }
     }
   }
   return s.substring(start, start + longest);
 }
}

