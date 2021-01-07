package leetcode3;

import java.util.*;

public class Solution {
 public int[] search(int[][] matrix, int target) {
   // Write your solution here
   int row = matrix.length;
   int col = matrix[0].length;
   int[] result = new int[2];
 
   int left = 0;
   int right = row*col-1;  // be careful about the index
 
   while(left<=right){
     int mid = left+(right-left)/2;
     int x = mid/col;
     int y = mid%col;
 
     if(matrix[x][y]==target){
       result[0] = x;
       result[1] = y;
       return result;
     }
 
     if(matrix[x][y]>target){
       right = mid-1;
     }else{
       left = mid+1;
     }
   }
   result[0] = -1;
   result[1] = -1;
   return result;
 }
}
