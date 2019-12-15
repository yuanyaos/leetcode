package leetcode54;

import java.util.*;

public class Solution {
	public List<Integer> spiralOrder(int[][] matrix) {
		if(matrix.length==0)
			return new ArrayList<Integer>();
		
        List<Integer> result = new ArrayList<Integer>();
        
        int r = matrix.length, c = matrix[0].length;
        int count = 0;
        int i = 0, j = -1;
        while(count<r*c){
        	j++;
        	while(count<r*c){
        		if(j+1>=c || matrix[i][j+1]==Integer.MAX_VALUE)
        			break;        		
        		result.add(matrix[i][j]);
        		matrix[i][j] = Integer.MAX_VALUE;        		
        		count++;
        		j++;
        	}
        	if(count<r*c){
	        	result.add(matrix[i][j]);
	    		matrix[i][j] = Integer.MAX_VALUE;    		
	    		count++;
        	}
        	
        	i++;
        	while(count<r*c){
        		if(i+1>=r || matrix[i+1][j]==Integer.MAX_VALUE)
        			break; 
        		result.add(matrix[i][j]);
        		matrix[i][j] = Integer.MAX_VALUE;        		
        		count++;
        		i++;
        	}
        	if(count<r*c){
	        	result.add(matrix[i][j]);
	    		matrix[i][j] = Integer.MAX_VALUE;    		
	    		count++;
        	}
        	
        	j--;
        	while(count<r*c){
        		if(j-1<0 || matrix[i][j-1]==Integer.MAX_VALUE)
        			break;
        		result.add(matrix[i][j]);
        		matrix[i][j] = Integer.MAX_VALUE;        		
        		count++;
        		j--;
        	}
        	if(count<r*c){
	        	result.add(matrix[i][j]);
	    		matrix[i][j] = Integer.MAX_VALUE;    		
	    		count++;
        	}
        	
        	i--;
        	while(count<r*c){
        		if(i-1<0 || matrix[i-1][j]==Integer.MAX_VALUE)
        			break; 
        		result.add(matrix[i][j]);
        		matrix[i][j] = Integer.MAX_VALUE;        		
        		count++;
        		i--;
        	}
        	if(count<r*c){
	        	result.add(matrix[i][j]);
	    		matrix[i][j] = Integer.MAX_VALUE;    		
	    		count++;
        	}
        }
        
        return result;
    }
}
