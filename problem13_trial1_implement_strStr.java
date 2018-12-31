public class Solution {
    /**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // Write your code here
        if(source.isEmpty() && target.isEmpty()) {
    		return 0;
		}
		
		if(target.isEmpty()) {
			return 0;
		}
		
		if(source.length()<target.length()) {
			return -1;
		}
		
		if(source.length()==1) {
			return 0;
		}
		
		int len = target.length();
		for(int i=0; i<source.length()-len+1; i++) {
				String t=source.substring(i, i+len);
				if(t.equals(target)) {
					return i;
				}
		}
		return -1;
    }
}