package leetcode22;

import java.util.*; 

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution test=new Solution();
		List<String> result = new ArrayList<String>();
		int n=3;
		result = test.generateParenthesis(n);
		for(int i=0;i<result.size();i++){
			System.out.println(result.get(i));
			System.out.println();
		}
	}

}