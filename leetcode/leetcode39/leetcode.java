package leetcode39;

import java.util.List;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<List<Integer>> combinationSum(int[] candidates, int target)
		
		int[] candidates = new int[]{2,3,6,7};
		int target = 7;
		
		Solution test = new Solution();
		List<List<Integer>> result = test.combinationSum(candidates, target);
		
		for(List<Integer> t:result){
			for(int i=0;i<t.size();i++){
				System.out.println(t.get(i));
			}
			System.out.println();
		}
	}

}