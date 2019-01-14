/**
 * public class GitRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use GitRepo.isBadVersion(k) to judge whether 
 * the kth code version is bad or not.
*/
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        int start=1, end=n;
        while(start+1<end){
            // int middle = (start+end)/2;
            int middle = start + (end - start) / 2; // avoid overflow
            if(!SVNRepo.isBadVersion(middle)){
                start = middle;
            }
            else{
                end = middle;
            }
        }
        if(SVNRepo.isBadVersion(start)){
            return start;
        }
        else{
            return end;
        }

    }
}