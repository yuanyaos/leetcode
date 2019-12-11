package leetcode40;

import java.util.List;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		10,1,2,7,6,1,5--8
//		2,5,2,1,2--5
		int[] candidates = new int[]{10,1,2,7,6,1,5};
		int target = 8;
		
		Solution test = new Solution();
		List<List<Integer>> result = test.combinationSum2(candidates, target);
		
		for(List<Integer> t:result){
			for(int i=0;i<t.size();i++){
				System.out.println(t.get(i));
			}
			System.out.println();
		}
	}

}
