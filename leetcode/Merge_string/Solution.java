
public class Solution {
	public String mergeString(String s1, String s2) {
		int l1 = s1.length();
		int l2 = s2.length();
		StringBuilder s = new StringBuilder();
		int i=0;
		while(i<l1 && i<l2) {
			s.append(s1.charAt(i));
			s.append(s2.charAt(i));
			i++;
		}
		if(i==l1 && i<l2)
			s.append(s2.substring(i));
		else if(i<l1 && i==l2)
			s.append(s1.substring(i));
		
		return s.toString();
	}
}
