package leetcode18;

import java.util.ArrayList;
import java.util.List;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution test=new Solution();
		int[] nums={1, 0, -1, 0, -2, 2};
		
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		list = test.fourSum(nums,0);
		
		for (int ii = 0; ii < list.size(); ii++) {
            for (int jj = 0; jj < list.get(ii).size(); jj++) { 
                System.out.print(list.get(ii).get(jj) + " ");
            }
            System.out.println(); 
        }
	}

}
