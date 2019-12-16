package leetcode58;

public class Solution {
	public int lengthOfLastWord(String s) {
		if(s.length()==0)
			return 0;
		
		int i=s.length()-1;
		while(s.charAt(i)==' '){
			s = s.substring(0,i);
			i=s.length()-1;
			
			if(i==-1)
				return 0;
		}
		
        int p1 = 0;
        int p2 = 0;
//        int maxlength = -1;
        while(p2<s.length()){
        	while(p2<s.length() && s.charAt(p2)!=' '){
            	p2++;
        	}
        	if(p2==s.length())
        		break;
//        	maxlength = (p2-p1)>maxlength ? p2-p1 : maxlength;
        	p2++;
        	p1 = p2;
        }
        
        return p2-p1;
    }
}
