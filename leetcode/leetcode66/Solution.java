package leetcode66;

public class Solution {
	public int[] plusOne(int[] digits) {
		int carry = 1;
		int sum = 0;
		int[] new_digits = new int[digits.length+1];
		for(int i=digits.length-1;i>=0;i--){
			sum = digits[i]+carry;
			carry = sum/10;
			new_digits[i+1] = sum%10;
			digits[i] = new_digits[i+1];
		}
		new_digits[0] = carry;
		
		if(new_digits[0]!=0)
			return new_digits;
		else
			return digits;
    }
}
