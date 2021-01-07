package leetcode3;

import java.util.*;

public int firstOccur(int[] array, int target) {
   // Write your solution here
   if(array.length==0)
     return -1;
 
   // consider this as: aaaaaaaaaaaBbbbbbbbbb, then find B
   // define left and right
   int left = 0;
   int right = array.length-1;
 
   while(left<right-1){  // in the last two elements array[left]==array[right]==target, if using while(left<=right), then give wrong result
     int mid = left+(right-left)/2;
     if(array[mid]==target){
       right = mid;  // do not return, keep moving toward middle to find the leftmost target
       continue;     // NEED TO CONTINUE otherwise it will keep doing the rest in the loop
     }
 
     if(array[mid]<target)
       left = mid+1;  // did not skip target
     else
       right = mid-1;  //did not skip target
   }
 
   if(array[left]==target)
     return left;
   else if(array[right]==target)
     return right;
   else
     return -1;
 }
