624. Fibonacci Number Lite
 
public class Solution {
 public int fibonacci(int K) {
   // Write your solution here
   if(K<=0)
     return 0;
   else if(K==1)
     return 1;
 
   int num1 = 0;
   int num2 = 1;
   int sum = 0;
   for(int i=1;i<K;i++){
     sum = num1 + num2;
     num1 = num2;
     num2 = sum;
   }
   return sum;
 }
}
 
13. a to the power of b
public class Solution {
 public long power(int a, int b) {
   // Use recursion, considering odd and even
   // b/2, (b/2)/2, ((b/2)/2)/2 ...
   // b%2 == 0 or b%2== 1
   // Time: O(logb), space: O(logb)
  
   // Base case
   if (b == 0) {
     return 1;
   } else if (b == 1) {
     return a;
   }
 
   // Subproblem and recursion rule
   long temp = power(a, b / 2);
   if (b % 2 == 0) {
     return temp * temp;
   } else {
     return temp * temp * a;
   }
 }
}
 
4. Selection Sort
public class Solution {
 public int[] solve(int[] array) {
   // Write your solution here
   if (array.length == 0) {
     return array;
   }
 
   for (int i = 0; i < array.length; i++) {
     for (int j = i + 1; j < array.length; j++) {
       if (array[j] < array[i]) {
         int temp = array[i];
         array[i] = array[j];
         array[j] = temp;
       }
     }
   }
   return array;
 }
}
 
 
9. Merge Sort
public class Solution {
 private int[] merge(int[] array1, int[] array2) {
   int i1 = 0;
   int i2 = 0;
   int count = 0;
   int[] mergedArray = new int[array1.length + array2.length];
 
   while (i1 < array1.length && i2 < array2.length) {
     if (array1[i1] < array2[i2]) {
       mergedArray[count] = array1[i1];
       i1++;
     } else {
       mergedArray[count] = array2[i2];
       i2++;
     }
     count++;
   }
 
   while (i1 < array1.length) {
     mergedArray[count] = array1[i1];
     i1++;
     count++;
   }
 
   while (i2 < array2.length) {
     mergedArray[count] = array2[i2];
     i2++;
     count++;
   }
 
   return mergedArray;
 }
 public int[] divide(int[] array, int left, int right) {
   // base case:
   if (left == right) {
     return new int[] {array[left]};
   }
  
   // subproblem: sort array[left, mid] and array[mid + 1, right]
   int mid = left + (right - left) / 2;
   int[] leftArray = divide(array, left, mid);
   int[] rightArray = divide(array, mid + 1, right);
 
   // recursion rule: merge two sorted arrays
   int[] mergedArray = merge(leftArray, rightArray);
 
   return mergedArray;
 }
 public int[] mergeSort(int[] array) {
   // Write your solution here
   if (array == null || array.length == 0) {
     return array;
   }
 
   return divide(array, 0, array.length - 1);
 }
}
 

10. Quick Sort
public class Solution {
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 private void quickSort(int[] array, int left, int right) {
   // Base case:
   if (left >= right) {
     return;
   }
 
   // subproblem:
   // Get random index and select the pivot
   Random rand = new Random();
   int pivotIndex = left + rand.nextInt(right - left + 1);
   swap(array, pivotIndex, right);
 
   int i = left, j = right - 1;  // array[right] is the pivot
   // 1. anything between [i, j] are unsorted
   // 2. shrink [i, j] until i > j
   while (i <= j) {
     if (array[i] < array[right]) {
       i++;
     } else {
       swap(array, i, j);
       j--;
     }
   }
 
   // Put the pivot to the right position
   swap(array, i, right);
 
   quickSort(array, left, i - 1);
   quickSort(array, i + 1, right);
 }
 public int[] quickSort(int[] array) {
   // Write your solution here
   if (array == null || array.length <= 1) {
     return array;
   }
 
   quickSort(array, 0, array.length - 1);
   return array;
 }
}
258. Move 0s To The End I
public class Solution {
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 public int[] moveZero(int[] array) {
   if (array.length == 0) {
     return array;
   }
 
   int left = 0;
   int right = array.length - 1;
   while (left <= right) {
     if (array[left] == 0) {
       swap(array, left, right);
       right--;
     } else {
       left++;
     }
   }
 
   return array;
 }
}

11. Rainbow Sort
public class Solution {
 private void swap(int[] array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
 public int[] rainbowSort(int[] array) {
   // Write your solution here
   if (array == null || array.length <= 1) {
     return array;
   }
 
   int i = 0, j = 0, k = array.length - 1;
   // 1st type (-1): [0, i)
   // 2nd type (0) : [i, j]
   // unsorted     : [j, k], shrink this range until j > k
   // 3rd type (1) : (k, n - 1]
 
   // Move j
   while (j <= k) {
     if (array[j] == -1) {
       swap(array, i, j);
       i++;
       j++;
     } else if (array[j] == 0) {
       j++;
     } else {
       swap(array, j, k);
       k--;
     }
   }
 
   return array;
 }
}
 
C++:
class Solution {
private:
 void swap(vector<int>& array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
public:
 vector<int> rainbowSort(vector<int> array) {
   // write your solution here
   int i = 0;
   int j = 0;
   int k = array.size() - 1;
  
   while (j <= k) {
     if (array[j] == -1) {
       swap(array, i, j);
       i++;
       j++;
     } else if (array[j] == 0) {
       j++;
     } else if (array[j] == 1) {
       swap(array, j, k);
       k--;
     }
   }
 
   return array;
 }
};
 
 
258. Move 0s To The End I

public class Solution {
 public int[] moveZero(int[] array) {
   // Write your solution here
 
   // Create a new array and move all non-zero elements from the input array to the new array
   int[] newArray = new int[array.length];
   int count = 0;
   for (int i = 0; i < array.length; i++) {
     if (array[i] != 0) {
       newArray[count] = array[i];
       count++;
     }
   }
 
   return newArray;
 }
}

C++:
class Solution {
private:
 void swap(vector<int>& array, int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
 }
public:
 vector<int> moveZero(vector<int> array) {
   if (array.size() <= 1) {
     return array;
   }
 
   // write your solution here
   int i = 0;
   int j = array.size() - 1;
   while (i <= j) {
     if (array[i] != 0) {
       i++;
     } else {
       swap(array, i, j);
       j--;
     }
   }
   return array;
 }
};
