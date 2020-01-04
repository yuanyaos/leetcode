package leetcode1_2nd;

import java.util.*;

public class Solution {
	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<nums.length;i++){
			map.put(nums[i],i);
		}
		for(int i=0;i<nums.length;i++){
			int temp = target-nums[i];
			if(map.containsKey(temp) && map.get(temp)!=i){
				result[0] = i;
				result[1] = map.get(temp);
			}
		}
		
		return result;
    }
}
