
public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[]{2,54,2,5,2,5,2,25,342,22,1,0};
		
		Solution test = new Solution();
		test.quicksort(array, 0, array.length-1);
		
		for(int i=0;i<array.length-1;i++)
			System.out.println(array[i]);
	}

}