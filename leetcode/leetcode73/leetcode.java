package leetcode73;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
		
		Solution test = new Solution();
		test.setZeroes(matrix);
		
		for(int i=0;i<matrix.length;i++)
			for(int j=0;j<matrix[0].length;j++)
				System.out.println(matrix[i][j]);
	}

}
