package leetcode3;

import java.util.*;

public class Solution {
 public int lastOccur(int[] array, int target) {
   // Write your solution here
   if(array.length==0)
     return -1;
 
   int left = 0;
   int right = array.length-1;
 
   while(left<right-1){  // 注意退出的condition
     int mid = left+(right-left)/2;
 
     if(array[mid]==target){
       left = mid;
     }
 
     if(array[mid]>target){
       right = mid-1;
     }else if(array[mid]<target){
       left = mid+1;
     }
   }
 
   if(array[right]==target)
     return right;
   else if(array[left]==target)
     return left;
 
   return -1;
 }
}
