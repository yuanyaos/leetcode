public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        // (A * B) mod C = (A mod C * B mod C) mod C
        if(n==0){
            return 1 % b;
        }
        if(n==1){
            return a % b;
        }
        
        long ans = fastPower(a, b, n/2);
        ans = ans * ans % b;
        if(n % 2==1){ // if n is odd
            ans = ans * (a % b) % b; // (A * B) mod C = (A mod C * B mod C) mod C
        }
        
        return (int)ans;
    }
}