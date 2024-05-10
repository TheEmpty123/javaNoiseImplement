package FalloffMapTest;

import java.util.Random;

public class NoiseMapTest {

	private int height, width;
	private double[][] gradientsX, gradientsY;
	private float[][] map;
	private Random rd = new Random();

	public NoiseMapTest(int width, int height, int seed) {
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
		this.gradientsX = new double[width][height];
		this.gradientsY = new double[width][height];
		this.rd.setSeed(seed);
	}

	public float[][] noiseGenerate(int seed, float scale) {
		generateGrid();
		initializeGradients();
//		float minHeight = Float.MAX_VALUE, maxHeight = Float.MIN_VALUE;

		if (scale <= 1)
			scale = 1.1f;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				float sampleX = x / scale;
				float sampleY = y / scale;

				float value = perlinNoise(sampleX, sampleY);

//				if (value < minHeight)
//					minHeight = value;
//				if (value > maxHeight)
//					maxHeight = value;
//				if(value < 0) value = Math.abs(value);

				map[x][y] = value + 0.5f;
			}
		}

//		float avg = (maxHeight + minHeight);
//		System.out.println(minHeight + " " + maxHeight);
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				map[x][y] += avg;
//			}
//		}

		return map;
	}

	private float perlinNoise(float x, float y) {
		int x0 = (int) x;
		int y0 = (int) y;
		int x1 = x0 + 1;
		int y1 = y0 + 1;

		double dx0 = x - x0;
		double dy0 = y - y0;
//        double dx1 = x - x1;
//        double dy1 = y - y1;

		double dot00 = dotProduct(x0, y0, x, y);
		double dot01 = dotProduct(x0, y1, x, y);
		double dot10 = dotProduct(x1, y0, x, y);
		double dot11 = dotProduct(x1, y1, x, y);

//        double dot00 = dx0 * gradientsX[x0][y0] + dy0 * gradientsY[x0][y0];
//        double dot01 = dx0 * gradientsX[x0][y1] + dy1 * gradientsY[x0][y1];
//        double dot10 = dx1 * gradientsX[x1][y0] + dy0 * gradientsY[x1][y0];
//        double dot11 = dx1 * gradientsX[x1][y1] + dy1 * gradientsY[x1][y1];

		double sx = interpolate(dot00, dot10, dx0);
		double sy = interpolate(dot01, dot11, dx0);
//		double sx = dx0;
//		double sy = dy0;

//		float finalValue = lerp(lerp(dot00, dot10, sx), lerp(dot01, dot11, sx), sy);
//		float finalValue = lerp(-1, 1, interpolate(sx, sy, dy0));
		float finalValue = interpolate(sx, sy, dy0);
		return finalValue;
	}

//	private float lerp(double a0, double a1, double w) {
//		return (float) ((1.0 - w) * a0 + w * a1);
//	}

	private float interpolate(double a, double b, double t) {
		// Interpolate using cosine interpolation
		double ft = t * Math.PI;
		double f = (1 - Math.cos(ft)) * 0.5;
		return (float) (a * (1 - f) + b * f);
	}

	private double dotProduct(int ix, int iy, double x, double y) {
		double dx1 = x - ix;
		double dy1 = y - iy;

		return dx1 * gradientsX[ix][iy] + dy1 * gradientsY[ix][iy];
	}

	private void generateGrid() {
		map = new float[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				map[i][j] = rd.nextFloat();
			}
		}
	}

	private void initializeGradients() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				double angle = rd.nextDouble() * 2 * Math.PI;
				gradientsX[i][j] = Math.cos(angle);
				gradientsY[i][j] = Math.sin(angle);
			}
		}
	}

	public float[][] generateWhiteNoiseMap(int seed) {
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
