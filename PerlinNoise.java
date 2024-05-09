package FalloffMapTest;

public class PerlinNoise {

    private static final int GRID_SIZE = 100;
    private static final double[][] gradients = generateGradients(GRID_SIZE);

    public static void main(String[] args) {
        double x = 0.5;
        double y = 0.5;
        double noiseValue = perlinNoise(x, y);
        System.out.println("Perlin noise value at (" + x + ", " + y + "): " + noiseValue);
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

    private static double perlinNoise(double x, double y) {
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

    private static double interpolate(double a, double b, double t) {
        // Interpolate using cosine interpolation
        double ft = t * Math.PI;
        double f = (1 - Math.cos(ft)) * 0.5;
        return a * (1 - f) + b * f;
    }
}
