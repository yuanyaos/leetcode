package leetcode79;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		String word = "ABCB";
		
		Solution test = new Solution();
		boolean result = test.exist(board, word);
		
		System.out.println(result);
	}

}
