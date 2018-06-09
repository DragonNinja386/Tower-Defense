package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Projectile extends JLabel {
	private static final Dimension ARROW = new Dimension(40,40);
	
	private String image;
	private Enemy target;
	
	public Projectile(String imagePath, Enemy t) {
		super();
		if (imagePath.indexOf("arrow") != -1) {
			this.setSize(ARROW);
		} else {
			this.setSize(ARROW);
			System.out.println("There has been a fatal error in which you should shout out, \"WHY ARE THE PROJECTILES BROKEN!?!?\"");
		}
		target = t;
		image = imagePath;
	}
	
	public Enemy getTarget() {
		return target;
	}
	
	public void setBounds(int x, int y) {
		super.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		File file = new File(image);
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(90), this.getWidth() / 2, this.getHeight() / 2);
        g2d.drawImage(image,0,0,null);
        
	}
}
