package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Enemy extends JLabel {
	private String dir;
	private int health;
	private double speed;
	private double currentSpeed;
	private String image;
	private int loc;
	
	public Enemy() {
		loc = -1;
		speed = 3.5;
		dir = "up";
		this.setSize(25, 25);
		image = "src\\assets\\enemyTest.png";
	}
	
	public String getImage() {
		return image;
	}
	
	public int getGrid() {
		return loc;
	}
	
	public String getDir() {
		return dir;
	}
	
	public void nextGrid() {
		loc++;
	}
	
	public void setDir(String s) {
		dir = s;
	}

	public void setBounds(int x, int y) {
		this.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	public void move() {
		currentSpeed += speed;
		if (dir != null) {
			switch (dir) {
				case "up":
					while (currentSpeed >= 1) {
						currentSpeed--;
						this.setBounds(this.getX(), this.getY() - 1, this.getWidth(), this.getHeight());
					}
					break;
				case "right":
					while (currentSpeed >= 1) {
						currentSpeed--;
						this.setBounds(this.getX() + 1, this.getY(), this.getWidth(), this.getHeight());
					}
					break;
				case "down":
					while (currentSpeed >= 1) {
						currentSpeed--;
						this.setBounds(this.getX(), this.getY() + 1, this.getWidth(), this.getHeight());
					}
					break;
				case "left":
					while (currentSpeed >= 1) {
						currentSpeed--;
						this.setBounds(this.getX() - 1, this.getY(), this.getWidth(), this.getHeight());
					}
					break;
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
			File file = new File(image);
			Image image = null;
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		    super.paintComponent(g);
		    g.drawImage(image, 0, 0, null);
	}
}
