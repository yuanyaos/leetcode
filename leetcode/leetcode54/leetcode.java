package leetcode54;

import java.util.*;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//		int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		
		Solution test = new Solution();
		
//		List<Integer> result =  new ArrayList<Integer>(test.spiralOrder(matrix));
		List<Integer> result = test.spiralOrder(matrix);
		
		System.out.println(result.size());
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i));
	}

}
