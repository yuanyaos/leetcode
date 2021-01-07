package leetcode3;

import java.util.*;

public int closest(int[] array, int target) {
   // Write your solution here
 
   // Need to find the closest TWO elements using binary search and then determine which one is the closest
   if(array.length==0)
     return -1;
 
   int left = 0;
   int right = array.length-1;  // remember -1
 
   while(left<right-1){  // because this ensure two elements after the loop
     int mid = left+(right-left)/2;
     if(array[mid]==target)
       return mid;
 
     // 不能使用left=mid+1和right=mid-1! [...3,4,...],当target=3.5的时候mid=left+1就会跳过target
     if(array[mid]<target){
       left = mid;
     }else if(array[mid]>target){
       right = mid;
     }
   }
 
   // judge which one is the closest between the two elements
   if(target-array[left]>array[right]-target)
     return right;
   else
     return left;
 }