package leetcode48;

import java.util.Arrays;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=9;
		int[][] matrix = new int[n][n];
		
		int count = 1;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++){
				matrix[i][j] = count;
				count = count+1;
			}
		
		Solution test = new Solution();
		test.rotate(matrix);
		
		for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
	}

}
