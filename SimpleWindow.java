package FalloffMapTest;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SimpleWindow extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int size = 1000;
	Plane plane;
	float[][] map;
	int seed = 1028;
	
	SimpleWindow() {
		super("My First Window");
		setSize(size, size);
		init();
		setBackground(Color.LIGHT_GRAY);
		setLocation(100, 100);
		addWindowListener(new WindowClosingAdapter(true));
		setVisible(true);
	}

	public static void main(String[] args) {

		SimpleWindow win = new SimpleWindow();

	}

	float[][] createFalloffMap(int size){
		float [][] falloffMap = FalloffMappArrayTest.createFalloffArray(size);
		return falloffMap;
	}
	
	float[][] createWhiteNoiseMap(int seed, int size){
		
		NoiseMapTest NSG = new NoiseMapTest(size, size);
		
		float[][] whiteNoiseMap = NSG.generateWhiteNoiseMap(seed);
		return whiteNoiseMap;
	}
	
	void init() {
//		map = FalloffMappArrayTest.createFalloffArray(size);
		map = createWhiteNoiseMap(seed, size);
		plane = new Plane(size, size);
		plane.drawPlane(map);
		JLabel img = new JLabel(new ImageIcon(plane.bImage));
		this.add(img);
	}
	
	void redrawPlane() {
		map = FalloffMappArrayTest.createFalloffArray(size);
		plane.setSize(size);
		plane.drawPlane(map);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		size = this.getWidth();
		setSize(size, size);
		redrawPlane();
	}
	
	
}