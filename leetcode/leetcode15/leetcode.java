package leetcode15;

import java.util.ArrayList;
import java.util.List;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		[-1,0,1,2,-1,-4]
		Solution test=new Solution();
		int[] nums={-1,0,1,2,-1,-4};
		
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		list = test.threeSum(nums);
		
		for (int ii = 0; ii < list.size(); ii++) {
            for (int jj = 0; jj < list.get(ii).size(); jj++) { 
                System.out.print(list.get(ii).get(jj) + " ");
            }
            System.out.println(); 
        }
	}

}