package leetcode43;

import java.util.Arrays;

public class Solution {
	public String multiply(String num1, String num2) {
		int n1 = num1.length();
		int n2 = num2.length();
		
		int[] store_num1 = new int[n1+1];
		int[] store_num2 = new int[n2+1];
		for(int ii=0;ii<n1;ii++){
			store_num1[ii] = (int) num1.charAt(n1-ii-1)-'0';
		}
		for(int ii=0;ii<n2;ii++){
			store_num2[ii] = (int) num2.charAt(n1-ii-1)-'0';
		}
		
		int[] result = new int[n1+n2+1];
		int ca = 0;
		for(int j=0;j<n2;j++){
			int k = 0;
			while(result[k]!=0){
				k = k+1;
			}
			if(k==0)
				k = 1;
			result[k] = ca;
			
			int[] store_up = new int[k];
			for(int ii=0;ii<k;ii++)
				store_up[ii] = result[ii];
			
			int[] store_bo = new int[n1+1];
			
			int bo = store_num2[j];
			int carry = 0;			
			for(int i=0;i<n1;i++){
				int up = store_num1[i];
				int mul = bo*up+carry;
				carry = mul/10;
				store_bo[i] = mul%10;
			}
			store_bo[n1] = carry;
			
			Arrays.fill(result,0);
			result[0] = store_up[0];
			k=1;
			ca = 0;
			while(k<store_up.length && k-1<store_bo.length){
				int sum = store_up[k]+store_bo[k-1]+ca;
				ca = sum/10;
				result[k] = sum%10;
				k = k+1;
			}
			if(k==store_up.length){
				for(k=store_up.length-1;k<store_bo.length;k++){
					result[k+1] = (store_bo[k]+ca)%10;
					ca = (store_bo[k]+ca)/10;
				}
			}else{
				for(k=store_bo.length+1;k<store_up.length;k++){
					result[k] = (store_up[k]+ca)%10;
					ca = (store_up[k]+ca)/10;
				}
			}
		}
		
		int kk=0;
		while(result[kk]!=0){
			kk = kk+1;
		}
		result[kk] = ca;
		
		String fresult = "";
		for(int ii=0;ii<kk+1;ii++){
			fresult += (char) (result[ii])+'0';
		}
		
		return fresult;
    }
}
