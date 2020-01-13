public class Solution {
	public String reverseWords(String s) {
        s = s.trim();
        String[] split_str = s.split("\\s+");
        
        StringBuilder newstr = new StringBuilder();
        for(int i=split_str.length-1;i>=0;i--) {
        	newstr.append(split_str[i]);
        	newstr.append(" ");
        }
        return newstr.toString().trim();
    }
}
