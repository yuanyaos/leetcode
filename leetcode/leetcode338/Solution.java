package leetcode338;

public class Solution {
	public int[] countBits(int num) {
        int[] result = new int[num+1];
        
        for(int i=1;i<=num;i++){
        	int div = i/2;
        	int rem = i-2*(i/2);
        	result[i] = result[div]+rem;
        }
        
        return result;
    }
}
