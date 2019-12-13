package leetcode49;

import java.util.*;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strs[] = {"eat", "tea", "tan", "ate", "nat", "bat"};
		
		Solution test = new Solution();
		List<List<String>> result = test.groupAnagrams(strs);
		
		for(List<String> t:result){
			for(int i=0;i<t.size();i++){
				System.out.println(t.get(i));
			}
			System.out.println();
		}
	}

}
