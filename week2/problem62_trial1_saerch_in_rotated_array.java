public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        
        if(A==null || A.length==0){
            return -1;
        }
        
        int start=0, end=A.length-1;
        while(start+1<end){ // must use start+1<end not start<end for [4,3]
            int middle = (start+end)/2;
            if(A[0]<A[middle]){
                if(target>=A[0] && target<=A[middle]){
                    end = middle;
                }
                else{
                    start = middle;
                }
            }
            else{ // A[0]>=A[middle]
                if(target>=A[middle] && target<A[0]){
                    start = middle;
                }
                else{ // target>=A[0] || target<A[mid]
                    end = middle;
                }
            }
        }
        
        if(target==A[start]){
            return start;
        }
        if(target==A[end]){
            return end;
        }
        
        return -1;
    }
}