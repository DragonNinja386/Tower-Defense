package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Projectile extends JLabel {
	private static final Dimension ARROW = new Dimension(40,40);
	
	private URL image;
	private Enemy target;
	private double speed;
	private double currentSpeed;
	private int damage;
	
	public Projectile(URL projectile, Enemy t) {
		super();
		this.setSize(ARROW);
		speed = 10;
		
		target = t;
		image = projectile;
	}
	
	public void setDamage(int d) {
		damage = d;
	}
	
	public void move() {
		int xMod;
		int yMod;
		int px = this.getX() + this.getWidth() / 2, py = this.getY() + this.getHeight() / 2;
		int tx = target.getX() + target.getWidth() / 2, ty = target.getY() + target.getHeight() / 2;
		if (px < tx) {
			xMod = 1;
		} else if (px == tx){
			xMod = 0;
		} else {
			xMod = -1;
		}
		if (py < ty) {
			yMod = 1;
		} else if (py == ty) {
			yMod = 0;
		} else {
			yMod = -1;
		}
		
		currentSpeed += speed;
		while (currentSpeed >= 1) {
			currentSpeed--;
			this.setBounds(this.getX() + 1 * xMod, this.getY() + 1 * yMod, this.getWidth(), this.getHeight());
		}
	}
	
	public URL getImagePath() {
		return image;
	}
	
	public Enemy getTarget() {
		return target;
	}
	
	public void setBounds(int x, int y) {
		super.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	public boolean checkCollision() {
		int enemyRadius = target.getWidth() / 2;
		int projectileRadius = this.getHeight() / 2;
		int distance = (int)(Math.sqrt(Math.pow(this.getX() - target.getX(), 2) + Math.pow(this.getY() - target.getY(), 2)));
		if(projectileRadius + enemyRadius >= distance) {
			target.hit(damage);
			return true;
		}
		return false;
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		Image image2 = null;
		try {
			image2 = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(90), this.getWidth() / 2, this.getHeight() / 2);
        g2d.drawImage(image2,0,0,null);
        
	}
}
