package FalloffMapTest;

import java.util.Random;

public class NoiseMapTest {

	int height, width;
	float[][] map;
	
	public NoiseMapTest(int width, int height) {
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
		
		map = new float[width][height];
		
	}
	
	public void noiseGenerate(int seed) {
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				int sampleX;
				int sampleY;
			}
		}
		
		return;
	}
	
	public float[][] generateWhiteNoiseMap(int seed){
		Random rd = new Random();
		rd.setSeed(seed);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				map[i][j] = rd.nextFloat();
			}
		}
		
		return map;
	}
	
	public static void main(String[] args) {
		int seed = 1028;
		
		Random rd = new Random();
		rd.setSeed(seed);
		float n = rd.nextFloat();
		n = rd.nextFloat();
		System.out.println(n);
	}
}
