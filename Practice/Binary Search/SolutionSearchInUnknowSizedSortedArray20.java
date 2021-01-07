package leetcode3;

import java.util.*;

/*
*  interface Dictionary {
*    public Integer get(int index);
*  }
*/
 
// You do not need to implement the Dictionary interface.
// You can use it directly, the implementation is provided when testing your solution.
public class Solution {
 public int search(Dictionary dict, int target) {
   // Write your solution here
 
   // 1. Find the size of the dict by jumping by *2
   int left = 0;
   int right = 1;  // 特别注意必须是1
   while(dict.get(right)!=null && dict.get(right)<target){
     left = right;
     right *= 2;
   }
 
   // 2. Start binary search for numbers between left and right
   while(left<=right){
     int mid = left+(right-left)/2;
     if(dict.get(mid)==null){
       right = mid-1;
       continue;
     }
 
     if(dict.get(mid)==target){
       return mid;
     }
 
     if(dict.get(mid)<target){
       left = mid+1;
     }else if(dict.get(mid)>target){
       right = mid-1;
     }
   }
 
   return -1;
 }
}
