package leetcode3;

import java.util.*;

public class Solution {
 public int search(int[] array, int target) {
   // Write your solution here
     if(array.length==0)
        return -1;
 
      int left = 0;
      int right = array.length-1;
      // 分三种情况讨论, ==, >, < target
      while(left<=right){
        int mid = left+(right-left)/2;
        if(array[mid]==target){
          return mid;
        }
       
        if(array[mid]<array[right]) {  // 如果用 array[mid]>=array[left]会把[2,1]中target=1 rule out
          if(target>array[mid] && target<=array[right]) {  // 保证右边是ascending的
       	 left = mid+1;
          }else {
       	 right = mid-1;
          }
        }else {  // array[mid]>=array[right]
          if(target>=array[left] && target<array[mid]) {
       	 right = mid-1;
          }else {
       	 left = mid+1;
          }
        }
      }
      return -1;
 }
}
