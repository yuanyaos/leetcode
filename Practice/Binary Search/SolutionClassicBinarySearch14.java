package leetcode3;

import java.util.*;

public class Solution {
 public int binarySearch(int[] array, int target) {
   // Write your solution here
   int left = 0;
   int right = array.length-1;
 
   while(left<=right){  // use sigle element to test
     int mid = left+(right-left)/2;  // avoid overflow
     if(array[mid]==target){
       return mid;
     }
 
     if(array[mid]>target){
       right = mid-1;
     }else{
       left = mid+1;
     }
   }
 
   return -1;
 }
}
