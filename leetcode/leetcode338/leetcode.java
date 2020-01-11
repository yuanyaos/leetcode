package leetcode338;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 8;
		
		Solution test = new Solution();
		
		int[] result = test.countBits(num);
		
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}

}
