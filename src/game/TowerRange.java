package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class TowerRange extends JLabel {
	private String imagePath;
	private int diameter;
	
	public TowerRange(int d) {
		super();
		diameter = d;
		imagePath = "src\\assets\\range.png";
	}
	
	/**
	 * Sets the bounds of the object
	 * @param x The x value of the tower
	 * @param y The Y value of the tower
	 */
	public void setBounds(int x, int y) {
		x += 25 - (diameter * 25);
		y += 25 - (diameter * 25);
		super.setBounds(x, y, diameter * 50, diameter * 50);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		File file = new File(imagePath);
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        Graphics2D g2d = (Graphics2D)g;
        g2d.scale(diameter, diameter);
        g2d.drawImage(image,0,0,null);
        
	}
}
