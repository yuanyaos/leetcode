
public class TravelIsFun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		int g = 1;
		int[] originCities = new int[] {10,4,3,6};
		int[] destinationCities = new int[] {3,6,2,9};
		
		Solution test = new Solution();
		int[] result = test.TravelisFun(n, g, originCities, destinationCities);
		
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}
}
