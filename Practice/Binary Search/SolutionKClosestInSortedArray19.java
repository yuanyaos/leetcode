package leetcode3;

import java.util.*;

public class Solution {
 public int[] kClosest(int[] array, int target, int k) {
      // Write your solution here
      int left = 0;
      int right = array.length-1;
 
      while(left<right-1){
        int mid = left+(right-left)/2;
 
        if(array[mid]>=target){
          right = mid;
        }else if(array[mid]<target){
          left = mid;
        }
      }
     
      int[] result = new int[k];
      int i;
      for(i=0;i<k;i++){
        if(left<0 || right>=array.length)
          break;
        if((target-array[left])>(array[right]-target)){
          result[i] = array[right];
          right++;
        }else{
          result[i] = array[left];
          left--;
        }
      }
     
      if(i==k){
        return result;
      }else if(left<0){
        while(i<k){
          result[i] = array[right];
          right++;
          i++;
        }
      }else if(right>=array.length){
        while(i<k){
          result[i] = array[left];
          left--;
          i++;
        }
      }
      return result;
    }
}
