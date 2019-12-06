package leetcode28;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String haystack = "mississippi";
		String needle = "a";
		
		Solution test = new Solution();
		int index = test.strStr(haystack, needle);
		System.out.println(index);
	}

}