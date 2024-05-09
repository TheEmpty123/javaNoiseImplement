package FalloffMapTest;

public class FalloffMappArrayTest {
	
	public static float[][] createFalloffArray(int size){
		
		float map[][] = new float[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				float x = i /  (float) size * 2 - 1;
				float y = j /  (float) size * 2 - 1;
				
				float value = Math.max(Math.abs(x), Math.abs(y));				
				map[i][j] = value;
			}
		}
		
		return map;
	}
	
	public static void printArray(float[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int size = 50;
		float map[][] = createFalloffArray(size);
		
		printArray(map);
	}
}
