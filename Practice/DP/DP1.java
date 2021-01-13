12. Fibonacci Number
public class Solution {
 public long fibonacci(int K) {
   // Write your solution here
   if (K == 0) {
     return 0;
   } else if (K == 1) {
     return 1;
   }
   long a = 0;
   long b = 1;
   long sum = 0;
   for (int i = 2; i <= K; i++) {
     sum = a + b;
     a = b;
     b = sum;
   }
   return sum;
 }
}
86. Longest Ascending SubArray
public class Solution {
 public int longest(int[] array) {
   // Need two pointer to record the ascending subarray
   // Also need a maxLength to store the max subarray length
   // Time: O(n), space: O(1)
   if (array.length <= 1) {
     return array.length;
   }
 
   int maxLength = 0;
   int slow = 0;
   int fast = 1;
   // array.length is at least 2
   while (fast < array.length) {
     if (array[fast] > array[fast - 1]) {
       // maxLength = Math.max(maxLength, fast - slow + 1);
       fast++;
     } else {
       maxLength = Math.max(maxLength, fast - slow);
       slow = fast;
       fast++;
     }
   }
   maxLength = Math.max(maxLength, fast - slow);
   return maxLength;
 
   // Test
   /*
   1, 2
  
   2, 1
 
   2, 1, 3
   */
 }
}

public class Solution {
 public int longest(int[] array) {
   // Write your solution here
   // Time: O(n)
   // Space: O(1)
  
   if (array.length <= 1) {
     return array.length;
   }
 
   int[] buffer = new int[array.length];
   int count = 1;
   int max = 1;
   for (int i = 1; i < array.length; i++) {
     if (array[i] > array[i - 1]) {
       count++;
       max = count > max ? count : max;
     } else {
       count = 1;
     }
   }
   return max;
 }
}
87. Max Product Of Cutting Rope
public class Solution {
 public int maxProduct(int length) {
   // 至少需要cut一次
 
   // length = 1                          d[i]
   //                                      =>1
 
   // length = 2
   // -||-                                 =>2
 
   // length = 3
   // -||--    max(d[1],1)*(2)=2
   // --||-    max(d[2],2)*(1)=2
 
   // length = 4
   // -||---   max(d[1],1)*(3)=3
   // --||--   在||所处位置一定要切一刀的情况下（右侧固定了），想办法如何利用之前subproblem的结果来找||左边的product最大值
  
   // Induction: 对于length=5
   // 固定右小段，右边有4个cases: 4, 3, 2, 1
   // case 1: max(M[1],1) * 4
   // case 2: max(M[2],2) * 3
   // case 3: max(M[3],3) * 2
   // case 4: max(M[4],4) * 1
 
   int[] dp = new int[length + 1];
   dp[1] = 1;
 
   for (int i = 1; i <= length; i++) {
     for (int j = 1; j < i; j++) {
       int temp = Math.max(dp[j], j) * (i - j);  // j (左边不切) 是可以大于dp[j]的; (i - j) 是左边大段j右边的长度
       dp[i] = Math.max(dp[i], temp);  // 更新dp[i]
     }
   }
   return dp[length];
 }
}


public class Solution {
 public int maxProduct(int length) {
   // Write your solution here
   // Time: O(n^2)
   // Space: O(n)
   int[] dp = new int[length + 1];
   dp[1] = 0;
   for (int i = 2; i <= length; i++) {
     for (int j = 1; j < i; j++) {
       // d[i]在inner loop里不断更新，所以要取max
       // i - j是右大段，会cover每一种切法，所以不用max(dp[i - j], i - j)
       dp[i] = Math.max(dp[i], Math.max(dp[j], j) * (i - j));
     }
   }
   return dp[length];
 }
}
88. Array Hopper I
public class Solution {
 public boolean canJump(int[] array) {   
// Clarification: array length >= 1
// Linear looking back的方法
   // 从左往右，先解决最基本的问题，然后存好基本问题的解，以给之后更大的问题提供解
   // 1 3 2 0 3
   // 先解决base case: 1 -> dp[0] = true;
   // 再解决 1 3，看能不能从index 0到达最后 -> 可以，dp[1] = true;
   // 再解决 1 3 2，看能不能从index 0到达最后
   // 以此类推
 
   // M[0] = 0;
   // M[i]代表从index 0能不能到达index i
   // dp[i]代表从index 0可以到达index i
   // 对于所有 j < i，dp[j] == true && j + M[j] >= i, then dp[i] = true
 
   boolean[] dp = new boolean[array.length];
   dp[0] = true;
   for (int i = 1; i < array.length; i++) {
     for (int j = 0; j < i; j++) {
       if (dp[j] == true && j + array[j] >= i) {
         dp[i] = true;
         break;
       }
     }
   }
   return dp[array.length - 1];
 }
}
   // Test 1
   // Test 1 3
   // Test 1 3 2
 

public class Solution {
 public boolean canJump(int[] array) {
   // Write your solution here
   // Time: O(n^2)
   // Space: O(n)
   // 从后往前看
   boolean[] dp = new boolean[array.length];  // All false in the beginning
   dp[array.length - 1] = true;
   for (int i = array.length - 2; i >= 0; i--) {
     for (int j = 1; j <= array[i]; j++) {
       if (dp[i + j] == true) {
         dp[i] = true;
         break;
       }
     }
   }
   return dp[0];
 }
}
Minimum cut to get all palindrome
	// CA: the input is a string. The output is an integer representing the minimum cut
	// Corner case: input string has 0 length or null
	// R: 1. Do linear scan from start to end. We need a buffer in which buffer[i] stores the smallest cut number
	//       for substring(0, i)
	//    2. When scanning, need to look back for j from 1 to i:
	//       (a) Between 0 to j, we read the minimum cut for substring(0, j) from buffer[j]
	//       (b) Between j + 1 to i, we need to check if substring(j + 1, i) is palindrome
	//       (c) Update buffer[i]: buffer[i] = Math.min(buffer[i], buffer[j] + 1)
	
	// Time: O(n^2 * n), we need extra n to determine palindrome
	// Space: O(n)
	
	public int minCut(String input) {
		if (input == null || input.length() == 0) {
			return Integer.MIN_VALUE;
		}
		
		// Record minimum cut
		int[] buffer = new int[input.length()];
		char[] array = input.toCharArray();
		buffer[0] = 0;
		
		for (int i = 1; i < input.length(); i++) {
			buffer[i] = Integer.MAX_VALUE;
			// Substring from 0 to i itself may also be a palindrome
			// Need to check this first
			if (isPalindrome(array, 0, i)) {
				buffer[i] = 0;
				continue;
			}
			
			for (int j = 0; j < i; j++) {
				if (isPalindrome(array, j + 1, i)) {
					buffer[i] = Math.min(buffer[i], buffer[j] + 1);
				}
			}
		}
		return buffer[buffer.length - 1];
	}
	
	private boolean isPalindrome(char[] array, int start, int end) {
		while (start <= end) {
			if (array[start] != array[end]) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
