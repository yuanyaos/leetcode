
public class Solution {
	public void quicksort(int[] array, int low, int high){
		if(low<high){
			int pindex = partition(array,low,high);
			
			quicksort(array,low,pindex-1);
			quicksort(array,pindex+1,high);
		}
		
	}
	
	private int partition(int[] array, int low, int high){
		int pivot = array[high];
		
		int i = low-1;
		for(int j=low;j<high;j++){
			if(array[j]<pivot){
				i++;
				swap(array,i,j);
			}
		}
		swap(array,i+1,high);	// insert pivot to partition the array
		return i+1;
	}
	
	private void swap(int[] array, int i, int j){
		int t = array[j];
		array[j] = array[i];
		array[i] = t;
	}
}
