public class Solution {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    private int longestSize = 0;
    private String longestChar;
    public String longestPalindrome(String s) {
        // write your code here
        int L_even, L_odd;
        int left;
        int right;
        String st;
        
        int m = s.length();
        if(s==null || m==0 || m==1){
            return s;
        }
        
        for(int i=0;i<m;i++){
            // even
            L_even = 0;
            left = i;
            right = i+1;
            while(left>=0 && right<m && s.charAt(left)==s.charAt(right)){
                L_even += 1;
                left--;
                right++;
            }
            
            // System.out.println("Left:"+left);
            // System.out.println("Right:"+right);
            
            // odd
            L_odd = 0;
            left = i;
            right = i;
            while(left>=0 && right<m && s.charAt(left)==s.charAt(right)){
                L_odd += 1;
                left--;
                right++;
            }
            
            if(L_even>=L_odd){
                st = s.substring(i-L_even+1,i+L_even+1);
            }else{
                st = s.substring(i-L_odd+1,i+L_odd);
            }
            
            if(st.length()>this.longestSize){
                this.longestSize = st.length();
                this.longestChar = st;
            }
        }
        return this.longestChar;
    }
}