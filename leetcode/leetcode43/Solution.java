package leetcode43;

public class Solution {
	public String multiply(String num1,String num2){
		
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
  
        s1.append(num1);
        num1 = s1.reverse().toString();
        s2.append(num2);
        num2 = s2.reverse().toString();
        
        int n1 = num1.length();
        int n2 = num2.length();
        
        int[] digit = new int[n1+n2+1];
        for(int i=0;i<n1;i++){
        	int t1 = (int) (num1.charAt(i)-'0');
        	for(int j=0;j<n2;j++){
        		int t2 = (int) (num2.charAt(j)-'0');
        		digit[i+j] = digit[i+j]+t1*t2;
        	}
        }
        
        int carry = digit[0]/10;
        int d;
        int[] number = new int[n1+n2+1];
        for(int i=0;i<n1+n2;i++){
        	digit[i+1] = digit[i+1]+carry;
        	d = digit[i]%10;
        	number[i] = d;
        	
        	carry = digit[i+1]/10;
        }
        
        StringBuilder result = new StringBuilder();
        int start;
        for(start=number.length-1;start>=0;start--)
        	if(number[start]!=0)
        		break;
        
        if(start<0)
        	return "0";
        
        for(int i=start;i>=0;i--){
        	result.append((char) (number[i]+'0'));
        }
        
        return result.toString();
    }
}
