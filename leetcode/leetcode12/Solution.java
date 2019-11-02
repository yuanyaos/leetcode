package leetcode12;

public class Solution {
	public String intToRoman(int num) {
        int[] romannum = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1}; 
		String[] romanstr = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		StringBuilder romanS=new StringBuilder();
		
		for(int i=0;i<romannum.length;i++) {
			while(num>=romannum[i]) {
				num -= romannum[i];
				romanS.append(romanstr[i]);
			}
		}
		return romanS.toString();
    }
}
