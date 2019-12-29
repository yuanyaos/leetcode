package leetcode78;

import java.util.*;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{3,1,2};
		
		Solution test = new Solution();
		
		List<List<Integer>> result = test.subsets(nums);
		
		for(List<Integer> t:result){
			for(Integer n:t){
				System.out.print(n + " "); 
			}
			System.out.println();
		}
	}

}
