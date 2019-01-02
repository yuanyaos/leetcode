public class Solution {
    /*
     * @param source: A source string
     * @param target: A target string
     * @return: An integer as index
     */
    public int BASE = 1000000;
    public int strStr2(String source, String target) {
        // write your code here
        if(source==null || target==null) {
    		return -1;
		}
		
		int m = target.length();
		if(m==0){
		    return 0;
		}
		
		// hash code for target
		int targethash = 0;
		for(int i=0;i<m;i++){
		    targethash = (targethash*31+target.charAt(i))%BASE;
		}
		
		// compute power of m for sliding to next
		int front = 1;
		for(int i=0;i<m;i++){
		    front = (front*31)%BASE;
		}
		
		// start finding the position
		int sourcehash = 0;
		for(int i=0;i<source.length();i++){
		    // Adding new charater into hash code every loop
		    sourcehash = (sourcehash*31+source.charAt(i))%BASE;
		    if(i<m-1){
		        continue;
		    }
		    
		    // Substract the charater in the front of the sliding window
		    if(i>=m){
		        sourcehash = (sourcehash-front*source.charAt(i-m))%BASE;
		        if(sourcehash<0) {
		        	sourcehash += BASE;
		        }
		    }
		    
		    if(sourcehash==targethash){
		        return i-m+1;
		    }
		}
		
		return -1;
    }
}