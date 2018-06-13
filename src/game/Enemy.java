package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Enemy extends JLabel {
	private String dir;
	private int attack;
	private int health;
	private double speed;
	private double currentSpeed;
	private String image;
	private int loc;
	private int cooldown = 0;
	
	private boolean heal = false;
	private boolean shield = false;
	private boolean dodge = false;
	
	
	public Enemy(String type) {
		switch (type) {
			case "basic":
				speed = 4;
				attack = 1;
				health = 22;
				image = "src\\assets\\enemyBasic.png";
				break;
			case "fast":
				speed = 10;
				attack = 2;
				health = 12;
				image = "src\\assets\\enemyFast.png";
				break;
			case "heal":
				speed = 2.5;
				attack = 4;
				health = 30;
				heal = true;
				image = "src\\assets\\enemyHeal.png";
				break;
			case "shield":
				speed = 2.5;
				attack = 4;
				health = 30;
				shield = true;
				image = "src\\assets\\enemyShield.png";
				break;
			case "dodge":
				speed = 3;
				attack = 3;
				health = 20;
				dodge = true;
				image = "src\\assets\\enemyDodge.png";
				break;
			case "boss":
				speed = 1;
				attack = 20;
				health = 100;
				shield = true;
				heal = true;
				image = "src\\assets\\enemyBoss.png";
				break;
		}

		loc = -1;
		dir = "up";
		this.setSize(25, 25);
		
	}
	
	public boolean getShield() {
		if (cooldown == 0) {
			if (shield) {
				cooldown = 30;
			}
			return shield;
		}
		return false;
	}
	
	public boolean getHeal() {
		if (cooldown == 0) {
			if (heal) {
				cooldown = 15;
			}
			return heal;
		}
		return false;
	}
	
	public void heal() {
		health += 3;
	}
	
	public void shield() {
		health += 2;
	}
	
	public void hit(int damage) {
		if (dodge) {
			if (Math.random() > 0.333) {
				health -= damage;
			}
		} else {
			health -= damage;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getAttack() {
		return attack;
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
		if (cooldown > 0) {
			cooldown--;
		}
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
