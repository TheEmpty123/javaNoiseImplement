package FalloffMapTest;

public class PerlinNoise {

    private static final int GRID_SIZE = 1000;
    private static final double[][] gradients = generateGradients(GRID_SIZE);
    float[][] map = new float[GRID_SIZE][GRID_SIZE];
    float minHeight = Float.MIN_VALUE, maxHeight = Float.MAX_VALUE;

    public static void main(String[] args) {
        double x = 1;
        double y = 2;
        double noiseValue = perlinNoise(x, y);
        System.out.println("Perlin noise value at (" + x + ", " + y + "): " + noiseValue);
    }

	public float[][] noiseGenerate(float scale) {
		
		if(scale <= 1) scale = 1.1f;
		
		for (int y = 0; y < GRID_SIZE; y++) {
			for (int x = 0; x < GRID_SIZE; x++) {
				float sampleX = x / scale;
				float sampleY = y / scale;
				
				float value = perlinNoise(sampleX, sampleY);
				if(value < minHeight) minHeight = value;
				if(value > maxHeight) maxHeight = value;
				map[x][y] = value;
			}
		}
		
		return map;
	}
    
    private static double[][] generateGradients(int size) {
        double[][] gradients = new double[size][size];
        // Generate random gradient vectors
        // (In a real implementation, these vectors should be normalized)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gradients[i][j] = Math.random() * 2 - 1; // Random value between -1 and 1
            }
        }
        return gradients;
    }

    private static float perlinNoise(double x, double y) {
        int x0 = (int) x;
        int y0 = (int) y;
        int x1 = x0 + 1;
        int y1 = y0 + 1;

        // Interpolation weights
        double sx = x - x0;
        double sy = y - y0;

        // Dot products between gradients and displacement vectors
        double n0 = dotGridGradient(x0, y0, x, y);
        double n1 = dotGridGradient(x1, y0, x, y);
        double ix0 = interpolate(n0, n1, sx);

        double n2 = dotGridGradient(x0, y1, x, y);
        double n3 = dotGridGradient(x1, y1, x, y);
        double ix1 = interpolate(n2, n3, sx);

        return interpolate(ix0, ix1, sy);
    }

    private static double dotGridGradient(int ix, int iy, double x, double y) {
        // Compute displacement vector
        double dx = x - ix;
        double dy = y - iy;

        // Compute dot product
        return dx * gradients[ix][iy] + dy * gradients[ix][iy];
    }

    private static float interpolate(double a, double b, double t) {
        // Interpolate using cosine interpolation
        double ft = t * Math.PI;
        double f = (1 - Math.cos(ft)) * 0.5;
        return (float) (a * (1 - f) + b * f);
    }
}
