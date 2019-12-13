package leetcode46;

import java.util.List;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{0,1};
		
		Solution test = new Solution();
		List<List<Integer>> result = test.permute(nums);
		
		for(List<Integer> t:result){
			for(int i=0;i<t.size();i++){
				System.out.println(t.get(i));
			}
			System.out.println();
		}
	}

}
