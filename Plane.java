package FalloffMapTest;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Plane {
	BufferedImage bImage;
	int width, height;
	
	
	public Plane(int width, int height) {
		this.width = width;
		this.height = height;
		
		bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public void setSize(int size) {
		this.height = size;
		this.width = size;
		this.bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	public void drawPlane(float[][] map) {

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				float value = map[i][j];
				int colorValue = (int) (255 * value);
				Color color = new Color(colorValue, colorValue, colorValue);
				bImage.setRGB(i, j, color.getRGB());
			}
		}
		
	}
	
}
